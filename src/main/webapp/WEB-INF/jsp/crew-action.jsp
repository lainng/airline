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
        <div class="d-flex flex-row justify-content-between py-4">
            <h3 class="pt-2"><fmt:message key="${requestScope.crewPageKey}"/></h3>
            <form method="post" action="${pageContext.request.contextPath}/controller?command=add-crew" id="crew" class="mb-0">
                <button type="submit" class="btn btn-primary btn-darkblue py-2 my-2"><fmt:message key="crewAction.confirm"/></button>
                <input type="hidden" name="hiddenFlights" class="">
                <input type="hidden" name="hiddenPilots" class="">
                <input type="hidden" name="hiddenAttendants" class="">
                <input type="hidden" name="crew-id" value="${requestScope.crew.ID}">
            </form>
        </div>
    </div>
    <div class="bg-white">
        <div class="container py-3">
            <div id="errContainer" class="text-center">
                <c:if test="${requestScope.success != null}">
                    <div class="success"><fmt:message key="${requestScope.success}"/></div>
                </c:if>
            </div>
            <h4 class="py-3"><fmt:message key="crewAction.flights"/>:</h4>
            <table class="display compact text-center" id="flights">
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
                        <td>${requestScope.crew.assignedFlight.route.departure}</td>
                        <td>${requestScope.crew.assignedFlight.route.destination}</td>
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
    <div class="container py-4">
        <h4 class="py-3"><fmt:message key="crewAction.chosenPilots"/>:</h4>
        <table class="display compact text-center" id="chosenPilots">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.position"/></th>
                <th><fmt:message key="table.lastName"/></th>
                <th><fmt:message key="table.firstName"/></th>
                <th><fmt:message key="table.email"/></th>
                <th><fmt:message key="table.crews"/></th>
                <th><fmt:message key="table.delete"/></th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${requestScope.crew != null}">
                <c:forEach items="${requestScope.crew.members}" var="employee">
                    <%--todo заменить числа на константы--%>
                    <c:if test="${(employee.position.ID eq 1) or (employee.position.ID eq 4)}">
                        <tr>
                            <td>${employee.ID}</td>
                            <td>${employee.position.title}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.firstName}</td>
                            <td>${employee.email}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                    <i class="bi bi-people text-center link-dark"></i>
                                </a>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" name="user-id" value="${employee.ID}" form="crew" checked>
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <h4 class="pb-3 pt-5"><fmt:message key="crewAction.pilots"/>:</h4>
        <table id="pilots" class="display compact text-center">
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
                <c:if test="${(employee.position.ID eq 1) or (employee.position.ID eq 4)}">
                    <tr>
                        <td>${employee.ID}</td>
                        <td>${employee.position.title}</td>
                        <td>${employee.lastName}</td>
                        <td>${employee.firstName}</td>
                        <td>${employee.email}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                <i class="bi bi-people text-center link-dark"></i>
                            </a>
                        </td>
                        <td>
                            <input type="checkbox" class="form-check-input" name="user-id" value="${employee.ID}" form="crew">
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="bg-white">
        <div class="container py-4">
            <h4 class="py-3"><fmt:message key="crewAction.chosenAttendants"/>:</h4>
            <table class="display compact text-center" id="chosenAttendants">
                <thead>
                <tr>
                    <th><fmt:message key="table.ID"/></th>
                    <th><fmt:message key="table.position"/></th>
                    <th><fmt:message key="table.lastName"/></th>
                    <th><fmt:message key="table.firstName"/></th>
                    <th><fmt:message key="table.email"/></th>
                    <th><fmt:message key="table.crews"/></th>
                    <th><fmt:message key="table.delete"/></th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${requestScope.crew != null}">
                    <c:forEach items="${requestScope.crew.members}" var="employee">
                        <c:if test="${(employee.position.ID eq 2) or (employee.position.ID eq 3)}">
                            <tr>
                                <td>${employee.ID}</td>
                                <td>${employee.position.title}</td>
                                <td>${employee.lastName}</td>
                                <td>${employee.firstName}</td>
                                <td>${employee.email}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                        <i class="bi bi-people text-center link-dark"></i>
                                    </a>
                                </td>
                                <td>
                                    <input type="checkbox" class="form-check-input" name="user-id" value="${employee.ID}" form="crew" checked>
                                </td>
                            </tr>
                        </c:if>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
            <h4 class="pb-3 pt-5"><fmt:message key="crewAction.attendants"/>:</h4>
            <table id="attendants" class="display compact text-center">
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
                    <c:if test="${(employee.position.ID eq 2) or (employee.position.ID eq 3)}">
                        <tr>
                            <td>${employee.ID}</td>
                            <td>${employee.position.title}</td>
                            <td>${employee.lastName}</td>
                            <td>${employee.firstName}</td>
                            <td>${employee.email}</td>
                            <td>
                                <%--todo tooltip--%>
                                <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}">
                                    <i class="bi bi-people text-center link-dark"></i>
                                </a>
                            </td>
                            <td>
                                <input type="checkbox" class="form-check-input" name="user-id" value="${employee.ID}" form="crew">
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <div class="container">
        <a href="${pageContext.request.contextPath}/controller?command=${requestScope.previousCommand}" class="btn btn-primary btn-darkblue py-2 my-5"><fmt:message key="button.goBack"/></a>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.11.3/sorting/datetime-moment.js"></script>
<script>
    $(document).ready(function () {
        $.fn.dataTable.moment('DD.MM.YYYY HH:mm');
        let chosenPilots = $('#chosenPilots').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: [5, 6] }
            ],
            'paging' : false,
            'info' : false,
            'searching' : false,
        });
        let pilots = $('#pilots').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: [5, 6] }
            ],
        });
        let attendants = $('#attendants').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: [5, 6] }
            ],
        });
        let chosenAttendants = $('#chosenAttendants').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: [5, 6] }
            ],
            'paging' : false,
            'info' : false,
            'searching' : false,
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

        replaceEmplBackForth(pilots, chosenPilots, ':checkbox');
        replaceEmplBackForth(attendants, chosenAttendants, ':checkbox');

        $.validator.addMethod('validFlights', function () {
            let checkedFlag = false;
            flights.$(':radio').each(function () {
                if($(this).is(':checked')) {
                    checkedFlag = true;
                }
            })
            return checkedFlag;
        });
        $.validator.addMethod('validPilots', function () {
            return !(chosenPilots.rows().data().length === 0);
        });
        $.validator.addMethod('validAttendants', function () {
            return !(chosenAttendants.rows().data().length === 0);
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
            errorClass: "is-invalid error",
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
    function replaceEmplBackForth(deptTable, destTable, selector) {
        deptTable.on('click', selector, function () {
            let row = deptTable.row($(this).parents('tr'));
            destTable.row.add(row.data()).draw();
            destTable.$(':not(:checked)').prop("checked", true);
            deptTable.row(row).remove().draw();
        });
        destTable.on('click', selector, function () {
            let row = destTable.row($(this).parents('tr'));
            deptTable.row.add(row.data()).draw();
            deptTable.$(':checked').prop("checked", false);
            destTable.row(row).remove().draw();
        });
    }
</script>
</body>
</html>