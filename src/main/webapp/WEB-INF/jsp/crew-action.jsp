<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>

<c:set var="PILOT" value="1" scope="page"/>
<c:set var="SECOND_PILOT" value="4" scope="page"/>
<c:set var="ATTENDANT" value="2" scope="page"/>
<c:set var="SENIOUR_ATTENDANT" value="3" scope="page"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="${requestScope.crewPageKey}"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="d-flex flex-row justify-content-between pt-4">
            <h3 class="pt-2"><fmt:message key="${requestScope.crewPageKey}"/></h3>
            <form method="post" action="${pageContext.request.contextPath}/controller?command=add-crew" id="crew" class="mb-0">
                <button type="submit" class="btn btn-primary btn-darkblue py-2 my-2"><fmt:message key="crewAction.confirm"/></button>
                <input type="hidden" name="hiddenFlights" class="">
                <input type="hidden" name="hiddenPilots" class="">
                <input type="hidden" name="hiddenAttendants" class="">
                <input type="hidden" name="crew-id" value="${requestScope.crew.ID}">
            </form>
        </div>
        <noscript>
            <div class="noscript text-center mb-4">
                <fmt:message key="noscript.label"/>
            </div>
        </noscript>
        <div id="errContainer" class="text-center pt-2 pb-4">
            <c:choose>
                <c:when test="${requestScope.success != null}">
                    <div class="success">
                        <fmt:message key="${requestScope.success}"/><br>
                        <a href="${pageContext.request.contextPath}/controller?command=dispatcher-crews" class="text-darkblue">
                            <fmt:message key="crewAction.goBack"/>
                        </a>
                    </div>
                </c:when>
                <c:when test="${requestScope.error != null}">
                    <div class="error">
                        <fmt:message key="${requestScope.error}"/>
                    </div>
                </c:when>
            </c:choose>
        </div>
    </div>
    <div class="bg-white">
        <div class="container py-3">

            <h4 class="py-3"><fmt:message key="crewAction.flights"/>:</h4>
            <table id="flights" class="display compact text-center pt-2 mb-2">
                <thead>
                <tr>
                    <th><fmt:message key="table.ID"/></th>
                    <th><fmt:message key="table.dept"/></th>
                    <th><fmt:message key="table.dest"/></th>
                    <th><fmt:message key="table.deptTime"/></th>
                    <th><fmt:message key="table.destTime"/></th>
                    <th><fmt:message key="table.plane"/></th>
                    <th><fmt:message key="table.select"/></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.flights != null}">
                    <c:forEach items="${requestScope.flights}" var="flight">
                        <tr>
                            <td>${flight.ID}</td>
                            <td>${flight.route.departure.name}</td>
                            <td>${flight.route.destination.name}</td>
                            <td><fmt:formatDate value="${flight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                            <td><fmt:formatDate value="${flight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                            <td>${flight.plane.model}</td>
                            <td>
                                <input class="form-check-input radio-btn" type="radio" name="flight-id" value="${flight.ID}" form="crew" <c:if test="${flight.ID == requestScope.currentFlight.ID}">checked</c:if> >
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                <c:if test="${requestScope.crew != null}">
                    <tr>
                        <td>${requestScope.crew.assignedFlight.ID}</td>
                        <td>${requestScope.crew.assignedFlight.route.departure.name}</td>
                        <td>${requestScope.crew.assignedFlight.route.destination.name}</td>
                        <td><fmt:formatDate value="${requestScope.crew.assignedFlight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td><fmt:formatDate value="${requestScope.crew.assignedFlight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td>${requestScope.crew.assignedFlight.plane.model}</td>
                        <td>
                            <input class="form-check-input radio-btn" type="radio" name="flight-id" value="${requestScope.crew.assignedFlight.ID}" form="crew" checked>
                        </td>
                    </tr>
                </c:if>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container py-3">
        <h4 class="pb-3 pt-3"><fmt:message key="crewAction.pilots"/>:</h4>
        <table id="pilots" class="display compact text-center pt-2 mb-2">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.position"/></th>
                <th><fmt:message key="table.lastName"/></th>
                <th><fmt:message key="table.firstName"/></th>
                <th><fmt:message key="table.email"/></th>
                <th><fmt:message key="table.crews"/></th>
                <th><fmt:message key="table.select"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.employees}" var="employee">
                <c:if test="${(employee.position.ID eq PILOT) or (employee.position.ID eq SECOND_PILOT)}">
                    <tr>
                        <td>${employee.ID}</td>
                        <td>${employee.position.name}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.email}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                <i class="bi bi-people text-center link-dark" data-toggle="tooltip" title="<fmt:message key="tooltip.crews"/>"></i>
                            </a>
                        </td>
                        <td>
                            <input type="checkbox" class="form-check-input" name="pilots" value="${employee.ID}" <c:if test="${requestScope.crew.members.contains(employee)}">checked</c:if> form="crew">
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="bg-white">
        <div class="container pt-3 pb-5">
            <h4 class="pb-3 pt-3"><fmt:message key="crewAction.attendants"/>:</h4>
            <table id="attendants" class="display compact text-center pt-2 mb-2">
                <thead>
                <tr>
                    <th><fmt:message key="table.ID"/></th>
                    <th><fmt:message key="table.position"/></th>
                    <th><fmt:message key="table.lastName"/></th>
                    <th><fmt:message key="table.firstName"/></th>
                    <th><fmt:message key="table.email"/></th>
                    <th><fmt:message key="table.crews"/></th>
                    <th><fmt:message key="table.select"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.employees}" var="employee">
                    <c:if test="${(employee.position.ID eq ATTENDANT) or (employee.position.ID eq SENIOUR_ATTENDANT)}">
                        <tr>
                            <td>${employee.ID}</td>
                            <td>${employee.position.name}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.firstName}</td>
                            <td>${employee.email}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                    <i class="bi bi-people text-center link-dark" data-toggle="tooltip" title="<fmt:message key="tooltip.crews"/>"></i>
                                </a>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" name="attendants" value="${employee.ID}" <c:if test="${requestScope.crew.members.contains(employee)}">checked</c:if> form="crew">
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.11.3/sorting/custom-data-source/dom-checkbox.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.11.3/sorting/datetime-moment.js"></script>
<script>
    $(document).ready(function () {
        $.fn.dataTable.moment('DD.MM.YYYY HH:mm');
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });

        let pilots = $('#pilots').dataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderDataType: 'dom-checkbox', targets: [6], type: 'text'},
                { orderable: false, targets: [5] }
            ],
            order: [[ 6, "desc" ]],
        });
        let attendants = $('#attendants').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderDataType: 'dom-checkbox', targets: [6], type: 'text'},
                { orderable: false, targets: [5] }
            ],
            order: [[ 6, "desc" ]],
        });
        let flights = $('#flights').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: 6 }
            ],
            "order": [[ 3, "desc" ]],
        });

        $.validator.addMethod('validFlights', function () {
            let checkedFlag = false;
            flights.$(':radio').each(function () {
                if($(this).is(':checked')) {
                    checkedFlag = true;
                }
            });
            return checkedFlag;
        });
        $.validator.addMethod('validPilots', function () {
            let checkedFlag = false;
            pilots.$(':checkbox').each(function () {
                if($(this).is(':checked')) {
                    checkedFlag = true;
                }
            });
            return checkedFlag;
        });
        $.validator.addMethod('validAttendants', function () {
            let checkedFlag = false;
            attendants.$(':checkbox').each(function () {
                if($(this).is(':checked')) {
                    checkedFlag = true;
                }
            });
            return checkedFlag;
        });

        $('#crew').validate({
            ignore: [],
            focusInvalid: false,
            rules: {
                hiddenFlights: {
                    validFlights: true,
                },
                hiddenPilots: {
                    validPilots: true,
                },
                hiddenAttendants: {
                    validAttendants: true,
                },
            },
            errorClass: "is-invalid error mt-2",
            errorElement: "div",
            errorLabelContainer: "#errContainer",
            messages: {
                hiddenFlights: {
                    validFlights: "<fmt:message key="validate.noFlightMsg"/>",
                },
                hiddenPilots: {
                    validPilots: "<fmt:message key="validate.noPilotMsg"/>",
                },
                hiddenAttendants: {
                    validAttendants: "<fmt:message key="validate.noAttendantMsg"/>",
                },
            },
        });
    })
</script>
</body>
</html>