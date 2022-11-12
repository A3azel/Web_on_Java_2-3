<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 11.11.2022
  Time: 17:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://example.com/functions" prefix="f" %>
<html>
<head>
    <title>Title</title>
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
        <div class="row mainRow">
            <div class="col-lg-6" style="padding: 20px">
                <div class="card mb-4">
                    <div class="card-body" >
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Номер квитка
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.ID}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Час оформлення
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${f:formatLocalDateTime(requestScope.order.createTime, 'yyyy-MM-dd HH:mm:ss')}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Номер потягу
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.route.train}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Місто <br> відправлення/прибуття
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.route.departureCity} / ${requestScope.order.route.arrivalCity}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Станція відправлення
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.route.startStation}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Станція прибуття</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.route.arrivalStation}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Час відправлення</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${f:formatLocalDateTime(requestScope.order.route.departureTime, 'yyyy-MM-dd HH:mm:ss')}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Час прибуття</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${f:formatLocalDateTime(requestScope.order.route.arrivalTime, 'yyyy-MM-dd HH:mm:ss')}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Час в дорозі</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.route.travelTime}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Кількість придбаних місць</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.countOfPurchasedTickets}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Ціна квитка</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.order.orderPrise} ₴
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
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
