<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
<%--    //todo check: local library for bootstrap (need?)--%>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<%--    // todo check: XML tag without body--%>
    <jsp:include page="../common/header.jsp"></jsp:include>

    <div class="container col-md-8 col-md-offset-3"
         style="overflow: auto">
        <h1>Login Form</h1>
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

            <button type="submit" class="btn btn-primary">Submit</button>

        </form>
    </div>

    <%--    // todo check: XML tag without body--%>
    <jsp:include page="../common/footer.jsp"></jsp:include>

</body>
</html>
