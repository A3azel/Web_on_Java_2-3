<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 13.11.2022
  Time: 17:14
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
                            ${requestScope.selectedUser.firstName} ${requestScope.selectedUser.lastName}
                        </h5>
                        <p class="text-muted mb-1"  >
                            ${requestScope.selectedUser.userRole}
                        </p>
                        <p class="text-muted mb-4" >
                            ${requestScope.selectedUser.username}
                        </p>
                        <div class="d-flex justify-content-center mb-3">
                            <c:choose>
                                <c:when test="${requestScope.selectedUser.userRole.equals('USER')}">
                                    <c:choose>
                                        <c:when test="${requestScope.selectedUser.accountVerified.equals(true)}">
                                            <form method="post" action="controller">
                                                <input type="hidden" name="action" value="allUsers">
                                                <input type="hidden" name="userID" value="${requestScope.selectedUser.ID}">
                                                <button type="submit" class="btn btn-outline-danger">
                                                    Заблокувати
                                                </button>
                                            </form>
                                        </c:when>
                                        <c:otherwise>
                                            <form method="post" action="controller">
                                                <input type="hidden" name="action" value="allUsers">
                                                <input type="hidden" name="userID" value="${requestScope.selectedUser.ID}">
                                                <button type="submit" class="btn btn-outline-success">
                                                    Розблокувати
                                                </button>
                                            </form>
                                        </c:otherwise>
                                    </c:choose>
                                </c:when>
                            </c:choose>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card mb-4">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">ID</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${requestScope.selectedUser.ID}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Дата створення</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${f:formatLocalDateTime(requestScope.selectedUser.createTime, 'yyyy-MM-dd HH:mm:ss')}
                                </p>
                            </div>
                        </div>
                        <hr>
                        <div class="row">
                            <div class="col-sm-3">
                                <p class="mb-0">Нікнейм</p>
                            </div>
                            <div class="col-sm-9">
                                <p class="text-muted mb-0">
                                    ${requestScope.selectedUser.username}
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
                                    ${requestScope.selectedUser.firstName} ${requestScope.selectedUser.lastName}
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
                                    ${requestScope.selectedUser.userEmail}
                                </p>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${requestScope.selectedUser.userRole.equals('USER')}">
                                <hr>
                                <div class="row">
                                    <div class="col-sm-3">
                                        <p class="mb-0">Баланс рахунку</p>
                                    </div>
                                    <div class="col-sm-9">
                                        <p class="text-muted mb-0">
                                                ${requestScope.selectedUser.userCountOfMoney} ₴
                                        </p>
                                    </div>
                                </div>
                            </c:when>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </main>

    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
</body>
</html>
