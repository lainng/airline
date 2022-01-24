<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" import="com.epamtc.airline.command.FlightCondition" %>

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
    <title><fmt:message key="dispatcher.crews.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="py-5">
            <div class="d-flex flex-row justify-content-between pb-4">
                <h3><fmt:message key="dispatcher.crews.mainLabel"/></h3>
                <a href="${pageContext.request.contextPath}/controller?command=crew-action-page" class="btn btn-primary py-2 btn-darkblue"><fmt:message key="dispatcher.crews.newCrew"/></a>
            </div>
            <noscript>
                <div class="noscript text-center mb-4">
                    <fmt:message key="noscript.label"/>
                </div>
            </noscript>
            <c:choose>
                <c:when test="${requestScope.success != null}">
                    <div class="success text-center mb-4"><fmt:message key="${requestScope.success}"/></div>
                </c:when>
                <c:when test="${requestScope.error != null}">
                    <div class="error text-center mb-4"><fmt:message key="${requestScope.error}"/></div>
                </c:when>
            </c:choose>
            <table id="crews" class="display compact text-center">
                <thead>
                <tr>
                    <th><fmt:message key="table.crewID"/></th>
                    <th><fmt:message key="table.flightID"/></th>
                    <th><fmt:message key="table.dept"/></th>
                    <th><fmt:message key="table.plane"/></th>
                    <th><fmt:message key="table.crews"/></th>
                    <th><fmt:message key="table.actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.crews}" var="crew">
                    <tr>
                        <td>${crew.ID}</td>
                        <td>${crew.assignedFlight.ID}</td>
                        <td><fmt:formatDate value="${crew.assignedFlight.departureTime}" pattern="dd.MM.yyyy HH:mm"/></td>
                        <td>${crew.assignedFlight.plane.model}</td>
                        <td class="text-start">
                            <div class="accordion accordion-flush d-none" id="accordionFlush${crew.ID}">
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
                            <noscript>
                                <div class="my-3">
                                    <c:forEach items="${crew.members}" var="employee">
                                        <strong>${employee.position.name}</strong> - ${employee.firstName} ${employee.lastName}<br>
                                    </c:forEach>
                                </div>
                            </noscript>
                        </td>
                        <td>
                            <c:if test="${crew.assignedFlight.flightStatus.ID == FlightCondition.SCHEDULED or crew.assignedFlight.flightStatus.ID == FlightCondition.READY}">
                                <a href="${pageContext.request.contextPath}/controller?command=crew-action-page&flight-id=${crew.assignedFlight.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="button.edit"/>">
                                    <i class="bi bi-pencil link-dark"></i>
                                </a>
                                <noscript>
                                    <a href="${pageContext.request.contextPath}/controller?command=delete-crew&crew-id=${crew.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="tooltip.cancel"/>">
                                        <i class="bi bi-x-circle text-danger mx-2"></i>
                                    </a>
                                </noscript>
                                <a class="text-decoration-none mx-2">
                                    <i class="bi bi-x-circle text-danger d-none" id="${crew.ID}" data-toggle="tooltip" title="<fmt:message key="button.delete"/>" data-bs-toggle="modal" data-bs-target="#deleteModal"></i>
                                </a>
                            </c:if>
                            <a href="${pageContext.request.contextPath}/controller?command=flight-info&flight-id=${crew.assignedFlight.ID}" class="text-decoration-none mx-2" data-toggle="tooltip" title="<fmt:message key="tooltip.seeMore"/>">
                                <i class="bi bi-info-circle link-dark"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modalLabel"><fmt:message key="dispatcher.crews.modal.deletingTitle"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <fmt:message key="dispatcher.crews.modal.deletingBody"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-darkblue" data-bs-dismiss="modal"><fmt:message key="button.goBack"/></button>
                <a href="${pageContext.request.contextPath}/controller?command=delete-crew&crew-id=" id="deleteButtonModal" class="btn btn-danger"><fmt:message key="button.delete"/></a>
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
        $('td > a > i.bi-x-circle, .accordion').toggleClass('d-none');
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $.fn.dataTable.moment('DD.MM.YYYY HH:mm');
        $('#crews').DataTable( {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            order: [[2, 'desc']],
            columns: [
                { "width": "8%" },
                { "width": "8%" },
                null,
                null,
                { "width": "40%" },
                { "width": "10%" },
            ],
            columnDefs: [ {
                "targets": [4, 5],
                "orderable": false
            } ],
        } );

        let path = $('#deleteButtonModal').attr('href');
        $('i.bi-x-circle').click(function () {
            let crewID = $(this).attr('id');
            $('#deleteButtonModal').attr('href', path + crewID);
        });
    } );
</script>
</body>
</html>
