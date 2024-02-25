package nvb.dev.busticketreservation.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.busticketreservation.base.repository.util.HibernateUtil;
import nvb.dev.busticketreservation.entity.Gender;
import nvb.dev.busticketreservation.entity.Ticket;
import nvb.dev.busticketreservation.entity.Travel;
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.TravelRepository;
import nvb.dev.busticketreservation.repository.UserRepository;
import nvb.dev.busticketreservation.repository.impl.TravelRepositoryImpl;
import nvb.dev.busticketreservation.repository.impl.UserRepositoryImpl;
import nvb.dev.busticketreservation.service.TravelService;
import nvb.dev.busticketreservation.service.UserService;
import nvb.dev.busticketreservation.service.impl.TravelServiceImpl;
import nvb.dev.busticketreservation.service.impl.UserServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "ConfirmTicketServlet", urlPatterns = "/confirmTicket")
public class ConfirmTicketServlet extends HttpServlet {

    private static final String BOOK_TICKET_URL = "/bookTicket.jsp";

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private final TravelRepository travelRepository = new TravelRepositoryImpl(entityManager);
    private final TravelService travelService = new TravelServiceImpl(entityManager, travelRepository);

    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        try {

            String username = ((User) httpSession.getAttribute("currentUser")).getUsername();

            Optional<User> userOptional = userService.findUserByUsername(username);

            if (userOptional.isPresent()) {

                User currentUser = userOptional.get();

                String fullName = req.getParameter("fullName");
                String start = req.getParameter("start");
                String destination = req.getParameter("destination");
                LocalDate moveDate = LocalDate.parse(req.getParameter("moveDate"));
                LocalTime moveTime = LocalTime.parse(req.getParameter("moveTime"));
                Gender gender = Gender.valueOf(req.getParameter("gender"));
                Integer travelCode = Integer.parseInt(req.getParameter("travelCode"));

                Travel travel = new Travel();
                travel.setFullName(fullName);
                travel.setStart(start);
                travel.setDestination(destination);
                travel.setMoveDate(moveDate);
                travel.setMoveTime(moveTime);
                travel.setGender(gender);
                travel.setTravelCode(travelCode);
                travel.setUser(currentUser);

                travelService.save(travel);

                httpSession.setAttribute("message", "Travel Confirmed Successfully!" + "\n" +
                        "Travel Code = " + travelCode);
                req.getRequestDispatcher(BOOK_TICKET_URL).include(req, resp);

            } else {
                httpSession.setAttribute("error", "Something went wrong.");
                req.getRequestDispatcher(BOOK_TICKET_URL).include(req, resp);
            }

        } catch (Exception e) {
            httpSession.setAttribute("error", "An error occurred while processing your request.");
            req.getRequestDispatcher(BOOK_TICKET_URL).include(req, resp);
        }

    }
}
