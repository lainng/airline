<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="login.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container py-3">
        <h3 class="pt-5 pb-4 mb-0 text-center"><fmt:message key="login.enter"/></h3>
        <form class="text-start d-flex flex-column justify-content-center m-auto w-35" method="post" id="login" action="${pageContext.request.contextPath}/controller?command=login">
            <c:if test="${requestScope.error != null}">
                <div class="mb-4 text-center error"><fmt:message key="${requestScope.error}"/></div>
            </c:if>
            <input class="form-control py-2 mb-2" type="email" name="email" id="email" value="<c:if test="${requestScope.email != null}">${requestScope.email}</c:if>" placeholder="<fmt:message key="field.email"/>">
            <input class="form-control py-2 mb-2 mt-2" type="password" name="password" id="password" placeholder="<fmt:message key="field.password"/>">
            <div class="d-flex flex-row justify-content-center pt-4 pb-5">
                <button class="btn btn-primary btn-darkblue py-2 w-33" type="submit"><fmt:message key="login.login"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $('#login').validate({
            rules: {
                email: {
                    required: true,
                    email: true,
                },
                password: {
                    required: true,
                    minlength: 6,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                email: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    email: "<fmt:message key="validate.emailMsg"/>",
                },
                password: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    minlength: "<fmt:message key="validate.minLengthSixMsg"/>",
                },
            },
        })
    })
</script>
</body>
</html>
