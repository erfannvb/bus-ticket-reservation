<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Main Page</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="overflow: hidden">

<jsp:include page="components/navbar.jsp"/>

<section class="bg-dark text-light p-5 text-center text-sm-start">
    <div class="container py-5">
        <div class="d-sm-flex align-items-center justify-content-between py-2">
            <div>
                <h1>Welcome to RideEasy</h1>
                <p class="lead my-4">
                    Get ready to embark on seamless journeys with RideEasy! Explore destinations, reserve seats, and
                    travel stress-free with our user-friendly platform.
                </p>
                <a class="btn btn-primary btn-lg" href="login.jsp">Get Started</a>
            </div>
            <a href="#">
                <img src="img/bus-main.png" alt="Bus" class="img-fluid" width="512">
            </a>
        </div>
    </div>
</section>

</body>
</html>