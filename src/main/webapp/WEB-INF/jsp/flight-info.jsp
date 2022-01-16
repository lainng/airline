<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>
<jsp:useBean id="currentFlight" class="com.epamtc.airline.entity.Flight" scope="request"/>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="flightInfo.flight"/> ${currentFlight.route.number}</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h2 class="pb-4 pt-5"><fmt:message key="flightInfo.info"/></h2>
        <div class="row align-content-start">
            <div class="col-4">
                <h3><fmt:message key="flightInfo.plane"/></h3>
                <table class="table">
                    <tbody>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.plane.model"/></th>
                        <td>${currentFlight.plane.model}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.plane.capacity"/></th>
                        <td>${currentFlight.plane.passengerCapacity}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.plane.range"/></th>
                        <td>${currentFlight.plane.flightRange}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.plane.hours"/></th>
                        <td>${currentFlight.plane.flyingHours}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-4">
                <h3><fmt:message key="flightInfo.route"/></h3>
                <table class="table">
                    <tbody>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.number"/></th>
                        <td>${currentFlight.route.number}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.dept"/></th>
                        <td>${currentFlight.route.departure.name}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.dest"/></th>
                        <td>${currentFlight.route.destination.name}</td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.deptTime"/></th>
                        <td><fmt:formatDate value="${currentFlight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.destTime"/></th>
                        <td><fmt:formatDate value="${currentFlight.destinationTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.duration"/></th>
                        <td><fmt:formatDate value="${currentFlight.route.duration}" pattern="HH:mm"/></td>
                    </tr>
                    <tr>
                        <th scope="row"><fmt:message key="flightInfo.route.distance"/></th>
                        <td>${currentFlight.route.distance}</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-4">
                <h3><fmt:message key="flightInfo.crew"/></h3>
                <table class="table">
                    <tbody>
                    <c:choose>
                        <c:when test="${requestScope.notAssignedCrew != null}">
                            <tr>
                                <td><fmt:message key="${requestScope.notAssignedCrew}"/></td>
                            </tr>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${requestScope.crew.members}" var="employee">
                                <tr>
                                    <th scope="row">${employee.position.title}</th>
                                    <td>${employee.lastName} ${employee.firstName}</td>
                                </tr>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="pt-4 pb-5">
            <a class="btn btn-primary btn-darkblue py-2" href="${pageContext.request.contextPath}/controller?command=${requestScope.previousCommand}">Назад</a>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>
