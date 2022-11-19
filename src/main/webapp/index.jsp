<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
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
        <div class="modal" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Modal title</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Modal body text goes here.</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="button" class="btn btn-primary">Save changes</button>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row popularDirections" style="padding-bottom: 20px">
                <div class="col-2">
                    <button type="button" value="Київ-Львів" class="btn btn-outline-secondary">Київ <i class="bi bi-arrow-left-right"></i> Львів</button>
                </div>
                <div class="col-2">
                    <button type="button" value="Київ-Харків" class="btn btn-outline-secondary">Київ <i class="bi bi-arrow-left-right"></i> Харків</button>
                </div>
                <div class="col-2">
                    <button type="button" value="Київ-Одесса" class="btn btn-outline-secondary">Київ <i class="bi bi-arrow-left-right"></i> Одесса</button>
                </div>
                <div class="col-2">
                    <button type="button" value="Київ-Дніпро" class="btn btn-outline-secondary">Київ <i class="bi bi-arrow-left-right"></i> Дніпро</button>
                </div>
                <div class="col-2">
                    <button type="button" value="Львів-Одесса" class="btn btn-outline-secondary">Львів <i class="bi bi-arrow-left-right"></i> Одесса</button>
                </div>
                <div class="col-2">
                    <button type="button" value="Дніпро-Львів" class="btn btn-outline-secondary">Дніпро <i class="bi bi-arrow-left-right"></i> Львів</button>
                </div>
            </div>
            <form action="controller" method="get">
                <input type="hidden" name="action" value="trainsBetweenCities">
                <input type="hidden" name="page" value="1">
                <div class="row align-items-center">
                    <div class="col-sm-6">
                        <div class="input-group mb-3">
                            <span class="input-group-text">Звідки</span>
                            <input type="text" class="form-control" id="from" name="cityOfDeparture" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:choose>
                            <c:when test="${requestScope.departureCityError != null}">
                                <p style="color: red">${requestScope.departureCityError}</p>
                            </c:when>
                        </c:choose>
                    </div>
                    <div class="col-sm-6">
                        <div class="input-group mb-3">
                            <span class="input-group-text" >Куди</span>
                            <input type="text" class="form-control" id="where" name="cityOfArrival" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                        <c:choose>
                            <c:when test="${requestScope.arrivalCityError != null}">
                                <p style="color: red">${requestScope.arrivalCityError}</p>
                            </c:when>
                        </c:choose>
                    </div>
                </div>

                <div class="row justify-content-md-center">
                    <div class="col col-lg-2">
                        <label for="startDate" style="padding-bottom:20px">Виберіть дату відправлення</label>
                        <input id="startDate" name="selectedDates" class="form-control" type="date" />
                    </div>
                    <div class="col col-lg-2">
                        <label for="startTime" style="padding-bottom:20px">Виберіть час відправлення</label>
                        <input  id="startTime" name="selectedTime" class="form-control" type="time" />
                    </div>
                </div>

                <div class="form-row text-center" style="padding-top: 20px">
                    <div class="col-12">
                        <c:choose>
                            <c:when test="${requestScope.routeErrors != null}">
                                <p style="color: red">${requestScope.routeErrors}</p>
                            </c:when>
                        </c:choose>
                        <button type="submit" class="btn btn-primary btn-lg" >
                        <span class="icon">
                            <i class="bi bi-cursor-fill"></i>
                        </span> Пошук
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </main>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
<script src="JS/mainWindowLink.js" defer="defer">
</script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
</body>
</html>