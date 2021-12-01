<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Mini Todo List</title>
    <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
    <link rel="stylesheet" href="../styles/main.css" type="text/css">
</head>

<body>
<header>
    <header>
        <nav class="navbar navbar-light bg-light justify-content-between">
            <a href="<%=request.getContextPath()%>/list"
               class="navbar-brand"> Mini Todo List</a>

            <ul class="nav list-inline ml-auto">
                <li class="list-inline-item">
                    <a href="<%=request.getContextPath()%>/logout"
                       class="nav-link col-4">Logout</a>
                </li>
            </ul>
        </nav>
    </header>
</header>
<div class="container col-md-5">
  <div class="card">
    <div class="card-body">

      <c:if test="${todo != null}">
        <form action="update" method="post">
      </c:if>
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
            <input type="hidden" name="task_id" value="<c:out
            value='${todo.task_id}' />" />
          </c:if>

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

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>