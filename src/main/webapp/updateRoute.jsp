<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 13.11.2022
  Time: 18:45
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
                <input type="hidden" name="action" value="updateRoute">
                <input type="hidden" name="routeID" value="${requestScope.routeID}">
                <div class="col-lg-6" style="margin: 20px">
                    <div class="card mb-4">
                        <div class="card-body">
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">ID</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.routeID}" readonly="readonly"/>
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
                                    <p class="mb-0">Номер потяга</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.trainNumber}" name="trainNumber"/>
                                    <c:choose>
                                        <c:when test="${requestScope.trainNumberError != null}">
                                            <p style="color: red">${requestScope.trainNumberError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Місто відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.startCityName}" name="startCityName"/>
                                    <c:choose>
                                        <c:when test="${requestScope.startCityNameError != null}">
                                            <p style="color: red">${requestScope.startCityNameError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Станція відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.startStationName}" name="startStationName"/>
                                    <c:choose>
                                        <c:when test="${requestScope.startStationNameError != null}">
                                            <p style="color: red">${requestScope.startStationNameError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Місто прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.arrivalCityName}" name="arrivalCityName"/>
                                    <c:choose>
                                        <c:when test="${requestScope.arrivalCityNameError != null}">
                                            <p style="color: red">${requestScope.arrivalCityNameError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Станція прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.arrivalStationName}" name="arrivalStationName"/>
                                    <c:choose>
                                        <c:when test="${requestScope.arrivalStationNameError != null}">
                                            <p style="color: red">${requestScope.arrivalStationNameError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Дата відправлення</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="datetime-local" class="form-control" value="${requestScope.departureTime}" name="departureTime"/>
                                    <c:choose>
                                        <c:when test="${requestScope.firstDate != null}">
                                            <p style="color: red">${requestScope.firstDate}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Дата прибуття</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="datetime-local" class="form-control" value="${requestScope.arrivalTime}" name="arrivalTime"/>
                                    <c:choose>
                                        <c:when test="${requestScope.secondDate != null}">
                                            <p style="color: red">${requestScope.secondDate}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Кількість місць</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="number" min="0" class="form-control" value="${requestScope.numberOfFreeSeats}" name="numberOfFreeSeats"/>
                                    <c:choose>
                                        <c:when test="${requestScope.freeSeatsError != null}">
                                            <p style="color: red">${requestScope.freeSeatsError}</p>
                                        </c:when>
                                    </c:choose>
                                </div>
                            </div>
                            <hr>
                            <div class="row">
                                <div class="col-sm-4">
                                    <p class="mb-0">Ціна квитка</p>
                                </div>
                                <div class="col-sm-8">
                                    <input type="text" class="form-control" value="${requestScope.priseOfTicket}" name="priseOfTicket"/>
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
                <c:choose>
                    <c:when test="${requestScope.sameRoutesError != null}">
                        <p style="color: red">${requestScope.sameRoutesError}</p>
                    </c:when>
                </c:choose>
                <button type="submit" name="id" class="btn btn-outline-primary">Оновити</button>
            </form>
        </div>
    </main>

    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>

</body>
</html>
