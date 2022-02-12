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
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="staffAction.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container">
        <h3 class="text-center pt-5 pb-4"><fmt:message key="staffAction.mainLabel"/></h3>
        <form method="post" class="mb-0" action="${pageContext.request.contextPath}/controller?command=edit-employee" id="employee">
            <div class="d-flex flex-column m-auto w-35">
                <c:if test="${requestScope.error != null}">
                    <div class="error text-center mb-4"><fmt:message key="${requestScope.error}"/></div>
                </c:if>
                <input type="hidden" name="user-id" value="${requestScope.employee.ID}">
                <input type="text" class="form-control mb-2 py-2" id="lastName" name="lastName" placeholder="<fmt:message key="staffAction.lastName"/>" value="${requestScope.employee.lastName}" autocomplete="off">
                <input type="text" class="form-control my-2 py-2" id="firstName" name="firstName" placeholder="<fmt:message key="staffAction.firstName"/>" value="${requestScope.employee.firstName}" autocomplete="off">
                <%--<select class="form-select my-2 py-2" id="position" name="position-id">
                    <option selected disabled value=""><fmt:message key="staffAction.choosePosition"/></option>
                    <c:forEach var="position" items="${requestScope.positions}">
                        <option value="${position.ID}" <c:if test="${requestScope.employee.position.ID == position.ID}">selected</c:if> >${position.name}</option>
                    </c:forEach>
                </select>--%>
            </div>
            <div class="d-flex flex-row justify-content-between m-auto w-35 py-5">
                <a class="btn btn-primary btn-darkblue w-35 py-2" type="button" href="${pageContext.request.contextPath}/controller?command=staff-page"><fmt:message key="button.goBack"/></a>
                <button class="btn btn-primary btn-darkblue w-35 py-2" id="submit"><fmt:message key="button.save"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script>
    $(document).ready(function () {
        $('#employee').validate({
            rules: {
                lastName: {
                    required: true,
                },
                firstName: {
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                lastName: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                firstName: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
    });
</script>
</body>
</html>
