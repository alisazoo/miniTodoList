<header>
    <nav class="navbar navbar-light bg-light justify-content-between"
        style="margin-bottom: 1em;">
        <a href="<%=request.getContextPath()%>/list"
           class="navbar-brand"> Mini Todo List</a>

        <ul class="nav list-inline ml-auto">

<%--            <c:if test="${isList == true}">--%>
            <li class="list-inline-item" style="margin-top: 1em">
                <form action="search" method="post" class="form-inline">
                    <input type="text" class="form-control"
                           placeholder="Find a task"
                           name="keyword">
                    <button class="btn btn-outline-secondary"
                            type="submit">Search</button>
                </form>
            </li>
<%--            </c:if>--%>

            <li class="list-inline-item" style="margin-top: 1em">
                <a href="<%=request.getContextPath()%>/logout"
                   class="nav-link col-4">Logout</a>
            </li>
        </ul>
    </nav>
</header>