<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

        <%--<div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1" aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2" aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img th:src="@{/Images/img.png}" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>First slide label</h5>
                        <p>Some representative placeholder content for the first slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img th:src="@{/Images/img_2.png}" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Second slide label</h5>
                        <p>Some representative placeholder content for the second slide.</p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img th:src="@{/Images/img_2.png}" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>Third slide label</h5>
                        <p>Some representative placeholder content for the third slide.</p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>--%>


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
            <form action="controller?action=trainsBetweenCities" method="get">
                <div class="row align-items-center">
                    <div class="col-sm-6">
                        <div class="input-group mb-3">
                            <span class="input-group-text">Звідки</span>
                            <input type="text" class="form-control" id="from" name="cityOfDeparture" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                    </div>
                    <c:choose>
                        <c:when test="${requestScope.departureCityError != null}">
                            <p style="color: red">${requestScope.departureCityError}</p>
                        </c:when>
                    </c:choose>

                    <div class="col-sm-6">
                        <div class="input-group mb-3">
                            <span class="input-group-text" >Куди</span>
                            <input type="text" class="form-control" id="where" name="cityOfArrival" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
                        </div>
                    </div>
                    <%--<c:choose>
                        <c:when test="${requestScope.arrivalCityError != null}">
                            <p style="color: red">${requestScope.arrivalCityError}</p>
                        </c:when>
                    </c:choose>--%>
                </div>
                <c:choose>
                    <c:when test="${requestScope.routeErrors != null}">
                        <p style="color: red">${requestScope.routeErrors}</p>
                    </c:when>
                </c:choose>
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
    <a href="login.jsp">login</a>

    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
<script src="JS/mainWindowLink.js" defer="defer"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">
</script>
</body>
</html>