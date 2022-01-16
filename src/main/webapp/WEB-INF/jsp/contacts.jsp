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
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title><fmt:message key="contacts.contacts"/></title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<!-- Contacts -->
<div class="bg-content">
    <div class="container py-5">
        <div class="ps-5 ms-5">
            <h2 class="pb-4"><fmt:message key="contacts.contacts"/></h2>
            <h4 class="pb-1"><fmt:message key="contacts.contactsCenter"/></h4>
            <p>
                +421(79)568-98-98, +421(88)468-98-98
            </p>
            <p>
                <fmt:message key="contacts.freeInBelarus"/>: 8 421 768-98-98
            </p>
            <p class="pt-3">
                <fmt:message key="contacts.freeNumbers"/>:
            </p>
            <ul>
                <li><fmt:message key="contacts.Germany"/>: 0800–421–9898</li>
                <li><fmt:message key="contacts.HongKong"/>: 0852–421–85769</li>
                <li><fmt:message key="contacts.Spain"/>: 900-421-134</li>
                <li><fmt:message key="contacts.Italy"/>: 800–421–564</li>
                <li><fmt:message key="contacts.China"/>: 900-421-908</li>
                <li><fmt:message key="contacts.Taiwan"/>: 900-421-963</li>
                <li><fmt:message key="contacts.Japan"/>: 900-421-567</li>
            </ul>
            <a href="${pageContext.request.contextPath}/controller?command=home-page" class="btn btn-primary btn-darkblue mt-4"><fmt:message key="button.toHome"/></a>
        </div>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</body>
</html>
