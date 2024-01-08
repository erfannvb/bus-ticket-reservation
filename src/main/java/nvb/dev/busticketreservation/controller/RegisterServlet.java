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
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.UserRepository;
import nvb.dev.busticketreservation.repository.impl.UserRepositoryImpl;
import nvb.dev.busticketreservation.service.UserService;
import nvb.dev.busticketreservation.service.impl.UserServiceImpl;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {

            HttpSession httpSession = req.getSession();

            String firstName = req.getParameter("firstName");
            String lastName = req.getParameter("lastName");
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            String gender = req.getParameter("gender");

            User user = new User(firstName, lastName, username, password, Gender.valueOf(gender));
            if (userService.validate(user)) {
                userService.save(user);
                httpSession.setAttribute("message", username + " registered successfully!");
                resp.sendRedirect("/register.jsp");
            }

        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
