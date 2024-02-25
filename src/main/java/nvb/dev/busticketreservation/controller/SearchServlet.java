package nvb.dev.busticketreservation.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.busticketreservation.base.repository.util.HibernateUtil;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.repository.TicketRepository;
import nvb.dev.busticketreservation.repository.impl.TicketRepositoryImpl;
import nvb.dev.busticketreservation.service.TicketService;
import nvb.dev.busticketreservation.service.impl.TicketServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "SearchServlet", urlPatterns = "/searchTicket")
public class SearchServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final TicketRepository ticketRepository = new TicketRepositoryImpl(entityManager);
    private final TicketService ticketService = new TicketServiceImpl(entityManager, ticketRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        String start = req.getParameter("start");
        String destination = req.getParameter("destination");
        LocalDate moveDate = LocalDate.parse(req.getParameter("moveDate"));

        List<Ticket> ticketList = ticketService.findTicketByStartAndDestinationAndMoveDate(start, destination, moveDate);

        httpSession.setAttribute("ticketList", ticketList);

    }
}
