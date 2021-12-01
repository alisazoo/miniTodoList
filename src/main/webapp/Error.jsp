<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isErrorPage="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>

<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%>
<head>
  <title>Mini Todo List: Error</title>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
  <link rel="stylesheet" href="/styles/main.css" type="text/css">
</head>

<body>

<header>

  <nav class="navbar navbar-light bg-light justify-content-between">
    <a href="<%=request.getContextPath()%>/"
       class="navbar-brand"> Mini Todo List</a>
  </nav>

</header>

  <h1>Error</h1>
  <h2><%=exception.getMessage() %></h2>
</body>
</html>