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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="dispatcher.flights.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="d-flex flex-row justify-content-between pt-5 pb-4">
            <h3><fmt:message key="dispatcher.flights.mainLabel"/></h3>
        </div>
        <table id="flights" class="display text-center">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.number"/></th>
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
                    <td>${flight.route.number}</td>
                    <td>${flight.route.departure.name}</td>
                    <td>${flight.route.destination.name}</td>
                    <td><fmt:formatDate value="${flight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td><fmt:formatDate value="${flight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td>${flight.plane.model}</td>
                    <c:choose>
                        <c:when test="${flight.flightStatus.ID eq FlightCondition.SCHEDULED}">
                            <td class="text-darkorange">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:when test="${flight.flightStatus.ID eq FlightCondition.READY}">
                            <td class="text-success">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:when test="${flight.flightStatus.ID eq FlightCondition.CANCELED}">
                            <td class="text-danger">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:when test="${flight.flightStatus.ID eq FlightCondition.DEPARTED}">
                            <td class="text-primary">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${flight.flightStatus.name}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${flight.flightStatus.ID eq FlightCondition.SCHEDULED}">
                            <a href="${pageContext.request.contextPath}/controller?command=crew-action-page&flight-id=${flight.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="dispatcher.flights.assignCrew"/>">
                                <i class="bi bi-person-plus link-dark"></i>
                            </a>
                        </c:if>
                        <c:if test="${flight.flightStatus.ID eq FlightCondition.READY}">
                            <a href="${pageContext.request.contextPath}/controller?command=crew-action-page&flight-id=${flight.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="dispatcher.flights.editCrew"/>">
                                <i class="bi bi-pencil link-dark"></i>
                            </a>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/controller?command=flight-info&flight-id=${flight.ID}" class="text-decoration-none mx-2">
                            <i class="bi bi-info-circle link-dark" data-toggle="tooltip" title="<fmt:message key="dispatcher.flights.more"/>"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pt-4 pb-5">
            <a class="btn text-light btn-darkblue py-2" href="${pageContext.request.contextPath}/controller?command=dispatcher-page"><fmt:message key="button.goBack"/></a>
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
        $.fn.dataTable.moment('DD.MM.YYYY HH:mm');
        $('#flights').DataTable( {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: 7 }
            ],
        } );
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
    } );
</script>
</body>
</html>
