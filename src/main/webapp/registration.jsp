<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 09.11.2022
  Time: 19:04
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
    <link href="CSS/registration.css" rel="stylesheet" type="text/css">
    <link href="CSS/footer.css" rel="stylesheet" type="text/css">
</head>
<body>
<div class="wrapper">
    <header>
        <jsp:include page="header.jsp" />
    </header>

    <main>
        <section class="gradient-custom">
            <div class="container py-5 h-100">
                <div class="row justify-content-center align-items-center h-100">
                    <div class="col-12 col-lg-9 col-xl-7">
                        <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                            <div class="card-body p-4 p-md-5">
                                <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Registration Form</h3>
                                <form method="post" action="controller" id="form">
                                    <input type="hidden" name="action" value="registration">
                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label requiredLable" for="firstName">First Name</label>
                                                <input type="text" id="firstName" class="form-control form-control-lg required" name="firstName"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.firstNameError != null}">
                                                        <p style="color: red">${requestScope.firstNameError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">

                                            <div class="form-outline">
                                                <label class="form-label requiredLable" for="lastName">Last Name</label>
                                                <input type="text" id="lastName" class="form-control form-control-lg required" name="lastName"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.lastNameError != null}">
                                                        <p style="color: red">${requestScope.lastNameError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>

                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4 d-flex align-items-center">
                                            <div class="form-outline datepicker w-100">
                                                <label for="username" class="form-label requiredLable">Username</label>
                                                <input type="text" id="username" class="form-control form-control-lg required" name="username"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.usernameError != null}">
                                                        <p style="color: red">${requestScope.usernameError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4 pb-2">
                                            <div class="form-outline">
                                                <label class="form-label requiredLable" for="emailAddress">Email</label>
                                                <input type="email" id="emailAddress" class="form-control form-control-lg required" name="userEmail"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.emailError != null}">
                                                        <p style="color: red">${requestScope.emailError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label requiredLable" for="password">Password</label>
                                                <input type="password" id="password" class="form-control form-control-lg required" name="password"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.firstPasswordError != null}">
                                                        <p style="color: red">${requestScope.firstPasswordError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                        <div class="col-md-6 mb-4">
                                            <div class="form-outline">
                                                <label class="form-label requiredLable" for="secondPassword">Repeat your password</label>
                                                <input type="password" id="secondPassword" class="form-control form-control-lg required" name="secondPassword"/>
                                                <c:choose>
                                                    <c:when test="${requestScope.secondPasswordError != null}">
                                                        <p style="color: red">${requestScope.secondPasswordError}</p>
                                                    </c:when>
                                                </c:choose>
                                            </div>
                                        </div>
                                    </div>
                                    <c:choose>
                                        <c:when test="${requestScope.passwordsError != null}">
                                            <p style="color: red">${requestScope.passwordsError}</p>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${requestScope.userAlreadyExist != null}">
                                            <p style="color: red">${requestScope.userAlreadyExist}</p>
                                        </c:when>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${requestScope.emailAlreadyExist != null}">
                                            <p style="color: red">${requestScope.emailAlreadyExist}</p>
                                        </c:when>
                                    </c:choose>
                                    <div class="mt-4 pt-2">
                                        <input class="btn btn-primary btn-lg" type="submit" value="Sing up" />
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </main>
    <footer>
        <jsp:include page="footer.jsp" />
    </footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0/dist/js/bootstrap.bundle.min.js">

</script>
</body>
</html>
