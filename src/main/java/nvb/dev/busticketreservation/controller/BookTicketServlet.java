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

@WebServlet(name = "BookTicketServlet", urlPatterns = "/bookTicket")
public class BookTicketServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private final TicketRepository ticketRepository = new TicketRepositoryImpl(entityManager);
    private final TicketService ticketService = new TicketServiceImpl(entityManager, ticketRepository);

    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            // Retrieve ticket information from request parameters
            String ticketOwner = req.getParameter("ticketOwner");
            String start = req.getParameter("start");
            String destination = req.getParameter("destination");
            LocalDate moveDate = LocalDate.parse(req.getParameter("moveDate"));
            LocalTime moveTime = LocalTime.parse(req.getParameter("moveTime")); // Change to LocalTime

            // Combine moveDate and moveTime to create LocalDateTime
            LocalDateTime moveDateTime = LocalDateTime.of(moveDate, moveTime);

            // Retrieve the user from the session
            User currentUser = (User) req.getSession().getAttribute("currentUser");

            // Reattach the user entity to the persistence context
            currentUser = entityManager.merge(currentUser);

            // Create a new Ticket object
            Ticket ticket = new Ticket(ticketOwner, start, destination, moveDate, moveDateTime);

            // Set the user for the ticket
            ticket.setUser(currentUser);

            // Save the ticket using TicketService
            ticketService.save(ticket);

            // Redirect the user to a success page or display a success message
            resp.sendRedirect("/bookTicket.jsp"); // Replace "success.jsp" with your actual success page
        } catch (Exception e) {
            // Handle exceptions
            req.setAttribute("errorMessage", "An error occurred while processing your request.");
            req.getRequestDispatcher("/error.jsp").forward(req, resp); // Forward to an error page
        }

    }
}
