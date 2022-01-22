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
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.3/css/jquery.dataTables.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="admin.routes.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="pb-1 bg-content">
    <div class="container pb-5">
        <div class="d-flex flex-row justify-content-between align-content-center py-5">
            <h3><fmt:message key="admin.routes.mainLabel"/></h3>
            <div>
                <a href="${pageContext.request.contextPath}/controller?command=cities-page" class="btn btn-primary btn-darkblue me-2"><fmt:message key="admin.routes.destinations"/></a>
                <a href="${pageContext.request.contextPath}/controller?command=route-action-page" class="btn btn-primary btn-darkblue"><fmt:message key="admin.routes.newRoute"/></a>
            </div>
        </div>
        <noscript>
            <div class="noscript text-center mb-4">
                <fmt:message key="noscript.label"/>
            </div>
        </noscript>
        <table id="routes" class="display text-center">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.dept"/></th>
                <th><fmt:message key="table.dest"/></th>
                <th><fmt:message key="table.duration"/></th>
                <th><fmt:message key="table.distance"/></th>
                <th><fmt:message key="table.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.routes}" var="route">
                <tr>
                    <td>${route.ID}</td>
                    <td>${route.departure.name}</td>
                    <td>${route.destination.name}</td>
                    <td><fmt:formatDate value="${route.duration}" pattern="HH:mm"/></td>
                    <td>${route.distance}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=route-action-page&route-id=${route.ID}">
                            <i class="bi bi-pencil link-dark" data-toggle="tooltip" title="<fmt:message key="tooltip.edit"/>"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
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
        $.fn.dataTable.moment('HH:mm');
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $('#routes').DataTable( {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            "columnDefs": [ {
                "targets": 5,
                "orderable": false
            } ],
        } );
    } );
</script>
</body>
</html>
