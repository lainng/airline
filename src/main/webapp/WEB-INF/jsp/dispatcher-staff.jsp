<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.epamtc.airline.command.UserRole" %>

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
    <title><fmt:message key="dispatcher.staff.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pb-5">
        <h3 class="pt-5 pb-4"><fmt:message key="dispatcher.staff.mainLabel"/></h3>
        <table id="staff" class="display text-center">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.lastName"/></th>
                <th><fmt:message key="table.firstName"/></th>
                <th><fmt:message key="table.position"/></th>
                <th><fmt:message key="table.email"/></th>
                <th><fmt:message key="table.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.employees}" var="employee">
                <tr>
                    <td>${employee.ID}</td>
                    <td>${employee.lastName}</td>
                    <td>${employee.firstName}</td>
                    <td>${employee.position.name}</td>
                    <td>${employee.email}</td>
                    <td>
                        <c:if test="${employee.position.ID eq UserRole.USER}">
                            <a href="${pageContext.request.contextPath}/controller?command=crews-page&user-id=${employee.ID}" class="mx-2">
                                <i class="bi bi-people link-dark text-decoration-none" data-toggle="tooltip" title="<fmt:message key="tooltip.seeMore"/>"></i>
                            </a>
                        </c:if>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function (){
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $('#staff').DataTable({
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columnDefs: [
                { orderable: false, targets: 5 }
            ],
        });
    });
</script>
</body>
</html>
