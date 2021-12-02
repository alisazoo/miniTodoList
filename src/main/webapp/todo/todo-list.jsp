<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<html>
<head>
    <title>Mini Todo List</title>

    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <link rel="stylesheet" href="${root}/styles/main.css" type="text/css">

</head>

<body>

<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">

    <div class="row justify-content-md-center" style="margin-top: 1em">
        <div class="col align-self-center col-md-auto">
            <a href="<%=request.getContextPath()%>/new"
               class="btn btn-secondary" role="button">Add Todo</a>
        </div>
    </div>

    <div class="row" style="margin-top: 1em">
        <div class="col-sm">
            <h2 class="text-center align-bottom">
                <a href="<%=request.getContextPath()%>/listtoday">Today</a>
            </h2>
        </div>
        <div class="col-sm">
            <h2 class="text-center align-bottom">
                <a href="<%=request.getContextPath()%>/list2days">Today & Tomorrow</a>
            </h2>
        </div>
        <div class="col-sm">
            <h2 class="text-center align-bottom">
                <a href="<%=request.getContextPath()%>/list"
                    class="text-center">All</a>
            </h2>
        </div>
    </div>

    <div class="row" style="margin-top: 1em">
        <p></p>
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Due</th>
                    <th>Task</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <!--   for (Todo todo: todos) {  -->
                <c:forEach var="todo" items="${listTodo}">

                    <tr>
                        <td><c:out value="${todo.targetDate}" /></td>
                        <td><c:out value="${todo.task_name}" /></td>
                        <td>
                            <c:if test="${!todo.status}">
                                <a href="edit?task_id=<c:out
                                value ='${todo.task_id}'/>"
                                      class="btn btn-primary btn-sm"
                                   role="button">
                                    in progress
                                </a>
                            </c:if>
                            <c:if test="${todo.status}">
                                <a href="edit?task_id=<c:out
                                value ='${todo.task_id}'/>"
                                   class="btn btn-danger btn-sm" role="button">
                                    complete
                                </a>
                            </c:if>
                        </td>
                        <td>
                            <a href="edit?task_id=<c:out
                            value='${todo.task_id}'/>"
                               class="btn btn-outline-info btn-sm"
                               role="button">
                                Edit
                            </a>
                            <a href="delete?task_id=<c:out
                            value='${todo.task_id}' />"
                               class="btn btn-outline-warning btn-sm"
                               role="button">
                                Delete
                            </a>
                        </td>

                        <!--  <td><button (click)="updateTodo(todo.id)" class="btn btn-success">Update</button>
                                  <button (click)="deleteTodo(todo.id)" class="btn btn-warning">Delete</button></td> -->
                    </tr>
                </c:forEach>
                <!-- } -->
                </tbody>

            </table>
        </div>

    <%--end row for a table--%>
    </div>

<%--end: container--%>
</div>


<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>