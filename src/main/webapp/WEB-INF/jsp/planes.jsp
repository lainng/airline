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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="admin.planes.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="d-flex flex-row justify-content-between pt-5 pb-4">
            <h3><fmt:message key="admin.planes.mainLabel"/></h3>
            <a href="${pageContext.request.contextPath}/controller?command=plane-action-page" class="btn btn-primary btn-darkblue mb-4 py-2"><fmt:message key="admin.planes.addPlane"/></a>
        </div>
        <table id="planes" class="display text-center my-2">
            <thead>
            <tr>
                <th><fmt:message key="table.ID"/></th>
                <th><fmt:message key="table.model"/></th>
                <th><fmt:message key="table.flightHours"/></th>
                <th><fmt:message key="table.passengerCapacity"/></th>
                <th><fmt:message key="table.flightRange"/></th>
                <th><fmt:message key="table.actions"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.planes}" var="plane">
                <tr>
                    <td>${plane.ID}</td>
                    <td>${plane.model}</td>
                    <td>${plane.flyingHours}</td>
                    <td>${plane.passengerCapacity}</td>
                    <td>${plane.flightRange}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/controller?command=plane-action-page&plane-id=${plane.ID}">
                            <i class="bi bi-pencil link-dark" data-toggle="tooltip" title="<fmt:message key="admin.planes.editTooltip"/>"></i>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="pt-4 pb-5">
            <a class="btn text-light btn-darkblue py-2" href="${pageContext.request.contextPath}/controller?command=admin-page"><fmt:message key="button.goBack"/></a>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
    $(document).ready(function() {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $('#planes').DataTable( {
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
