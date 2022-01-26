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
    <title><fmt:message key="signUp.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h3 class="pt-5 pb-4 mb-0 text-center"><fmt:message key="signUp.account"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/controller?command=sign-up" id="signUp" class="mb-0">
            <div class="d-flex flex-column m-auto w-35">
                <c:if test="${requestScope.error != null}">
                    <div class="mb-4 text-center error"><fmt:message key="${requestScope.error}"/></div>
                </c:if>
                <div class="d-flex flex-row justify-content-between">
                    <div class="d-flex flex-column w-48">
                        <input class=" mb-2 ps-2 py-2 form-control" type="text" name="lastName" id="lastName" value="<c:if test="${requestScope.lastName != null}">${requestScope.lastName}</c:if>" placeholder="<fmt:message key="signUp.lastName"/>">
                    </div>
                    <div class="d-flex flex-column w-48">
                        <input class=" mb-2 ps-2 py-2 form-control" type="text" name="firstName" id="firstName" value="<c:if test="${requestScope.firstName != null}">${requestScope.firstName}</c:if>" placeholder="<fmt:message key="signUp.firstName"/>">
                    </div>
                </div>
                <select class="mb-2 mt-2 ps-2 py-2 form-select" name="position-id" id="position">
                    <option disabled selected value=""><fmt:message key="signUp.position"/></option>
                    <c:forEach var="position" items="${requestScope.positions}">
                        <option value="${position.ID}" <c:if test="${requestScope.position == position.ID}">selected</c:if> >${position.name}</option>
                    </c:forEach>
                </select>
                <input class="mb-2 mt-2 ps-2 py-2 form-control" type="email" name="email" id="email" value="<c:if test="${requestScope.email != null}">${requestScope.email}</c:if>" placeholder="<fmt:message key="field.email"/>">
                <input class="mb-2 mt-2 ps-2 py-2 form-control" type="password" name="password" id="password" placeholder="<fmt:message key="field.password"/>">
                <input class="mb-2 mt-2 ps-2 py-2 form-control" type="password" name="confirmPassword" id="confirmPassword" placeholder="<fmt:message key="field.confirmPass"/>">
            </div>
            <div class="d-flex justify-content-center m-auto w-50">
                <button type="submit" id="submit" class="btn btn-primary btn-darkblue my-5 py-2 w-50"><fmt:message key="signUp.signUp"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
<script src="${pageContext.request.contextPath}/scripts/changeSelectColor.js"></script>
<script>
    $(document).ready(function () {
        $('#signUp').validate({
            rules: {
                lastName: {
                    validNames: true,
                    required: true,
                    minlength: 2,
                },
                firstName: {
                    validNames: true,
                    required: true,
                    minlength: 2,
                },
                position: {
                    required: true,
                },
                email: {
                    required: true,
                    email: true,
                },
                password: {
                    required: true,
                    minlength: 6,
                },
                confirmPassword: {
                    equalTo: "#password"
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                lastName: {
                    validNames: "<fmt:message key="validate.validNamesMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    minlength: "<fmt:message key="validate.minLengthTwoMsg"/>",
                },
                firstName: {
                    validNames: "<fmt:message key="validate.validNamesMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    minlength: "<fmt:message key="validate.minLengthTwoMsg"/>",
                },
                position: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                email: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    email: "<fmt:message key="validate.emailMsg"/>",
                },
                password: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                    minlength: "<fmt:message key="validate.minLengthSixMsg"/>",
                },
                confirmPassword: {
                    equalTo: "<fmt:message key="validate.equalToMsg"/>",
                },
            },
        });
        $.validator.addMethod('validNames', function (value) {
            return /^([A-ZА-Я][-,a-zа-я. ']+)+/.test(value);
        });
        setMutedColor($('select.form-select'));
    })
</script>
</body>
</html>
