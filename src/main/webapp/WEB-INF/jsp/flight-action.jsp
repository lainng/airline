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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="${requestScope.flightActionPageKey}"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h3 class="text-center pt-5 pb-4"><fmt:message key="${requestScope.flightActionPageKey}"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-flight" id="flight" class="mb-0">
            <div class="d-flex flex-column justify-content-between m-auto w-35">
                <input type="hidden" name="flight-id" value="${requestScope.currentFlight.ID}">
                <c:choose>
                    <c:when test="${requestScope.success != null}">
                        <div class="success text-center mb-3"><fmt:message key="${requestScope.success}"/></div>
                    </c:when>
                    <c:when test="${requestScope.error != null}">
                        <div class="error text-center mb-3"><fmt:message key="${requestScope.error}"/></div>
                    </c:when>
                </c:choose>
                <label for="route"><fmt:message key="flightAction.route"/>:</label>
                <select class="form-select mb-2 mt-1 py-2" id="route" name="route-id">
                    <option selected disabled value=""><fmt:message key="flightAction.chooseRoute"/></option>
                    <c:forEach items="${requestScope.routes}" var="route">
                        <c:choose>
                            <c:when test="${requestScope.currentFlight != null}">
                                <option value="${route.ID}" <c:if test="${requestScope.currentFlight.route.ID == route.ID}">selected</c:if> >${route.departure.name} - ${route.destination.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${route.ID}">${route.number}: ${route.departure.name} - ${route.destination.name}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <label for="plane" class="mt-2"><fmt:message key="flightAction.plane"/>:</label>
                <select class="form-select mb-2 mt-1 py-2" id="plane" name="plane-id">
                    <option selected disabled value=""><fmt:message key="flightAction.choosePlane"/></option>
                    <c:forEach items="${requestScope.planes}" var="plane">
                        <c:choose>
                            <c:when test="${requestScope.currentFlight != null}">
                                <option value="${plane.ID}" <c:if test="${requestScope.currentFlight.plane.ID == plane.ID}">selected</c:if> >${plane.model}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${plane.ID}">${plane.model}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
                <div class="d-flex flex-row align-items-center justify-content-between mt-2">
                    <label for="deptDate" class="w-50"><fmt:message key="flightAction.deptDate"/>:</label>
                    <label for="deptTime" class="w-48"><fmt:message key="flightAction.deptTime"/>:</label>
                </div>
                <div class="d-flex flex-row justify-content-between pb-2">
                    <div class="d-flex flex-column w-48">
                        <input type="text" class="form-control py-2 mb-2" id="deptDate" name="deptDate" value="<c:if test="${requestScope.currentFlight != null}"><fmt:formatDate value="${requestScope.currentFlight.departureTime}" pattern="dd.MM.yyyy"/></c:if>" placeholder="<fmt:message key="flightAction.chooseDate"/>" autocomplete="off">
                    </div>
                    <div class="d-flex flex-column w-48">
                        <input type="time" class="form-control text-center py-2 mb-2" id="deptTime" name="deptTime" value="<c:if test="${requestScope.currentFlight != null}"><fmt:formatDate value="${requestScope.currentFlight.departureTime}" pattern="HH:mm"/></c:if>" autocomplete="off">
                    </div>
                </div>
            </div>
            <div class="d-flex flex-row justify-content-between m-auto w-35 py-5">
                <a class="btn btn-primary btn-darkblue w-35 py-2" href="${pageContext.request.contextPath}/controller?command=flights-page"><fmt:message key="button.goBack"/></a>
                <button type="submit" class="btn btn-primary btn-darkblue w-35 py-2"><fmt:message key="button.create"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="${pageContext.request.contextPath}/scripts/datepickerLocalization.js"></script>
<script src="${pageContext.request.contextPath}/scripts/changeSelectColor.js"></script>
<script>
    $(document).ready(function () {
        datepickerLocalization('ru');
        $('#from, #to').prop('readonly', true);
        $('#deptDate').datepicker({
            minDate: +1,
        });
        $('#flight').validate({
            rules: {
                "route-id": {
                    required: true,
                },
                "plane-id": {
                    required: true,
                },
                deptDate: {
                    validDate: true,
                    required: true,
                },
                deptTime: {
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                "route-id": {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                "plane-id": {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                deptDate: {
                    validDate: "<fmt:message key="validate.validDateMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                deptTime: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
        $.validator.addMethod('validDate', function (value) {
            return /^(([0-9]){2}[.]){2}([0-9]){4}$/.test(value);
        });
        setMutedColor($('select.form-select'));
    });
</script>
</body>
</html>
