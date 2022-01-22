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
    <title><fmt:message key="cities.destinations"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <div class="d-flex flex-row justify-content-between align-content-center py-5">
            <h3><fmt:message key="cities.destinations"/></h3>
            <div id="errContainer"></div>
            <div class="d-flex flex-row justify-content-between align-content-center w-25">
                <form action="${pageContext.request.contextPath}/controller?command=add-city" method="post" class="me-2 mb-0" id="city">
                    <input type="text" class="form-control py-2" name="city" placeholder="<fmt:message key="cities.newPoint"/>">
                </form>
                <button type="submit" form="city" class="btn btn-primary btn-darkblue py-2"><fmt:message key="button.add"/></button>
            </div>
        </div>
        <div class="d-flex flex-column justify-content-center w-50 m-auto pb-5">
            <noscript>
                <div class="noscript text-center mb-4">
                    <fmt:message key="noscript.label"/>
                </div>
            </noscript>
            <c:choose>
                <c:when test="${requestScope.success != null}">
                    <div class="success text-center mb-3"><fmt:message key="${requestScope.success}"/></div>
                </c:when>
                <c:when test="${requestScope.error != null}">
                    <div class="error text-center mb-3"><fmt:message key="${requestScope.error}"/></div>
                </c:when>
            </c:choose>
            <table id="cities" class="display text-center">
                <thead>
                <tr>
                    <th><fmt:message key="table.ID"/></th>
                    <th><fmt:message key="table.point"/></th>
                    <th><fmt:message key="table.actions"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.cities}" var="city">
                    <tr>
                        <td>${city.ID}</td>
                        <td>${city.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/controller?command=city-action-page&city-id=${city.ID}" class="text-decoration-none mx-2">
                                <i class="bi bi-pencil link-dark" data-toggle="tooltip" title="<fmt:message key="tooltip.rename"/>"></i>
                            </a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<div class="modal fade" id="cancelModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><fmt:message key="cities.deleteModal.header"/></h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <fmt:message key="cities.deleteModal.body"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary btn-darkblue" data-bs-dismiss="modal"><fmt:message key="button.goBack"/></button>
                <button type="button" class="btn btn-danger"><fmt:message key="button.delete"/></button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.11.3/js/jquery.dataTables.js"></script>
<script>
    $(document).ready(function() {
        $('[data-toggle="tooltip"]').tooltip({
            container: 'table',
        });
        $('#cities').DataTable( {
            language: {
                url: '//cdn.datatables.net/plug-ins/1.11.0/i18n/ru.json'
            },
            columns: [
                { width: "16%" },
                null,
                { width: "22%" },
            ],
        } );
        $('#city').validate({
            rules: {
                city: {
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            errorLabelContainer: "#errContainer",
            messages: {
                city: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
    } );
</script>
</body>
</html>
