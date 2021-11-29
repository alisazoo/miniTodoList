<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
  <title>User Management Application</title>

  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">

</head>

<body>
<header>
  <nav class="navbar navbar-expand-md navbar-dark"
       style="background-color: tomato">
    <div>
      <a href="https://www.javaguides.net" class="navbar-brand"> Todo
        App</a>
    </div>

    <ul class="navbar-nav">
<%--      // todo cannot resolve file list--%>
      <li><a href="<%=request.getContextPath()%>/list"
             class="nav-link">Todos</a></li>
    </ul>

    <ul class="navbar-nav navbar-collapse justify-content-end">
      <%--      // todo cannot resolve file logout--%>
      <li><a href="<%=request.getContextPath()%>/logout"
             class="nav-link">Logout</a></li>
    </ul>
  </nav>
</header>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">

      <%--      // todo cannot resolve variable todo--%>
      <c:if test="${todo != null}">
      <%--      // todo cannot resolve file update--%>
        <form action="update" method="post">
      </c:if>
      <%--      // todo cannot resolve variable todo & file insert--%>
      <c:if test="${todo == null}">
        <form action="insert" method="post">
      </c:if>

          <caption>
            <h2>
              <c:if test="${todo != null}">
                Edit Todo
              </c:if>
              <c:if test="${todo == null}">
                Add New Todo
              </c:if>
            </h2>
          </caption>

          <c:if test="${todo != null}">
<%--              //todo check the kind of this id--%>
            <input type="hidden" name="task_id" value="<c:out
            value='${todo.task_id}' />" />
          </c:if>

<%--          // todo send user id--%>
<%--          <input type="hidden" name="user_io" value="<c: out --%>
<%--          value='${user_id}' />" />--%>

          <fieldset class="form-group">
            <label>Todo Title</label>
              <input type="text" value="<c:out value='${todo.task_name}' />"
                     class="form-control" name="task_name"
                     required="required" minlength="2">
          </fieldset>

          <fieldset class="form-group">
            <label>Todo Status</label>
            <select class="form-control" name="isDone">
                <option value="false">In Progress</option>
                <option value="true">Complete</option>
            </select>
          </fieldset>

          <fieldset class="form-group">
            <label>Todo Target Date</label> <input type="date"
                                                   value="<c:out value='${todo.targetDate}' />" class="form-control"
                                                   name="targetDate" required="required">
          </fieldset>

          <button type="submit" class="btn btn-success">Save</button>
        </form>
    </div>
  </div>
</div>

<%--// todo the tag has empty body--%>
<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>