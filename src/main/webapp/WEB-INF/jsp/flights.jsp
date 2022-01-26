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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="admin.flights.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pb-5">
        <div class="d-flex flex-row justify-content-between pt-5 pb-5">
            <h3><fmt:message key="admin.flights.mainLabel"/></h3>
            <c:choose>
                <c:when test="${requestScope.success != null}">
                    <div class="success text-center w-48"><fmt:message key="${requestScope.success}"/></div>
                </c:when>
                <c:when test="${requestScope.error != null}">
                    <div class="error text-center w-48"><fmt:message key="${requestScope.error}"/></div>
                </c:when>
            </c:choose>
            <a href="${pageContext.request.contextPath}/controller?command=flight-action-page" class="btn btn-primary btn-darkblue py-2"><fmt:message key="admin.flights.addPlane"/></a>
        </div>
        <noscript>
            <div class="noscript text-center mb-4">
                <fmt:message key="noscript.label"/>
            </div>
        </noscript>
        <table id="flights" class="display text-center my-2">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.dept"/></th>
                <th><fmt:message key="table.dest"/></th>
                <th><fmt:message key="table.deptTime"/></th>
                <th><fmt:message key="table.destTime"/></th>
                <th><fmt:message key="table.plane"/></th>
                <th><fmt:message key="table.status"/></th>
                <th><fmt:message key="table.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.flights}" var="flight">
                <tr>
                    <td>${flight.ID}</td>
                    <td>${flight.route.departure.name}</td>
                    <td>${flight.route.destination.name}</td>
                    <td><fmt:formatDate value="${flight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td><fmt:formatDate value="${flight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td>${flight.plane.model}</td>
                    <c:choose>
                        <c:when test="${flight.flightStatus.ID eq FlightCondition.SCHEDULED or flight.flightStatus.ID eq FlightCondition.READY}">
                            <c:if test="${flight.flightStatus.ID eq FlightCondition.SCHEDULED}">
                                <td class="text-darkorange">${flight.flightStatus.name}</td>
                            </c:if>
                            <c:if test="${flight.flightStatus.ID eq FlightCondition.READY}">
                                <td class="text-success">${flight.flightStatus.name}</td>
                            </c:if>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=flight-action-page&flight-id=${flight.ID}" class="text-decoration-none me-1" data-toggle="tooltip" title="<fmt:message key="tooltip.edit"/>">
                                    <i class="bi bi-pencil link-dark"></i>
                                </a>
                                <noscript>
                                    <a href="${pageContext.request.contextPath}/controller?command=cancel-flight&flight-id=${flight.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="tooltip.cancel"/>">
                                        <i class="bi bi-x-circle text-danger mx-2"></i>
                                    </a>
                                </noscript>
                                <a class="text-decoration-none" data-toggle="tooltip" data-bs-toggle="modal" data-bs-target="#cancelModal" title="<fmt:message key="tooltip.cancel"/>">
                                    <i class="bi bi-x-circle text-danger d-none mx-2" id="${flight.ID}"></i>
                                </a>
                                <a href="${pageContext.request.contextPath}/controller?command=flight-info&flight-id=${flight.ID}" class="text-decoration-none ms-1" data-toggle="tooltip" title="<fmt:message key="tooltip.seeMore"/>">
                                    <i class="bi bi-info-circle link-dark"></i>
                                </a>
                            </td>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${flight.flightStatus.ID eq FlightCondition.DEPARTED}">
                                <td class="text-primary">${flight.flightStatus.name}</td>
                            </c:if>
                            <c:if test="${flight.flightStatus.ID eq FlightCondition.CANCELED}">
                                <td class="text-danger">${flight.flightStatus.name}</td>
                            </c:if>
                            <c:if test="${flight.flightStatus.ID eq FlightCondition.ARRIVED}">
                                <td>${flight.flightStatus.name}</td>
                            </c:if>
                            <td>
                                <a href="${pageContext.request.contextPath}/controller?command=flight-info&flight-id=${flight.ID}" class="text-decoration-none" data-toggle="tooltip" title="<fmt:message key="tooltip.seeMore"/>">
                                    <i class="bi bi-info-circle link-dark"></i>
                                </a>
                            </td>
                        </c:otherwise>
                    </c:choose>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<div class="modal fade" id="cancelModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="admin.flights.deleteModalTitle"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <fmt:message key="admin.flights.deleteModalBody"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-darkblue" data-bs-dismiss="modal"><fmt:message key="button.goBack"/></button>
                <a href="${pageContext.request.contextPath}/controller?command=cancel-flight&flight-id=" class="btn btn-danger" id="cancelButtonModal"><fmt:message key="admin.flights.deleteModalButton"/></a>
            </div>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/moment@2.29.1/moment.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script src="https://cdn.datatables.net/plug-ins/1.11.3/sorting/datetime-moment.js"></script>
<script>
    $(document).ready(function() {
        $('td > a > i.bi-x-circle').toggleClass('d-none');
        $.fn.dataTable.moment('DD.MM.YYYY HH:mm');
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $('#flights').DataTable( {
            language: {
                url: localStorage.getItem("tableLang")
            },
            order: [[ 3, "desc" ]],
            columnDefs: [ {
                "targets": 7,
                "orderable": false
            } ],
            columns: [
                { width: "4%" },
                null,
                null,
                null,
                null,
                null,
                null,
                null,
            ],
        } );

        let path = $('#cancelButtonModal').attr('href');
        $('i.bi-x-circle').click(function () {
            let flightID = $(this).attr('id');
            $('#cancelButtonModal').attr('href', path + flightID);
        });
    } );
</script>
</body>
</html>
