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
    <title><fmt:message key="crews.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="py-5">
            <h2 class="pb-4"><fmt:message key="crews.mainLabel"/></h2>
            <c:if test="${requestScope.employee != null}">
                <h5><strong><fmt:message key="crews.employee"/>:</strong> ${requestScope.employee.firstName} ${requestScope.employee.lastName}</h5>
                <h5 class="pb-4"><strong><fmt:message key="crews.position"/>:</strong> ${requestScope.employee.position.name}</h5>
            </c:if>
            <table id="crews" class="display text-center my-2">
                <thead>
                <tr>
                    <th><fmt:message key="table.crewID"/></th>
                    <th><fmt:message key="table.flightID"/></th>
                    <th><fmt:message key="table.route"/></th>
                    <th><fmt:message key="table.deptTime"/></th>
                    <th><fmt:message key="table.plane"/></th>
                    <th><fmt:message key="table.crews"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.crews}" var="crew">
                    <tr>
                        <td>${crew.ID}</td>
                        <td>${crew.assignedFlight.ID}</td>
                        <td>${crew.assignedFlight.route.number}</td>
                        <td><fmt:formatDate value="${crew.assignedFlight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td>${crew.assignedFlight.plane.model}</td>
                        <td class="text-start">
                            <div class="accordion accordion-flush" id="accordionFlush${crew.ID}">
                                <div class="accordion-item">
                                    <h2 class="accordion-header" id="flush-heading${crew.ID}">
                                        <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#flush-collapse${crew.ID}" aria-expanded="false" aria-controls="flush-collapse${crew.ID}">
                                            <fmt:message key="dispatcher.crews.crew"/>
                                        </button>
                                    </h2>
                                    <div id="flush-collapse${crew.ID}" class="accordion-collapse collapse" aria-labelledby="flush-heading${crew.ID}" data-bs-parent="#accordionFlush${crew.ID}">
                                        <div class="accordion-body">
                                            <p>
                                                <c:forEach items="${crew.members}" var="employee">
                                                    <strong>${employee.position.name}</strong> - ${employee.firstName} ${employee.lastName}<br>
                                                </c:forEach>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
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
        $('#crews').DataTable( {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columns: [
                null,
                null,
                null,
                null,
                null,
                { "width": "40%" },
            ],
            order: [[ 3, "desc" ]],
            columnDefs: [ {
                "targets": 5,
                "orderable": false
            } ],
        } );
    } );
</script>
</body>
</html>
