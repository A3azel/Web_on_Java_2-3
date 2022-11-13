<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 11.11.2022
  Time: 22:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="CSS/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp" />
    </header>

    <main>
        <table class="table table-bordered table-striped text-center" style="margin: 20px">
            <thead class="table-info">
            <tr>
                <th scope="col">
                    ID
                </th>
                <th scope="col">
                    Нікнейм
                </th>
                <th scope="col">
                    Повне Ім'я
                </th>
                <th scope="col">
                    Роль
                </th>
                <th scope="col">
                    Статус
                </th>
                <th scope="col">Інфо/Заблокувати</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="user" items="${requestScope.userList}">
                <tr>
                    <td>${user.ID}</td>
                    <td>${user.username}</td>
                    <td>${user.firstName} ${user.lastName}</td>
                    <td>${user.userRole}</td>
                    <td>${user.accountVerified}</td>
                    <td>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-center">
                            <form method="get" action="controller">
                                <input type="hidden" name="action" value="userInfo">
                                <input type="hidden" name="userID" value="${user.ID}">
                                <button type="submit" class="btn btn-outline-info">Детальні інформація</button>
                            </form>
                            <c:choose>
                                <c:when test="${user.userRole.equals('USER')}">
                                    <c:choose>
                                        <c:when test="${user.accountVerified.equals(true)}">
                                            <form method="post" action="controller">
                                                <input type="hidden" name="action" value="allUsers">
                                                <input type="hidden" name="userID" value="${user.ID}">
                                                <button type="submit" class="btn btn-outline-danger">
                                                    Заблокувати
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form method="post" action="controller">
                                                <input type="hidden" name="action" value="allUsers">
                                                <input type="hidden" name="userID" value="${user.ID}">
                                                <button type="submit" class="btn btn-outline-success">
                                                    Розблокувати
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <nav aria-label="...">
            <ul class="pagination justify-content-center">
                <%--First--%>
                <c:choose>
                    <c:when test="${requestScope.currentPage>1}">
                        <li class="page-item">
                            <a class="page-link" href = "controller?action=allUsers&page=${1}">First</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">First</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <%-- Previous --%>
                <c:choose>
                    <c:when test="${requestScope.currentPage>1}">
                        <li class="page-item">
                            <a class="page-link" href = "controller?action=allUsers&page=${requestScope.currentPage-1}">Previous</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
                        </li>
                    </c:otherwise>
                </c:choose>
                <%-- Main --%>
                <c:choose>
                    <c:when test="${requestScope.countOfPages<=10}">
                        <c:forEach var="i" begin="1" end="${requestScope.countOfPages}">
                            <c:choose>
                                <c:when test="${i==requestScope.currentPage}">
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">${i}</span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link" href = "controller?action=allUsers&page=${i}">${i}</a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </c:when>
                </c:choose>

                <c:choose>
                    <c:when test="${requestScope.countOfPages>10}">
                        <c:choose>
                            <c:when test="${requestScope.currentPage <= 5 || requestScope.currentPage > requestScope.countOfPages-4}">
                                <c:forEach var="i" begin="1" end="5">
                                    <li class="${requestScope.currentPage != i ? 'page-item' : 'page-item active'}">
                                        <a class="page-link" href = "controller?action=allUsers&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <a class="page-link" href = "controller?action=allUsers&page=6">...</a>
                                <c:forEach var="i" begin="${requestScope.countOfPages-4}" end="${requestScope.countOfPages}">
                                    <li class="${requestScope.currentPage != i ? 'page-item' : 'page-item active'}">
                                        <a class="page-link" href = "controller?action=allUsers&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:when test="${requestScope.currentPage > 5 && requestScope.currentPage < requestScope.countOfPages-4}">
                                <c:forEach var="i" begin="1" end="5">
                                    <li class="${requestScope.currentPage != i ? 'page-item' : 'page-item active'}">
                                        <a class="page-link" href = "controller?action=allUsers&page=${requestScope.currentPage-5}">...</a>
                                    </li>
                                </c:forEach>
                            </c:when>
                        </c:choose>
                    </c:when>
                </c:choose>

                <%--Next--%>
                <c:choose>
                    <c:when test="${requestScope.currentPage<requestScope.countOfPages}">
                        <li class="page-item">
                            <a class="page-link" href = "controller?action=allUsers&page=${requestScope.currentPage+1}">Next</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
                        </li>
                    </c:otherwise>
                </c:choose>

                <%--Last--%>
                <c:choose>
                    <c:when test="${requestScope.currentPage<requestScope.countOfPages}">
                        <li class="page-item">
                            <a class="page-link" href = "controller?action=allUsers&page=${requestScope.countOfPages}">Last</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="page-item disabled">
                            <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Last</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </nav>
    </main>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
</body>
</html>
