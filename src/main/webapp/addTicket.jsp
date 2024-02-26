<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    }

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Add Ticket</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="background-color: darkgrey">

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card mt-3">
                <jsp:include page="components/ok_message.jsp"/>
                <jsp:include page="components/error_message.jsp"/>
                <div class="card-header text-center fs-4"><h3>Adding Ticket</h3></div>
                <div class="card-body">
                    <form action="addTicket" method="post">
                        <div class="mb-3">
                            <input type="hidden" class="form-control" id="ticketOwner" name="ticketOwner"
                                   value=" <%= currentUser.getUsername() %>">
                        </div>
                        <div class="mb-3">
                            <label for="start" class="form-label">Start</label>
                            <input type="text" class="form-control" id="start" name="start"
                                   placeholder="Enter your start..." required>
                        </div>
                        <div class="mb-3">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" id="destination" name="destination"
                                   placeholder="Enter your destination..." required>
                        </div>
                        <div class="mb-3">
                            <label for="moveDate" class="form-label">Date</label>
                            <input type="date" class="form-control" id="moveDate" name="moveDate" required>
                        </div>
                        <div class="mb-3">
                            <label for="moveTime" class="form-label">Time</label>
                            <input type="time" class="form-control" id="moveTime" name="moveTime" required>
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-outline-success">Add</button>
                            <button type="reset" class="btn btn-outline-danger">Reset</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


</body>
</html>
