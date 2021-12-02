<%@ page contentType="text/html;charset=UTF-8" %>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<html>
<head>
    <title>Mini Todo List: Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>

<body>

    <div class="container col-md-4 col-md-offset-3"
         style="overflow: auto">

        <h1>Mini Todo List: Login</h1>

        <p class="text-info">${INVALIDLOGIN}</p>

        <form action="<%=request.getContextPath()%>/login" method="post">

            <div class="form-group">
                <label for="username">User Name: </label>
                <input type="text" class="form-control" id="username"
                       placeholder="User Name" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password: </label>
                <input type="password" class="form-control" id="password"
                       placeholder="Password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Log in</button>
        </form>

        <a href="<%= request.getContextPath() %>/register"
           class="btn btn-secondary" role="button">Sign up</a>
    </div>

    <jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
