package nvb.dev.busticketreservation.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
import java.util.Optional;

@WebFilter(filterName = "LoginFilter", servletNames = "LoginServlet", urlPatterns = "/login")
public class LoginFilter implements Filter {

    private final EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();
    private final UserRepository userRepository = new UserRepositoryImpl(entityManager);
    private final UserService userService = new UserServiceImpl(entityManager, userRepository);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession httpSession = request.getSession();

        try {

            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Optional<User> userOptional = userService.findUserByUsernameAndPassword(username, password);
            if (userOptional.isPresent()) {
                httpSession.setAttribute("currentUser", userOptional.get());
                filterChain.doFilter(request, response);
            }

        } catch (NoResultException e) {
            httpSession.setAttribute("error", "Invalid Username/Password. Please Try Again!");
            response.sendRedirect("/login.jsp");
        }

    }
}
