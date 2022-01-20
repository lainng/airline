<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<jsp:useBean id="user" scope="session" class="com.epamtc.airline.entity.User"/>
<jsp:useBean id="STATUS" class="com.epamtc.airline.entity.FlightStatus$Condition"/>

<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="user.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pb-5">
        <h2 class="pt-5"><fmt:message key="user.welcome"/> ${user.firstName}</h2>
        <h5 class="pb-2"><fmt:message key="user.position"/>: ${user.position.name}</h5>
        <h3 class="py-3"><fmt:message key="user.flights"/></h3>
        <table id="flights" class="display text-center">
            <thead>
            <tr>
                <th><fmt:message key="table.number"/></th>
                <th><fmt:message key="table.dept"/></th>
                <th><fmt:message key="table.dest"/></th>
                <th><fmt:message key="table.deptTime"/></th>
                <th><fmt:message key="table.destTime"/></th>
                <th><fmt:message key="table.status"/></th>
                <th><fmt:message key="table.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.flights}" var="flight">
                <tr>
                    <td>${flight.route.ID}</td>
                    <td>${flight.route.departure.name}</td>
                    <td>${flight.route.destination.name}</td>
                    <td><fmt:formatDate value="${flight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <td><fmt:formatDate value="${flight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    <c:choose>
                        <c:when test="${flight.flightStatus.ID eq STATUS.SCHEDULED}">
                            <td class="text-darkorange">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:when test="${flight.flightStatus.ID eq STATUS.READY}">
                            <td class="text-success">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:when test="${flight.flightStatus.ID eq STATUS.CANCELED}">
                            <td class="text-danger">${flight.flightStatus.name}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${flight.flightStatus.name}</td>
                        </c:otherwise>
                    </c:choose>
                    <td>
                        <c:if test="${(flight.flightStatus.ID eq STATUS.READY) or (flight.flightStatus.ID eq STATUS.SCHEDULED)}">
                            <c:if test="${flight.confirmed}">
                                <i data-toggle="tooltip" title="<fmt:message key="user.confirmed"/>" class="bi bi-check-circle link-success mx-2"></i>
                            </c:if>
                            <c:if test="${!flight.confirmed}">
                                <a href="${pageContext.request.contextPath}/controller?command=confirm-flight&flight-id=${flight.ID}" class="text-decoration-none mx-2">
                                    <i class="bi bi-person-plus link-dark" data-toggle="tooltip" title="<fmt:message key="user.confirm"/>"></i>
                                </a>
                            </c:if>
                        </c:if>
                        <a href="${pageContext.request.contextPath}/controller?command=flight-info&flight-id=${flight.ID}" class="text-decoration-none mx-2">
                            <i class="bi bi-info-circle link-dark" data-toggle="tooltip" title="<fmt:message key="user.more"/>"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
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
            "order": [[ 3, "desc" ]],
            columnDefs: [
                { orderable: false, targets: 6 }
            ],
        } );
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
    } );
</script>
</body>
</html>
