package nvb.dev.busticketreservation.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.busticketreservation.base.repository.util.HibernateUtil;
import nvb.dev.busticketreservation.repository.TravelRepository;
import nvb.dev.busticketreservation.repository.impl.TravelRepositoryImpl;
import nvb.dev.busticketreservation.service.TravelService;
import nvb.dev.busticketreservation.service.impl.TravelServiceImpl;

import java.io.IOException;

@WebServlet(name = "CancelTravelServlet", urlPatterns = "/cancelTravel")
public class CancelTravelServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

    private final TravelRepository travelRepository = new TravelRepositoryImpl(entityManager);
    private final TravelService travelService = new TravelServiceImpl(entityManager, travelRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();

            long id = Long.parseLong(req.getParameter("id"));
            travelService.deleteTravelById(id);

            transaction.commit();

            httpSession.setAttribute("message", "Travel cancelled successfully!");
            resp.sendRedirect("/homePage.jsp");

        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            httpSession.setAttribute("error", "Something went wrong!");
            resp.sendRedirect("/homePage.jsp");
        } finally {
            entityManager.close();
        }

    }
}
