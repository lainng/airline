<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="${requestScope.planeActionPageKey}"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h3 class="text-center py-5"><fmt:message key="${requestScope.planeActionPageKey}"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-plane" id="plane" class="mb-0">
            <input type="hidden" name="plane-id" value="${requestScope.plane.ID}">
            <div class="d-flex flex-column justify-content-center m-auto w-35">
                <c:choose>
                    <c:when test="${requestScope.success != null}">
                        <div class="success text-center mb-4"><fmt:message key="${requestScope.success}"/></div>
                    </c:when>
                    <c:when test="${requestScope.error != null}">
                        <div class="error text-center mb-4"><fmt:message key="${requestScope.error}"/></div>
                    </c:when>
                </c:choose>
                <input type="text" class="form-control my-2 py-2" id="model" name="model" value="<c:if test="${requestScope.plane.model != null}">${requestScope.plane.model}</c:if>" placeholder="<fmt:message key="planeAction.model"/>" autocomplete="off">
                <div class="input-group my-2">
                    <input type="text" class="form-control py-2" id="flyingHours" name="flyingHours" value="<c:if test="${requestScope.plane.flyingHours != null}">${requestScope.plane.flyingHours}</c:if>" placeholder="<fmt:message key="planeAction.flightHours"/>" autocomplete="off">
                    <span class="input-group-text"><fmt:message key="planeAction.hours"/></span>
                </div>
                <div class="input-group my-2">
                    <input type="text" class="form-control py-2" id="passCapacity" name="passCapacity" value="<c:if test="${requestScope.plane.passengerCapacity != null}">${requestScope.plane.passengerCapacity}</c:if>" placeholder="<fmt:message key="planeAction.passengerCapacity"/>" autocomplete="off">
                    <span class="input-group-text"><fmt:message key="planeAction.passengers"/></span>
                </div>
                <div class="input-group my-2">
                    <input type="text" class="form-control py-2" id="flightRange" name="flightRange" value="<c:if test="${requestScope.plane.flightRange != null}">${requestScope.plane.flightRange}</c:if>" placeholder="<fmt:message key="planeAction.flightRange"/>" autocomplete="off">
                    <span class="input-group-text"><fmt:message key="planeAction.kilometers"/></span>
                </div>
            </div>
            <div class="d-flex flex-wrap justify-content-between m-auto w-35 py-5">
                <a class="btn btn-primary btn-darkblue w-35 py-2" type="button" href="${pageContext.request.contextPath}/controller?command=planes-page"><fmt:message key="button.goBack"/></a>
                <button class="btn btn-primary btn-darkblue w-35 py-2" id="submit"><fmt:message key="button.save"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    $(document).ready(function () {
        $('#plane').validate({
            rules: {
                model: {
                    required: true,
                },
                flyingHours: {
                    zeroOrPositive: true,
                    required: true,
                },
                passCapacity: {
                    greaterZero: true,
                    required: true,
                },
                flightRange: {
                    greaterZero: true,
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            errorPlacement: function (error, element) {
                switch (element.attr('name')) {
                    case 'model': {
                        element.after(error);
                        break;
                    }
                    default: {
                        element.parent().after(error);
                    }
                }
            },
            messages: {
                model: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                flyingHours: {
                    zeroOrPositive: "<fmt:message key="validate.zeroOrPositiveMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                passCapacity: {
                    greaterZero: "<fmt:message key="validate.greaterZero"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                flightRange: {
                    greaterZero: "<fmt:message key="validate.greaterZero"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
        $.validator.addMethod('greaterZero', function (value) {
            return /^[1-9][0-9]+$/.test(value);
        });
        $.validator.addMethod('zeroOrPositive', function (value) {
            return /^[0-9]+$/.test(value);
        });
    });
</script>
</body>
</html>
