<%@ page contentType="text/html;charset=UTF-8" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="ISO-8859-1">
    <title>Mini TodoList: Sign up</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../styles/main.css" type="text/css">
</head>

<body>

<div class="container col-md-4 col-md-offset-3" style="overflow: auto">

    <h1>Mini Todo List: User Register Form</h1>
        <p class="text-info">${NOTIFICATION}</p>
        <p class="text-secondary">Back to
            <a href="<%=request.getContextPath()%>/login">Login</a>?</p>
        <form action="<%=request.getContextPath()%>/register" method="post">

            <div class="form-group">
                <label for="firstname">First Name:</label> <input type="text"
                                                              class="form-control" id="firstname" placeholder="First Name" name="firstName" required>
            </div>

            <div class="form-group">
                <label for="lastname">Last Name:</label> <input type="text"
                                                             class="form-control" id="lastname" placeholder="last Name" name="lastName" required>
            </div>

            <div class="form-group">
                <label for="username">User Name:</label> <input type="text"
                                                              class="form-control" id="username" placeholder="User Name" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label> <input type="password"
                                                        class="form-control" id="password" placeholder="Password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Sign up</button>

        </form>
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>

</body>

</html>
