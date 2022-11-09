<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-gH2yIJqKdNHPEq0n4Mqa/HGKIhSkIHeL5AyhkYV8i59U5AR6csBvApHHNl/vI1Bx" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <link href="CSS/loginStyles.css" rel="stylesheet" type="text/css">
    <link href="CSS/footer.css" rel="stylesheet" type="text/css">
</head>

<body class="gradient-custom">
<%--<header>
        <jsp:include page="header.jsp" />
    </header>--%>
<div class="wrapper">
    <main>
        <div class="logo">
            <!--<img src="https://www.freepnglogos.com/uploads/twitter-logo-png/twitter-bird-symbols-png-logo-0.png" alt="">-->
            <img src="Icons/train-freight-front-fill.svg" alt="">
        </div>
        <div class="text-center mt-4 name">
            RailWay Ticket Office
        </div>
        <form class="p-3 mt-3" action="controller" method="post">
            <input type="hidden" name="action" value="login">
            <div class="form-field d-flex align-items-center">
                <span class="far fa-user"></span>
                <input type="text" name="username" id="username" placeholder="Username">
            </div>
            <div class="form-field d-flex align-items-center">
                <span class="fas fa-key"></span>
                <input type="password" name="password" id="pwd" placeholder="Password">
            </div>
            <c:choose>
                <c:when test="${requestScope.loginError != null}">
                    <p style="color: red">${requestScope.loginError}</p>
                </c:when>
            </c:choose>
            <button class="btn mt-3" type="submit">Login</button>
        </form>
        <div class="text-center fs-6">
            <a href="#">Sing up</a>
        </div>
    </main>
</div>
<%--<footer>
        <jsp:include page="footer.jsp" />
    </footer>--%>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
</body>
</html>
