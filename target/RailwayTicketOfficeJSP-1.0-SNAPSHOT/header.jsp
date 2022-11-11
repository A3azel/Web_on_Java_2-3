<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Header</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="CSS/roundButton.css" rel="stylesheet" type="text/css">
</head>
<body>
<div>
    <header>
        <nav class="navbar navbar-expand-lg navbar navbar-light" style="background-color: #e3f2fd;">
            <div class="container-fluid">
                <a class="navbar-brand" href="index.jsp">
                    <img src="Icons/train-freight-front-fill.svg" alt="" width="30" height="24" class="d-inline-block align-text-top">
                    Головна сторінка
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="controller?action=user">Особистий кабінет</a>
                        </li>
                    </ul>

                    <div class="d-grid gap-3 d-md-flex justify-content-md-end">
                        <c:choose>
                            <c:when test="${sessionScope.username == null}">
                                <form method="get" action="login.jsp">
                                    <button class="btn btn-outline-primary" type="submit">Вхід</button>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form method="get" action="personalOffice.jsp">
                                    <button class="btn btn-outline-warning" type="submit">
                                        Баланс <br> ${sessionScope.user.userCountOfMoney} ₴
                                    </button>
                                </form>
                                <form method="post" action="controller">
                                    <input type="hidden" name="action" value="logout">
                                    <button class="btn btn-outline-primary" type="submit">Вихід</button>
                                </form>
                            </c:otherwise>
                        </c:choose>
                        <form method="get" action="registration.jsp">
                            <button class="btn btn-primary" type="submit">Реєстрація</button>
                        </form>
                    </div>
                </div>
            </div>
        </nav>
    </header>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
</body>
</html>
