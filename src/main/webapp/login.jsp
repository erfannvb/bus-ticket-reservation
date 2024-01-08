<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Login Form</title>
    <jsp:include page="components/common_css_js.jsp"/>
</head>
<body style="background-image: linear-gradient(to right, #7762ca, #6078d6, #4e8adb, #4b9bdb, #58a9d8);">

<jsp:include page="components/navbar.jsp"/>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-4 offset-md-4">
            <div class="card mt-3">
                <jsp:include page="components/ok_message.jsp"/>
                <jsp:include page="components/error_message.jsp"/>
                <div class="card-header text-center fs-4"><h3>Login</h3></div>
                <div class="card-body">
                    <form action="login" method="post">
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
                        <div class="container text-center">
                            <button type="submit" class="btn btn-primary">Login</button>
                            <button type="reset" class="btn btn-danger">Reset</button>
                        </div>
                        <div class="mt-3 text-center">
                            <p>Not a member? <a id="signUpLink" href="register.jsp">Sign Up</a></p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
