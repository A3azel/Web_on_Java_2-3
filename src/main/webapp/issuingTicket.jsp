<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 09.11.2022
  Time: 02:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
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
        <div class="row mainRow">
            <div class="col-lg-6" style="padding: 20px">
                <div class="card mb-4">
                    <div class="card-body" >
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">
                                    Номер потягу
                                </p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.selectedRoute.train}
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
                                   <%-- ${selectedRoute.startStation.city.cityName} / ${selectedRoute.arrivalStation.city.cityName}--%>
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
                                    ${requestScope.selectedRoute.startStation}
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
                                    ${requestScope.selectedRoute.arrivalStation}
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
                                    <%--<fmt:formatDate value="${requestScope.selectedRoute.departureTime}" pattern="yyyy-MM-dd HH:mm:ss" />--%>
                                        ${f:formatLocalDateTime(requestScope.selectedRoute.departureTime, 'yyyy-MM-dd HH:mm:ss')}

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
                                    ${f:formatLocalDateTime(requestScope.selectedRoute.arrivalTime, 'yyyy-MM-dd HH:mm:ss')}
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
                                    ${requestScope.selectedRoute.travelTime}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-4">
                                <p class="mb-0">Кількість вільних місць</p>
                            </div>
                            <div class="col-sm-8">
                                <p class="text-muted mb-0">
                                    ${requestScope.selectedRoute.numberOfFreeSeats}
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
                                    ${requestScope.selectedRoute.priseOfTicket} ₴
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-6">
                <div class="row">
                    <form action="#" method="post">
                        <div class="input-group" style="width: 150px">
                            <!--readonly js-->
                            <label for="countOfTickets">Кількість квитків</label>
                            <button type="button" id="minusCount" class="input-group-text">-</button>
                            <input type="number" class="form-control" id="countOfTickets" name="cityOfDeparture" min="0"/>
                            <button type="button" id="plusCount" class="input-group-text" >+</button>
                        </div>

                        <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                            <button type="submit" class="btn btn-outline-success">Придбати</button>
                        </div>
                    </form>
                </div>
                <div class="row">
                    <label>Сума: </label>
                    <p id="prise">0 ₴</p>
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script th:inline="javascript">
    $('#plusCount').click(plus);
    $('#minusCount').click(minus);
    let count = $('#countOfTickets');
    let res = document.querySelector("#prise");
    count.val(0);

    let numberOfFreeSeats =  '${countOfFreeTickets}';
    let ticketPrice = '${ticketPrise}'

    function plus() {
        if(Number(count.val()) < numberOfFreeSeats){
            count.val(Number($(count).val())+1);
            console.log(Number(count.val())*ticketPrice);
            /*prise.innerHTML = Number(count.val())*ticketPrice;*/
           res.textContent = Number(count.val())*ticketPrice + " ₴";
        }
    }

    function minus() {
        if(count.val() > 0){
            count.val(Number($(count).val())-1);
            res.textContent = Number(count.val())*ticketPrice + " ₴";
        }
    }


</script>
</body>
</html>
