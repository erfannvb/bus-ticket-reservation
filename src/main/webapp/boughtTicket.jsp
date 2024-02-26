<%@ page import="nvb.dev.busticketreservation.entity.User" %>
<%@ page import="jakarta.persistence.EntityManager" %>
<%@ page import="nvb.dev.busticketreservation.base.repository.util.HibernateUtil" %>
<%@ page import="nvb.dev.busticketreservation.repository.TravelRepository" %>
<%@ page import="nvb.dev.busticketreservation.repository.impl.TravelRepositoryImpl" %>
<%@ page import="nvb.dev.busticketreservation.service.TravelService" %>
<%@ page import="nvb.dev.busticketreservation.service.impl.TravelServiceImpl" %>
<%@ page import="nvb.dev.busticketreservation.entity.Travel" %>
<%@ page import="java.util.List" %>
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
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bought Tickets</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<%
    try {

        EntityManager entityManager = HibernateUtil.getEntityManagerFactory().createEntityManager();

        TravelRepository travelRepository = new TravelRepositoryImpl(entityManager);
        TravelService travelService = new TravelServiceImpl(entityManager, travelRepository);

        List<Travel> travelList = travelService.findTravelByUserId(currentUser.getId());

        request.setAttribute("travelList", travelList);

    } catch (Exception e) {
        e.getStackTrace();
    }
%>

<div class="container w-75 mt-5 p-3">
    <div class="table-responsive">
        <table class="table table-bordered table-striped table-hover">
            <caption class="text-white">Your tickets</caption>
            <tr>
                <th style="text-align: center">Travel Date</th>
                <th style="text-align: center">Travel Code</th>
                <th style="text-align: center">Action</th>
            </tr>
            <c:forEach items="${travelList}" var="travel">
                <tr>
                    <td style="text-align: center"><c:out value="${travel.date}"/></td>
                    <td style="text-align: center"><c:out value="${travel.travelCode}"/></td>
                    <td style="text-align: center"><a class="btn btn-outline-primary"
                                                      href="viewTicket.jsp?id=${travel.id}">View</a>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

</body>
</html>
