<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="nvb.dev.busticketreservation.base.repository.util.HibernateUtil" %>
<%@ page import="nvb.dev.busticketreservation.repository.TravelRepository" %>
<%@ page import="nvb.dev.busticketreservation.repository.impl.TravelRepositoryImpl" %>
<%@ page import="nvb.dev.busticketreservation.service.TravelService" %>
<%@ page import="nvb.dev.busticketreservation.service.impl.TravelServiceImpl" %>
<%@ page import="nvb.dev.busticketreservation.entity.Travel" %>
<%@ page import="java.util.Optional" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>View Ticket</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<%
    try {

        long id = Long.parseLong(request.getParameter("id"));

        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        TravelRepository travelRepository = new TravelRepositoryImpl(entityManager);
        TravelService travelService = new TravelServiceImpl(entityManager, travelRepository);

        Travel travel = travelService.findById(id).orElseThrow();

%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card mt-3">
                <div class="card-header text-center fs-4"><h3>Travel Info</h3></div>
                <div class="card-body">
                    <form>
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="fullName" name="fullName"
                                   value="<%= travel.getFullName() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="gender" class="form-label">Gender</label>
                            <input type="text" class="form-control" id="gender" name="gender"
                                   value="<%= travel.getGender() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="start" class="form-label">Start</label>
                            <input type="text" class="form-control" id="start" name="start"
                                   value="<%= travel.getStart() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" id="destination" name="destination"
                                   value="<%= travel.getDestination() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="moveDate" class="form-label">Move Date</label>
                            <input type="date" class="form-control" id="moveDate" name="moveDate"
                                   value="<%= travel.getDate() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="moveTime" class="form-label">Move Time</label>
                            <input type="time" class="form-control" id="moveTime" name="moveTime"
                                   value="<%= travel.getTime() %>" disabled>
                        </div>
                        <div class="mb-3">
                            <label for="travelCode" class="form-label">Travel Code</label>
                            <input type="text" class="form-control" id="travelCode" name="travelCode"
                                   value="<%= travel.getTravelCode() %>" disabled>
                        </div>
                        <div class="container text-center">
                            <button type="button" class="btn btn-danger" data-bs-toggle="modal"
                                    data-bs-target="#cancelModal">Cancel
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Cancel Modal -->
<div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Cancel the Ticket</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="cancelTravel?id=<%= travel.getId() %>" method="post">
                    <div class="container text-center">
                        <strong>Are you sure you want to cancel your travel?</strong>
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

<%
    } catch (Exception e) {
        e.getStackTrace();
    }
%>

</body>
</html>
