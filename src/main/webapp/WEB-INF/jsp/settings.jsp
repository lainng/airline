<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.epamtc.airline.command.UserRole" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="settings.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <form method="post" action="${pageContext.request.contextPath}/controller?command=change-password" id="changePassword">
            <h3 class="text-center pt-5"><fmt:message key="settings.title"/></h3>
            <h5 class="text-center py-3"><fmt:message key="settings.changePassword"/></h5>
            <div class="d-flex flex-column justify-content-between m-auto pb-4 w-35">
                <c:choose>
                    <c:when test="${requestScope.success != null}">
                        <div class="success text-center mb-2"><fmt:message key="${requestScope.success}"/></div>
                    </c:when>
                    <c:when test="${requestScope.error != null}">
                        <div class="error text-center mb-2"><fmt:message key="${requestScope.error}"/></div>
                    </c:when>
                </c:choose>
                <input class="form-control my-2 py-2" type="password" name="password" id="password" placeholder="<fmt:message key="settings.newPassword"/>">
                <input class="form-control my-2 py-2" type="password" name="confirmPassword" id="confirmPassword" placeholder="<fmt:message key="settings.confirmPassword"/>">
                <div class="d-flex flex-row justify-content-between py-5">
                    <c:choose>
                        <c:when test="${sessionScope.roleId eq UserRole.USER}">
                            <a href="${pageContext.request.contextPath}/controller?command=user-page" class="btn btn-primary btn-darkblue py-2 my-2 w-35"><fmt:message key="settings.profileButton"/></a>
                        </c:when>
                        <c:when test="${sessionScope.roleId eq UserRole.DISPATCHER}">
                            <a href="${pageContext.request.contextPath}/controller?command=dispatcher-page" class="btn btn-primary btn-darkblue my-2 w-35"><fmt:message key="settings.profileButton"/></a>
                        </c:when>
                        <c:when test="${sessionScope.roleId eq UserRole.ADMIN}">
                            <a href="${pageContext.request.contextPath}/controller?command=admin-page" class="btn btn-primary btn-darkblue my-2 w-35"><fmt:message key="settings.profileButton"/></a>
                        </c:when>
                    </c:choose>
                    <button type="submit" class="btn btn-primary btn-darkblue py-2 my-2 w-35"><fmt:message key="settings.changeButton"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script>
    $(document).ready(function () {
        $('#changePassword').validate({
            rules: {
                password: {
                    required: true,
                    minlength: 6,
                },
                confirmPassword: {
                    equalTo: "#password",
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                password: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    minlength: "<fmt:message key="validate.minLengthSixMsg"/>",
                },
                confirmPassword: {
                    equalTo: "<fmt:message key="validate.equalToMsg"/>",
                },
            },
        });
    });
</script>
</body>
</html>