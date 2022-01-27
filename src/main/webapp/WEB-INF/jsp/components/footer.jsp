<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="ww" uri="WWAirlineTags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.locale != null ? sessionScope.locale : 'ru'}"/>
<fmt:bundle basename="labels"/>

<footer class="mt-auto py-3">
    <div class="container">
        <div class="row justify-content-between">
            <div class="col-4">
                <a href="${pageContext.request.contextPath}/controller?command=home-page">
                    <img src="${pageContext.request.contextPath}/assets/logo-blue.png" width="304" height="55" alt="logo">
                </a>
            </div>
            <div class="col-auto text-white">
                <ww:copyrightTag year="2022" companyName="White Water Airline" locale="${sessionScope.locale}"  bundleKeyName="footer.rights"/>
            </div>
        </div>
    </div>
</footer>
