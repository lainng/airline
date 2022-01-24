<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<%@ page import="com.epamtc.airline.command.UserRole" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>

<div class="container">
    <header class="d-flex flex-row align-items-center justify-content-between py-2">
        <div class="d-flex flex-row align-items-center">
            <a href="${pageContext.request.contextPath}/controller?command=home-page">
                <img src="${pageContext.request.contextPath}/assets/logo-white.png" width="313" height="55" alt="logo">
            </a>
            <a href="${pageContext.request.contextPath}/controller?command=about-company" class="link-dark nav-link ps-5"><fmt:message key="header.aboutUs"/></a>
            <a href="${pageContext.request.contextPath}/controller?command=contacts" class="link-dark nav-link"><fmt:message key="header.contacts"/></a>
        </div>
        <div class="d-flex flex-row w-33 justify-content-between">
            <div class="d-flex flex-row">
                <a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&locale=en" class="nav-link link-dark">EN</a>
                <a href="${pageContext.request.contextPath}/controller?${pageContext.request.queryString}&locale=ru" class="nav-link link-dark">RU</a>
            </div>
            <c:choose>
                <c:when test="${sessionScope.user == null}">
                    <div class="d-flex flex-row">
                        <a href="${pageContext.request.contextPath}/controller?command=login-page" class="btn text-light btn-darkblue me-1"><fmt:message key="header.login"/></a>
                        <a href="${pageContext.request.contextPath}/controller?command=sign-up-page" class="btn text-light btn-darkblue ms-1"><fmt:message key="header.signUp"/></a>
                    </div>
                </c:when>
                <c:when test="${sessionScope.user != null}">
                    <noscript>
                        <div class="d-flex flex-row align-items-center">
                            <c:choose>
                                <c:when test="${sessionScope.roleId == UserRole.USER}">
                                    <a class="nav-link link-dark px-2" href="${pageContext.request.contextPath}/controller?command=user-page"><fmt:message key="header.profile"/></a>
                                </c:when>
                                <c:when test="${sessionScope.roleId == UserRole.DISPATCHER}">
                                    <a class="nav-link link-dark px-2" href="${pageContext.request.contextPath}/controller?command=dispatcher-page"><fmt:message key="header.profile"/></a>
                                </c:when>
                                <c:when test="${sessionScope.roleId == UserRole.ADMIN}">
                                    <a class="nav-link link-dark px-2" href="${pageContext.request.contextPath}/controller?command=admin-page"><fmt:message key="header.profile"/></a>
                                </c:when>
                            </c:choose>
                            <a class="nav-link link-dark px-2" href="${pageContext.request.contextPath}/controller?command=settings"><fmt:message key="header.settings"/></a>
                            <a class="nav-link link-dark px-2" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="header.logout"/></a>
                        </div>
                    </noscript>
                    <button type="button" id="userButton" class="btn btn-primary btn-darkblue dropdown-toggle d-none" data-bs-toggle="dropdown" aria-expanded="false">
                            ${sessionScope.user.firstName} ${sessionScope.user.lastName}
                    </button>
                    <ul class="dropdown-menu">
                        <c:choose>
                            <c:when test="${sessionScope.roleId == UserRole.USER}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=user-page"><fmt:message key="header.profile"/></a></li>
                            </c:when>
                            <c:when test="${sessionScope.roleId == UserRole.DISPATCHER}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=dispatcher-page"><fmt:message key="header.profile"/></a></li>
                            </c:when>
                            <c:when test="${sessionScope.roleId == UserRole.ADMIN}">
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=admin-page"><fmt:message key="header.profile"/></a></li>
                            </c:when>
                        </c:choose>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=settings"><fmt:message key="header.settings"/></a></li>
                        <li><hr class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/controller?command=logout"><fmt:message key="header.logout"/></a></li>
                    </ul>
                </c:when>
            </c:choose>
        </div>
    </header>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script>
    $('#userButton').toggleClass('d-none');
</script>

