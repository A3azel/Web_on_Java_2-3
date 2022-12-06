<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.11.2022
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="CSS/roundButton.css" rel="stylesheet">
    <link href="CSS/footer.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp" />
    </header>

    <main>
        <div class="d-grid gap-1 d-md-flex justify-content-md-center" style="margin: 20px">
            <form method="get" action="addCity.jsp">
                <button type="submit" class="btn btn-outline-primary btn-rounded"><i class="bi bi-pencil"></i>Додати</button>
            </form>
        </div>
        <table class="table table-bordered table-striped text-center" style="margin: 20px; width: 80%; margin-left: auto; margin-right: auto">
            <thead class="table-info" >
            <tr>
                <th scope="col">
                    id
                </th>
                <th scope="col">
                    Місто
                </th>
                <th scope="col">
                    Додати станцію
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="city" items="${requestScope.citiesListForAdmin}">
                <tr>
                    <td>${city.ID}</td>
                    <td>${city.cityName}</td>
                    <td>
                        <div class="d-grid gap-3 d-md-flex justify-content-md-center">
                            <div class="d-grid gap-3 d-md-flex justify-content-md-center">
                                <form action="controller"  method="get">
                                    <input type="hidden" name="action" value="allStationsForAdmin">
                                    <input type="hidden" name="cityName" value="${city.cityName}">
                                    <button type="submit" class="btn btn-outline-primary">Всі станції</button>
                                </form>
                                <form action="controller" method="get">
                                    <input type="hidden" name="action" value="addStation">
                                    <%--<input type="hidden" name="requestType" value="addStation">--%>
                                    <input type="hidden" name="cityID" value="${city.ID}">
                                    <input type="hidden" name="cityName" value="${city.cityName}">
                                    <button type="submit" class="btn btn-outline-success btn-rounded"><i class="bi bi-pencil"></i>Додати станцію</button>
                                </form>
                                <c:choose>
                                    <c:when test="${city.relevant.equals(true)}">
                                        <form method="post" action="controller">
                                            <input type="hidden" name="action" value="allCitiesForAdmin">
                                            <input type="hidden" name="requestType" value="setStatus">
                                            <input type="hidden" name="cityID" value="${city.ID}">
                                            <button type="submit" class="btn btn-outline-warning">
                                                Заблокувати
                                            </button>
                                        </form>
                                    </c:when>
                                    <c:otherwise>
                                        <form method="post" action="controller">
                                            <input type="hidden" name="action" value="allCitiesForAdmin">
                                            <input type="hidden" name="requestType" value="setStatus">
                                            <input type="hidden" name="cityID" value="${city.ID}">
                                            <button type="submit" class="btn btn-outline-success">
                                                Розблокувати
                                            </button>
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </div>
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
                            <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${1}">First</a>
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
                            <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${requestScope.currentPage-1}">Previous</a>
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
                                        <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${i}">${i}</a>
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
                                        <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                                <a class="page-link" href = "controller?action=allRoutsForAdmin&page=6">...</a>
                                <c:forEach var="i" begin="${requestScope.countOfPages-4}" end="${requestScope.countOfPages}">
                                    <li class="${requestScope.currentPage != i ? 'page-item' : 'page-item active'}">
                                        <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${i}">${i}</a>
                                    </li>
                                </c:forEach>
                            </c:when>
                            <c:when test="${requestScope.currentPage > 5 && requestScope.currentPage < requestScope.countOfPages-4}">
                                <c:forEach var="i" begin="1" end="5">
                                    <li class="${requestScope.currentPage != i ? 'page-item' : 'page-item active'}">
                                        <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${requestScope.currentPage-5}">...</a>
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
                            <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${requestScope.currentPage+1}">Next</a>
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
                            <a class="page-link" href = "controller?action=allCitiesForAdmin&page=${requestScope.countOfPages}">Last</a>
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
