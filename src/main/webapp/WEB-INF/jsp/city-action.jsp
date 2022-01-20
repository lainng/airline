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
    <link rel="stylesheet" href="https://code.jquery.com/ui/1.13.0/themes/base/jquery-ui.css">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@300;400&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="cityAction.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pb-5">
        <h3 class="text-center pt-5 pb-5"><fmt:message key="cityAction.mainLabel"/></h3>
        <form method="post" action="${pageContext.request.contextPath}/controller?command=add-city" id="city">
            <input type="hidden" name="city-id" value="${requestScope.city.ID}">
            <div class="d-flex flex-column justify-content-between m-auto w-35">
                <label for="city" class="pb-2"><fmt:message key="cityAction.destName"/>:</label>
                <input type="text" class="form-control py-2 mb-2" name="city" value="<c:if test="${requestScope.city != null}">${requestScope.city.name}</c:if>" placeholder="<fmt:message key="cityAction.inputName"/>">
            </div>
            <div class="d-flex flex-row justify-content-between m-auto w-35 py-5">
                <a class="btn btn-primary btn-darkblue w-35" href="${pageContext.request.contextPath}/controller?command=cities-page"><fmt:message key="button.goBack"/></a>
                <button type="submit" class="btn btn-primary btn-darkblue w-35"><fmt:message key="button.save"/></button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script>
    $(document).ready(function () {
        $('#city').validate({
            rules: {
                city: {
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            messages: {
                city: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
    });
</script>
</body>
</html>
