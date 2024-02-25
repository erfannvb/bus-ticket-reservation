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
import nvb.dev.busticketreservation.entity.Travel;
import nvb.dev.busticketreservation.repository.TravelRepository;
import nvb.dev.busticketreservation.repository.impl.TravelRepositoryImpl;
import nvb.dev.busticketreservation.service.TravelService;
import nvb.dev.busticketreservation.service.impl.TravelServiceImpl;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

@WebServlet(name = "ConfirmTicketServlet", urlPatterns = "/confirmTicket")
public class ConfirmTicketServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final TravelRepository travelRepository = new TravelRepositoryImpl(entityManager);
    private final TravelService travelService = new TravelServiceImpl(entityManager, travelRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();

        try {
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

            travelService.save(travel);

            httpSession.setAttribute("message", "Travel Confirmed Successfully!" + "\n" +
                    "Travel Code = " + travelCode);
            resp.sendRedirect("/bookTicket.jsp");

        } catch (Exception e) {
            httpSession.setAttribute("error", "An error occurred while processing your request.");
            resp.sendRedirect("/bookTicket.jsp");
        }

    }
}
