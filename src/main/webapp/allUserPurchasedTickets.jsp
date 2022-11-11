<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 11.11.2022
  Time: 02:05
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
    <link href="CSS/roundButton.css" rel="stylesheet" type="text/css">
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
                    Номер квитка
                </th>
                <th scope="col">
                    Номер потяга
                </th>
                <th scope="col">Місто <br> Відправлення/Прибуття</th>
                <th scope="col">Станція <br> Відправлення/Прибуття</th>
                <th scope="col">
                    Вартість
                </th>
                <th scope="col">Деталі</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="ticket" items="${requestScope.ticketList}">
                <tr>
                    <td>${ticket.id}</td>
                    <td>${ticket.train}</td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td>
                        <form action="controller" method="get">
                            <input type="hidden" name="action" value="makeOrder">
                            <button type="submit" class="btn btn-outline-info">Інформація</button>
                        </form>
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
</body>
</html>
