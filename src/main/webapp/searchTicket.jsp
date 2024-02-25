<%@ page import="nvb.dev.busticketreservation.entity.Ticket" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="nvb.dev.busticketreservation.base.repository.util.HibernateUtil" %>
<%@ page import="nvb.dev.busticketreservation.repository.TicketRepository" %>
<%@ page import="nvb.dev.busticketreservation.repository.impl.TicketRepositoryImpl" %>
<%@ page import="nvb.dev.busticketreservation.service.TicketService" %>
<%@ page import="nvb.dev.busticketreservation.service.impl.TicketServiceImpl" %>
<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <title>Search Ticket</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="background-color: darkgrey">

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-6 offset-md-3">
            <div class="card mt-3">
                <jsp:include page="components/ok_message.jsp"/>
                <jsp:include page="components/error_message.jsp"/>
                <div class="card-header text-center fs-4"><h3>Search Ticket</h3></div>
                <div class="card-body">
                    <form action="searchTicket" method="post" class="row g-3">
                        <div class="col-md-4">
                            <label for="start" class="form-label">Start</label>
                            <input type="text" class="form-control" id="start" name="start">
                        </div>
                        <div class="col-md-4">
                            <label for="destination" class="form-label">Destination</label>
                            <input type="text" class="form-control" id="destination" name="destination">
                        </div>
                        <div class="col-md-4">
                            <label for="moveDate" class="form-label">Move Date</label>
                            <input type="date" class="form-control" id="moveDate" name="moveDate">
                        </div>
                        <div class="container text-center col-12 mt-3">
                            <button type="button" class="btn btn-primary" id="search-btn">Search</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    $(document).ready(() => {

        $('#search-btn').click(() => {

            let start = $('#start').val();
            let destination = $('#destination').val();
            let moveDate = $('#moveDate').val();

            $.ajax({
                type: 'POST',
                data: {
                    start: start,
                    destination: destination,
                    moveDate: moveDate
                },
                url: 'http://localhost:8080/searchTicket',
                success: () => {
                    window.location = '/searchTicket.jsp';
                },
                error: (err) => {
                    console.log(err);
                }
            });

        });
    });

</script>

<%
    List<Ticket> ticketList = (List<Ticket>) session.getAttribute("ticketList");
    request.setAttribute("ticketList", ticketList);
%>

<jsp:include page="components/ok_message.jsp"/>
<jsp:include page="components/error_message.jsp"/>

<div class="container w-75 mt-5 p-3">
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <caption class="text-white">Available Tickets</caption>
            <tr>
                <th style="text-align: center">Path</th>
                <th style="text-align: center">Move Date</th>
                <th style="text-align: center">Move Time</th>
                <th style="text-align: center">Travel ID</th>
                <th style="text-align: center"></th>
            </tr>
            <c:forEach items="${ticketList}" var="ticket">
                <tr>
                    <td style="text-align: center"><c:out value="${ticket.start} - ${ticket.destination}"/></td>
                    <td style="text-align: center"><c:out value="${ticket.moveDate}"/></td>
                    <td style="text-align: center"><c:out value="${ticket.moveTime}"/></td>
                    <td style="text-align: center"><c:out value="${ticket.travelCode}"/></td>
                    <td style="text-align: center"><a class="btn btn-outline-primary">Select</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
