<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        session.setAttribute("error", "You are not logged-in! Login first.");
        response.sendRedirect("/login.jsp");
        return;
    }

%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bus Ticket Reservation</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="overflow: hidden">

<jsp:include page="components/navbar.jsp"/>

<section class="bg-dark text-light p-5 text-center text-sm-start">
    <div class="container py-5">
        <div class="d-sm-flex align-items-center justify-content-between py-2">
            <div>
                <h1>Book Your Ride:<br> Easy Bus Ticket Booking</h1>
                <p class="lead my-4">
                    Effortless bus ticket booking: Find, select, and reserve instantly!
                </p>
                <a class="btn btn-primary btn-lg" href="searchTicket.jsp">Search Ticket</a>
            </div>
            <a href="#">
                <img src="img/bus.png" alt="Bus" class="img-fluid" width="512">
            </a>
        </div>
    </div>
</section>

</body>
</html>