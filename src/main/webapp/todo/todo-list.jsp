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
    <nav class="navbar navbar-expand-md">
        <div>
            <a href="https://www.javaguides.net" class="navbar-brand"> Todo
                App</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="<%=request.getContextPath()%>/list"
                   class="nav-link">Todos</a></li>
        </ul>

        <ul class="navbar-nav navbar-collapse justify-content-end">
            <li><a href="<%=request.getContextPath()%>/logout"
                   class="nav-link">Logout</a></li>
        </ul>
    </nav>
</header>

<div class="row">
    <!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

    <div class="container">
        <h3 class="text-center">List of Todos</h3>

        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new"
               class="btn btn-secondary">Add Todo</a>
        </div>

        <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Search"
                   aria-label="Search" aria-describedby="basic-addon2">
            <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button">Button</button>
            </div>
        </div>

        <br>

<%--        //todo fix the button link: Not compatible style with HTML5--%>
        <div class="btn-group" role="group" aria-label="Basic example">
            <button type="button" class="btn btn-secondary">
                <a href="<%=request.getContextPath()%>/listtoday">Today</a></button>
            <button type="button" class="btn btn-secondary">
                <a href="<%=request.getContextPath()%>/list2days">Today &
                    Tomorrow</a>
            </button>
            <button type="button" class="btn btn-secondary">
                <a href="<%=request.getContextPath()%>/list">All</a>
            </button>
        </div>

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
</div>

<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>