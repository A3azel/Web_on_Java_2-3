<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 07.11.2022
  Time: 23:03
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
    <link href="CSS/personalOffice.css" rel="stylesheet" type="text/css">
    <link href="CSS/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp" />
    </header>

    <main>
        <div class="row">
            <div class="col-lg-4">
                <div class="card mb-4">
                    <div class="card-body text-center">
                        <img src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3.webp" alt="avatar"
                             class="rounded-circle img-fluid" style="width: 150px;">
                        <h5 class="my-3">
                            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                        </h5>
                        <p class="text-muted mb-1"  >
                            ${sessionScope.user.userRole}
                        </p>
                        <p class="text-muted mb-4" >
                           ${sessionScope.user.username}
                        </p>
                        <div class="d-flex justify-content-center mb-3">
                        <c:choose>
                            <c:when test="${sessionScope.user.userRole.equals('USER')}">
                                <form method="get" action="controller">
                                    <input type="hidden" name="action" value="userPurchasedTickets">
                                    <button type="submit" class="btn btn-primary">Придбані білети</button>
                                </form>
                            </c:when>
                        </c:choose>
                            <form method="post" action="controller">
                                <input type="hidden" name="action" value="logout">
                                <button type="submit" class="btn btn-outline-primary ms-1">
                                    <i class="bi bi-door-open-fill"></i>
                                    Вийти
                                </button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Нікнейм</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${sessionScope.user.username}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Повне ім'я</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0" >
                                    ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Пошта</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${sessionScope.user.userEmail}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Баланс рахунку</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${sessionScope.user.userCountOfMoney} ₴
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
                <c:choose>
                    <c:when test="${sessionScope.user.userRole.equals('USER')}">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-start">
                            <button type="submit" id="topUpTheAccount"  class="btn btn-outline-secondary">Поповнити рахунок</button>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <form action="controller" method="get">
                            <input type="hidden" name="action" value="allUsers">
                            <button class="btn btn-outline-primary" type="submit" >Список користувачів</button>
                        </form>
                        <form action="controller" method="get">
                            <input type="hidden" name="action" value="allCitiesForAdmin">
                            <button class="btn btn-outline-primary" type="submit">Список міст</button>
                        </form>
                        <form action="controller" method="get">
                            <input type="hidden" name="action" value="allRoutsForAdmin">
                            <button class="btn btn-outline-primary" type="submit" >Список маршрутів</button>
                        </form>
                    </c:otherwise>
                </c:choose>
                <div id="placeToAdd" class="changedMenu">
                    <form method="post" action="controller">
                        <input type="hidden" name="action" value="topUpAccount">
                        <div class="d-grid gap-2 d-md-flex justify-content-md-start" style="padding-top: 20px">
                            <input type="number" min="1" class="form-control" name="countOfMoney" style="border-radius:20px;height:40px;width:200px">
                            <c:choose>
                                <c:when test="${requestScope.moneyError != null}">
                                    <p style="color: red">${requestScope.moneyError}</p>
                                </c:when>
                            </c:choose>
                            <button type="submit" class="btn btn-outline-success"><i class="bi bi-cash-coin"></i>Поповнити</button>
                        </div>
                    </form>
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
<script src="JS/forPersonalOffice.js"></script>
</body>
</html>
