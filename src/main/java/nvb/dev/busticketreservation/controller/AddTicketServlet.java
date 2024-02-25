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
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.TicketRepository;
import nvb.dev.busticketreservation.repository.UserRepository;
import nvb.dev.busticketreservation.repository.impl.TicketRepositoryImpl;
import nvb.dev.busticketreservation.repository.impl.UserRepositoryImpl;
import nvb.dev.busticketreservation.service.TicketService;
import nvb.dev.busticketreservation.service.UserService;
import nvb.dev.busticketreservation.service.impl.TicketServiceImpl;
import nvb.dev.busticketreservation.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

@WebServlet(name = "BookTicketServlet", urlPatterns = "/addTicket")
public class AddTicketServlet extends HttpServlet {

    private static final String ADD_TICKET_URL = "/addTicket.jsp";

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private final TicketRepository ticketRepository = new TicketRepositoryImpl(entityManager);
    private final TicketService ticketService = new TicketServiceImpl(entityManager, ticketRepository);

    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        try {

            String ticketOwner = req.getParameter("ticketOwner");
            String start = req.getParameter("start");
            String destination = req.getParameter("destination");
            LocalDate moveDate = LocalDate.parse(req.getParameter("moveDate"));
            LocalTime moveTime = LocalTime.parse(req.getParameter("moveTime"));

            LocalDateTime moveDateTime = LocalDateTime.of(moveDate, moveTime);

            String username = ((User) httpSession.getAttribute("currentUser")).getUsername();

            Optional<User> userOptional = userService.findUserByUsername(username);
            if (userOptional.isPresent()) {
                User currentUser = userOptional.get();

                Ticket ticket = new Ticket(ticketOwner, start, destination, moveDate, moveDateTime);
                ticket.setUser(currentUser);

                ticketService.save(ticket);

                httpSession.setAttribute("message", "Ticket Added Successfully!");
                resp.sendRedirect(ADD_TICKET_URL);
            } else {
                httpSession.setAttribute("error", "Something went wrong.");
                req.getRequestDispatcher(ADD_TICKET_URL).include(req, resp);
            }

        } catch (Exception e) {
            httpSession.setAttribute("error", "An error occurred while processing your request.");
            req.getRequestDispatcher(ADD_TICKET_URL).include(req, resp);
        }

    }
}
