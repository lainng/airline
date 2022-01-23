<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.epamtc.airline.command.FlightCondition" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="searchResult.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pb-5">
        <div class="d-flex flex-column justify-content-start pt-5 pb-2">
            <h3 class="pb-4"><fmt:message key="searchResult.mainLabel"/></h3>
            <noscript>
                <div class="noscript text-center mb-4"><fmt:message key="noscript.label"/></div>
            </noscript>
            <div class="row align-content-start col-auto p-4 white-box mt-2 mb-5">
                <form method="get" action="${pageContext.request.contextPath}/controller" id="flightSearch" class="mt-2">
                    <div class="d-flex flex-row justify-content-between">
                        <div class="d-flex flex-column w-19">
                            <input type="hidden" name="command" value="searching-flight">
                            <select class="form-select py-2 mb-2" id="dept" name="dept">
                                <option selected disabled value=""><fmt:message key="searchResult.from"/></option>
                                <c:forEach items="${requestScope.cities}" var="city">
                                    <option value="${city.ID}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-flex flex-column w-19">
                            <select class="form-select py-2 mb-2" id="dest" name="dest">
                                <option disabled selected value=""><fmt:message key="searchResult.to"/></option>
                                <c:forEach items="${requestScope.cities}" var="city">
                                    <option value="${city.ID}">${city.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="d-flex flex-column w-19">
                            <div class="input-group mb-2" id="fromBlock">
                                <input type="text" class="form-control py-2" id="from" name="deptDate" placeholder="<fmt:message key="searchResult.outbound"/>" autocomplete="off">
                                <span class="input-group-text" id="fromBtn">
                                    <i class="bi bi-calendar-date"></i>
                                </span>
                            </div>
                        </div>
                        <div class="d-flex flex-column w-19">
                            <div class="input-group mb-2" id="toBlock">
                                <input type="text" class="form-control py-2" id="to" name="destDate" placeholder="<fmt:message key="searchResult.inbound"/>" autocomplete="off">
                                <span class="input-group-text" id="toBtn">
                                    <i class="bi bi-calendar-date"></i>
                                </span>
                            </div>
                        </div>
                        <div class="d-flex flex-column w-19">
                            <button type="submit" class="btn btn-primary btn-darkblue form-control py-2"><fmt:message key="searchResult.search"/></button>
                        </div>
                    </div>
                    <c:if test="${requestScope.error != null}">
                        <div class="error text-center"><fmt:message key="${requestScope.error}"/></div>
                    </c:if>
                </form>
            </div>
            <table class="display text-center" id="flights">
                <thead>
                <tr>
                    <th><fmt:message key="table.dept"/></th>
                    <th><fmt:message key="table.dest"/></th>
                    <th><fmt:message key="table.deptTime"/></th>
                    <th><fmt:message key="table.destTime"/></th>
                    <th><fmt:message key="table.plane"/></th>
                    <th><fmt:message key="table.status"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.flights}" var="flight">
                    <tr>
                        <td>${flight.route.departure.name}</td>
                        <td>${flight.route.destination.name}</td>
                        <td><fmt:formatDate value="${flight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td><fmt:formatDate value="${flight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td>${flight.plane.model}</td>
                        <c:choose>
                            <c:when test="${flight.flightStatus.ID == FlightCondition.SCHEDULED or flight.flightStatus.ID == FlightCondition.READY}">
                                <td>${requestScope.flightStatus.name}</td>
                            </c:when>
                            <c:otherwise>
                                <td>${flight.flightStatus.name}</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="${pageContext.request.contextPath}/scripts/datepickerLocalization.js"></script>
<script src="${pageContext.request.contextPath}/scripts/changeSelectColor.js"></script>
<script src="${pageContext.request.contextPath}/scripts/flightSearch.js"></script>
<script>
    $(document).ready(function () {
        datepickerLocalization('ru');
        setMutedColor($('select.form-select'));
        $('#from, #to').prop('readonly', true);
        $('#flightSearch').validate({
            rules: {
                dept: {
                    required: true,
                },
                dest: {
                    required: true,
                },
                deptDate: {
                    validDate: true,
                    required: true,
                },
                destDate: {
                    validDate: true,
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            errorPlacement: function (error, element) {
                switch (element.attr('name')) {
                    case 'deptDate':
                    case 'destDate': {
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
                deptDate: {
                    validDate: "<fmt:message key="validate.validDateMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                destDate: {
                    validDate: "<fmt:message key="validate.validDateMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
        $.validator.addMethod('validDate', function (value) {
            return /^(([0-9]){2}[.]){2}([0-9]){4}$/.test(value);
        });
        $('#flights').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            "order": [[ 2, "asc" ]],
        });
    });
</script>
</body>
</html>
