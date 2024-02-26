<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Book Ticket</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body>

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card mt-5">
                <jsp:include page="components/ok_message.jsp"/>
                <jsp:include page="components/error_message.jsp"/>
                <div class="card-header text-center fs-4"><h3>Book Ticket</h3></div>
                <div class="card-body">
                    <form action="confirmTicket" method="post">
                        <input type="hidden" name="start" value="<%= request.getParameter("start") %>">
                        <input type="hidden" name="destination" value="<%= request.getParameter("destination") %>">
                        <input type="hidden" name="moveDate" value="<%= request.getParameter("moveDate") %>">
                        <input type="hidden" name="moveTime" value="<%= request.getParameter("moveTime") %>">
                        <input type="hidden" name="travelCode" value="<%= request.getParameter("travelCode") %>">
                        <div class="mb-3">
                            <label for="fullName" class="form-label">Full Name</label>
                            <input type="text" class="form-control" id="fullName" name="fullName"
                                   placeholder="Enter your full name..." required>
                        </div>
                        <div class="mt-3 mb-3">
                            <label class="form-label my-3 mx-2">Gender</label>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender"
                                       id="maleRadioOption" value="MALE">
                                <label class="form-check-label" for="maleRadioOption">Male</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender"
                                       id="femaleRadioOption" value="FEMALE">
                                <label class="form-check-label" for="femaleRadioOption">Female</label>
                            </div>
                        </div>
                        <div class="container text-center">
                            <button type="submit" class="btn btn-primary">Confirm</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
