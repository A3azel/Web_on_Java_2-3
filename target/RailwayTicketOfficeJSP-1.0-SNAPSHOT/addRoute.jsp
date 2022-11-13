<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 12.11.2022
  Time: 21:23
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
            <form method="post" action="controller">
                <input type="hidden" name="action" value="addRoute">
                <div class="col-lg-6" style="margin: 20px">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Номер потяга</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.trainNumber}" name="trainNumber"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.trainNumberError != null}">
                                        <p style="color: red">${requestScope.trainNumberError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Місто відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.startCityName}" name="startCityName"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.startCityNameError != null}">
                                        <p style="color: red">${requestScope.startCityNameError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Станція відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.startStationName}" name="startStationName"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.startStationNameError != null}">
                                        <p style="color: red">${requestScope.startStationNameError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Місто прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.arrivalCityName}" name="arrivalCityName"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.arrivalCityNameError != null}">
                                        <p style="color: red">${requestScope.arrivalCityNameError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Станція прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.arrivalStationName}" name="arrivalStationName"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.arrivalStationNameError != null}">
                                        <p style="color: red">${requestScope.arrivalStationNameError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Дата відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="datetime-local" class="form-control" name="departureTime"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.departureTimeError != null}">
                                        <p style="color: red">${requestScope.departureTimeError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Дата прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="datetime-local" class="form-control" name="arrivalTime"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.arrivalTimeError != null}">
                                        <p style="color: red">${requestScope.arrivalTimeError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Час в дорозі</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="time" class="form-control" name="travelTime"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.travelTimeError != null}">
                                        <p style="color: red">${requestScope.travelTimeError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Кількість місць</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="number" min="0" class="form-control" name="numberOfFreeSeats"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.freeSeatsError != null}">
                                        <p style="color: red">${requestScope.freeSeatsError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Ціна квитка</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" name="priseOfTicket"/>
                                </div>
                                <c:choose>
                                    <c:when test="${requestScope.loginError != null}">
                                        <p style="color: red">${requestScope.loginError}</p>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
                <button type="submit" class="btn btn-outline-success btn-rounded"><i class="bi bi-pencil"></i>Додати</button>
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
