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
    <nav class="navbar navbar-light bg-light justify-content-between">
        <a href="<%=request.getContextPath()%>/list"
               class="navbar-brand"> Mini Todo List</a>

        <ul class="nav list-inline ml-auto">
            <li class="list-inline-item">
                <form action="search" method="post" class="form-inline">
                        <input type="text" class="form-control"
                           placeholder="Enter Keyword"
                           name="keyword">
                        <button class="btn btn-outline-secondary"
                                type="submit">Search</button>
                </form>
            </li>
            <li class="list-inline-item">
                <a href="<%=request.getContextPath()%>/logout"
                       class="nav-link col-4">Logout</a>
            </li>
        </ul>
    </nav>
</header>

<div class="container">

    <div class="row justify-content-md-center">
        <div class="col align-self-center col-md-auto">
            <button class="btn btn-outline-secondary" type="button">
                <a href="<%=request.getContextPath()%>/new" class="text-center">Add Todo</a>
            </button>
        </div>
    </div>

    <div class="row">
        <div class="col-sm">
            <h1 class="text-center"><a href="<%=request.getContextPath()%>/listtoday">Today</a></h1>
        </div>
        <div class="col-sm">
            <h1 class="text-center"><a href="<%=request.getContextPath()%>/list2days">Today & Tomorrow</a></h1>
        </div>
        <div class="col-sm">
            <h1 class="text-center"><a href="<%=request.getContextPath()%>/list"
                    class="text-center">All</a></h1>
        </div>
    </div>

    <div class="row">
        <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

        <div class="container">

            <table class="table table-bordered">
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Target Date</th>
                    <th>Todo Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                <!--   for (Todo todo: todos) {  -->
                <c:forEach var="todo" items="${listTodo}">

                    <tr>
                        <td><c:out value="${todo.task_name}" /></td>
                        <td><c:out value="${todo.targetDate}" /></td>
                        <td><c:out value="${todo.status}" /></td>

                        <td><a
                                href="edit?task_id=<c:out value='${todo.task_id}'
                                />">Edit
                        </a>
                            &nbsp;&nbsp;&nbsp;&nbsp; <a
                                    href="delete?task_id=<c:out
                                    value='${todo.task_id}'
                                    />">Delete</a></td>

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