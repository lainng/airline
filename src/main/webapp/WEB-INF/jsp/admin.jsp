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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="admin.title"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="bg-content">
    <div class="container pt-4 pb-5">
        <div class="d-flex flex-column my-2 ps-5 ms-5">
            <h2><fmt:message key="user.welcome"/> ${sessionScope.user.firstName}</h2>
            <h5><fmt:message key="user.position"/>: ${sessionScope.user.position.name}</h5>
            <h3 class="pt-4"><fmt:message key="admin.actions"/>:</h3>
            <a class="link-dark my-2" href="${pageContext.request.contextPath}/controller?command=flights-page"><fmt:message key="admin.flights"/></a>
            <a class="link-dark my-2" href="${pageContext.request.contextPath}/controller?command=routes-page"><fmt:message key="admin.routes"/></a>
            <a class="link-dark my-2" href="${pageContext.request.contextPath}/controller?command=planes-page"><fmt:message key="admin.planes"/></a>
            <a class="link-dark my-2" href="${pageContext.request.contextPath}/controller?command=staff-page"><fmt:message key="admin.staff"/></a>
            <a class="link-dark my-2" href="${pageContext.request.contextPath}/controller?command=crews-page"><fmt:message key="admin.crews"/></a>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
