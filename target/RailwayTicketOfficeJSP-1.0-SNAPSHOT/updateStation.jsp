<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 19.11.2022
  Time: 00:29
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
    <link href="CSS/footer.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp" />
    </header>

    <main>
        <div class="row mainRow">
            <form action="controller" method="post">
                <input type="hidden" name="action" value="updateStation">
                <input type="hidden" name="stationID" value="${requestScope.stationID}">
                <div class="col-lg-6" style="margin: 20px">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-4">
                                        <p class="mb-0">ID</p>
                                    </div>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" value="${requestScope.stationID}" readonly="readonly"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <p class="mb-0">Час створення</p>
                                    </div>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="created" value="${requestScope.created}" readonly="readonly"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <p class="mb-0">Час остпннього оновлення</p>
                                    </div>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="updated" value="${requestScope.updated}" readonly="readonly"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <p class="mb-0">Місто</p>
                                    </div>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="cityName" value="${requestScope.cityName}" readonly="readonly"/>
                                    </div>
                                </div>
                                <hr>
                                <div class="row">
                                    <div class="col-sm-4">
                                        <p class="mb-0">Назва станції</p>
                                    </div>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" name="stationName" value="${requestScope.stationName}"/>
                                        <c:choose>
                                            <c:when test="${requestScope.stationError != null}">
                                                <p style="color: red">${requestScope.stationError}</p>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-success btn-rounded"><i class="bi bi-pencil"></i>Редагувати</button>
            </form>
        </div>
    </main>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">
</script>
</body>
</html>
