<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<jsp:include page="common_css_js.jsp"/>
<%
    User currentUser = (User) session.getAttribute("currentUser");
%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark py-2">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/index.jsp">Home</a>
                </li>
            </ul>
            <ul class="navbar-nav ml-auto">

                <%
                    if (currentUser == null) {%>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/login.jsp">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" aria-current="page" href="/register.jsp">Register</a>
                </li>

                <%
                } else {
                %>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page"
                       href="/homePage.jsp">
                        <%= currentUser.getUsername() %>
                    </a>
                </li>

                <li class="nav-item">
                    <a class="nav-link" aria-current="page" id="logoutNavLink"
                       data-bs-toggle="modal" data-bs-target="#logoutModal">
                        Logout
                    </a>
                </li>

                <% } %>

            </ul>
        </div>
    </div>
</nav>

<!-- Modal -->
<div class="modal fade" id="logoutModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Logout</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="logout" method="post">
                    <div class="container text-center">
                        <strong>Are you sure you want to log out?</strong>
                    </div>
                    <hr>
                    <div class="text-center">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                        <button type="submit" class="btn btn-danger">Yes</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>