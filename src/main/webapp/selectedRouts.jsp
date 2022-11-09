<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 08.11.2022
  Time: 23:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
    <title>all trains</title>
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
                    <p>
                        Номер потяга
                    </p>
                </th>
                <th scope="col">
                    Станція <br/> відправлення/прибуття
                </th>
                <th scope="col">
                    <p>
                        Дата <br/> відправлення/прибуття
                    </p>
                </th>
                <th scope="col">
                    <p>
                        Час в дорозі
                    </p>
                </th>
                <th scope="col">
                    <p>
                        Кількість вільних місць
                    </p>
                </th>
                <th scope="col">
                    <p>
                        Ціна квитка
                    </p>
                </th>
                <th scope="col">
                    <p>
                        Придбати
                    </p>
                </th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="route" items="${requestScope.routeList}">
                <tr>
                    <td >
                        ${route.train}
                    </td>
                    <td>
                            ${route.startStation}/${route.arrivalStation}
                    </td>
                    <td>
                        <fmt:formatDate value="${route.departureTime}" pattern="yyyy-MM-dd HH:mm:ss" /> <br>
                        <fmt:formatDate value="${route.arrivalTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                    </td>

                    <td>
                            ${route.travelTime}
                    </td>
                    <td>
                            ${route.numberOfFreeSeats}
                    </td>
                    <td>
                            ${route.priseOfTicket}
                    </td>
                    <td>
                        <div class="container" style="width:250px; text-align:end;">
                            <div class="row">
                                <div class="col">
                                    <form action="controller" method="get">
                                        <input type="hidden" name="action" value="order">
                                        <input type="hidden" name="id" value="${route.ID}">
                                        <button type="submit" class="btn btn-outline-primary">Оформити квиток</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
