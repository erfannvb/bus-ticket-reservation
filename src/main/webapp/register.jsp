<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Registration Form</title>
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
                <div class="card-header text-center fs-4"><h3>Sign Up</h3></div>
                <div class="card-body">
                    <form action="register" method="post">
                        <div class="mb-3">
                            <label for="firstName" class="form-label">First Name</label>
                            <input type="text" class="form-control" id="firstName" name="firstName"
                                   placeholder="Enter your first name..." required>
                        </div>
                        <div class="mb-3">
                            <label for="lastName" class="form-label">Last Name</label>
                            <input type="text" class="form-control" id="lastName" name="lastName"
                                   placeholder="Enter your last name..." required>
                        </div>
                        <div class="mb-3">
                            <label for="username" class="form-label">Username</label>
                            <input type="text" class="form-control" id="username" name="username"
                                   placeholder="Enter your username..." required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" class="form-control" id="password" name="password"
                                   placeholder="Enter your password..." required>
                        </div>
                        <div class="mt-3 mb-3">
                            <label class="form-label my-3 mx-2">Gender : </label>
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
                            <button type="submit" class="btn btn-outline-success">Register</button>
                            <button type="reset" class="btn btn-outline-danger">Reset</button>
                        </div>
                        <div class="mt-3 text-center">
                            <p>Already have an account? <a id="registerLink" href="login.jsp"
                                                           style="text-decoration: none">Login</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
