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
    <title><fmt:message key="${requestScope.routeActionPageKey}"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h3 class="text-center pt-5 pb-4"><fmt:message key="${requestScope.routeActionPageKey}"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-route" id="route" class="mb-0">
            <div class="d-flex flex-column justify-content-between m-auto w-35 mb-2">
                <c:choose>
                    <c:when test="${requestScope.success != null}">
                        <div class="success text-center mb-3"><fmt:message key="${requestScope.success}"/></div>
                    </c:when>
                    <c:when test="${requestScope.error != null}">
                        <div class="error text-center mb-3"><fmt:message key="${requestScope.error}"/></div>
                    </c:when>
                </c:choose>
                <input type="hidden" name="route-id" value="${requestScope.route.ID}">
                <label for="dept"><fmt:message key="routeAction.dept"/>:</label>
                <select class="form-select mb-2 py-2 mt-2" id="dept" name="dept">
                    <option selected disabled value=""><fmt:message key="routeAction.departmentSelect"/></option>
                    <c:forEach items="${requestScope.cities}" var="city">
                        <option value="${city.ID}" <c:if test="${requestScope.route.departure.ID == city.ID}">selected</c:if> >${city.name}</option>
                    </c:forEach>
                </select>
                <label for="dest"><fmt:message key="routeAction.dest"/>:</label>
                <select class="form-select mb-2 py-2 mt-2" id="dest" name="dest">
                    <option selected disabled value=""><fmt:message key="routeAction.destinationSelect"/></option>
                    <c:forEach items="${requestScope.cities}" var="city">
                        <option value="${city.ID}" <c:if test="${requestScope.route.destination.ID == city.ID}">selected</c:if> >${city.name}</option>
                    </c:forEach>
                </select>
                <div class="d-flex flex-row align-items-center justify-content-between mt-2">
                    <label for="duration" class="w-48"><fmt:message key="routeAction.duration"/>:</label>
                    <label for="distance" class="w-48"><fmt:message key="routeAction.distance"/>:</label>
                </div>
                <div class="d-flex flex-row justify-content-between pb-2">
                    <div class="d-flex flex-column w-48">
                        <input type="time" class="form-control text-center py-2 mb-2" id="duration" name="duration" value="<c:if test="${requestScope.route.duration != null}"><fmt:formatDate value="${requestScope.route.duration}" pattern="HH:mm"/></c:if>" autocomplete="off">
                    </div>
                    <div class="d-flex flex-column w-48">
                        <div class="input-group mb-2">
                            <input type="text" class="form-control py-2" id="distance" name="distance" value="<c:if test="${requestScope.route.distance != null}">${requestScope.route.distance}</c:if>" autocomplete="off">
                            <span class="input-group-text"><fmt:message key="routeAction.kilometers"/></span>
                        </div>
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row justify-content-between m-auto w-35 py-5">
                <a class="btn btn-primary btn-darkblue w-35 py-2" href="${pageContext.request.contextPath}/controller?command=routes-page"><fmt:message key="button.goBack"/></a>
                <button class="btn btn-primary btn-darkblue w-35 py-2" id="submit"><fmt:message key="button.create"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="${pageContext.request.contextPath}/scripts/changeSelectColor.js"></script>
<script>
    $(document).ready(function () {
        $('#route').validate({
            rules: {
                dept: {
                    required: true,
                },
                dest: {
                    required: true,
                },
                duration: {
                    required: true,
                },
                distance: {
                    greaterZero: true,
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            errorPlacement: function (error, element) {
                switch (element.attr('name')) {
                    case 'distance': {
                        element.parent().after(error);
                        break;
                    }
                    default: {
                        element.after(error);
                    }
                }
            },
            messages: {
                dept: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                dest: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                duration: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                distance: {
                    greaterZero: "<fmt:message key="validate.greaterZero"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
        hideSelectCity('#dept', '#dest');
        hideSelectCity('#dest', '#dept');

        $.validator.addMethod('greaterZero', function (value) {
            return /^[1-9][0-9]*$/.test(value);
        });
    });

    function hideSelectCity(baseCities, citiesWhereHided) {
        $(baseCities).change(function () {
            let hidedCities = $(citiesWhereHided + " option");
            hidedCities.each(function () {
                $(this).removeClass('d-none');
            });
            let checkedVal = $(baseCities + ' option:checked').val();
            $(citiesWhereHided + ' option[value = ' + checkedVal + ']').addClass('d-none');
        });
    }
</script>
</body>
</html>
