package nvb.dev.busticketreservation.controller;

import jakarta.persistence.EntityManager;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nvb.dev.busticketreservation.base.repository.util.HibernateUtil;
import nvb.dev.busticketreservation.entity.User;
import nvb.dev.busticketreservation.repository.UserRepository;
import nvb.dev.busticketreservation.repository.impl.UserRepositoryImpl;
import nvb.dev.busticketreservation.service.UserService;
import nvb.dev.busticketreservation.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "RegisterFilter", servletNames = "RegisterServlet", urlPatterns = "/register")
public class RegisterFilter implements Filter {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();

        String username = request.getParameter("username");

        boolean usernameExists = false;

        List<User> userList = new ArrayList<>(userService.findAll());
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                usernameExists = true;
                break;
            }
        }

        if (usernameExists) {
            httpSession.setAttribute("error", "User with this username exists!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/register.jsp");
            dispatcher.include(request, response);
        } else {
            filterChain.doFilter(request, response);
        }

    }
}
