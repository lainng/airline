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
    <link href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <title>WW Airlines</title>
</head>
<body>
<jsp:include page="components/header.jsp"/>
<div class="img-wrap">
    <img src="${pageContext.request.contextPath}/assets/plane1.png" height="610" width="1440" alt="plane">
    <div class="img-text">
        <h2><fmt:message key="home.imageHeader"/></h2>
        <p>
            <fmt:message key="home.imageLabel"/>
        </p>
    </div>
</div>
<div class="bg-form-search">
    <div class="row align-content-start col-auto p-4 white-box">
        <div class="col-12 pb-1">
            <h5><fmt:message key="flightSearch.label"/></h5>
        </div>
        <form method="get" action="${pageContext.request.contextPath}/controller" id="flightSearch">
            <div class="d-flex flex-row justify-content-between">
                <div class="d-flex flex-column w-19">
                    <input type="hidden" name="command" value="searching-flight">
                    <select class="form-select py-2 mb-3" id="dept" name="dept">
                        <option selected disabled value=""><fmt:message key="flightSearch.dept"/></option>
                        <c:forEach items="${requestScope.cities}" var="city">
                            <option value="${city.ID}">${city.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-flex flex-column w-19">
                    <select class="form-select py-2 mb-3" id="dest" name="dest">
                        <option disabled selected value=""><fmt:message key="flightSearch.dest"/></option>
                        <c:forEach items="${requestScope.cities}" var="city">
                            <option value="${city.ID}">${city.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="d-flex flex-column w-19">
                    <div class="input-group mb-3" id="fromBlock">
                        <input type="text" class="form-control py-2" id="from" name="deptDate" placeholder="<fmt:message key="flightSearch.outbound"/>" autocomplete="off">
                        <span class="input-group-text" id="fromBtn">
                        <i class="bi bi-calendar-date"></i>
                    </span>
                    </div>
                </div>
                <div class="d-flex flex-column w-19">
                    <div class="input-group mb-3" id="toBlock">
                        <input type="text" class="form-control py-2" id="to" name="destDate" placeholder="<fmt:message key="flightSearch.inbound"/>" autocomplete="off">
                        <span class="input-group-text" id="toBtn">
                            <i class="bi bi-calendar-date"></i>
                    </span>
                    </div>
                </div>
                <div class="d-flex flex-column w-19">
                    <button type="submit" class="btn btn-primary btn-darkblue form-control py-2"><fmt:message key="flightSearch.search"/></button>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="components/footer.jsp"/>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"></script>
<script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/scripts/datepickerLocalization.js"></script>
<script src="${pageContext.request.contextPath}/scripts/changeSelectColor.js"></script>
<script src="${pageContext.request.contextPath}/scripts/datepickerInit.js"></script>
<script src="${pageContext.request.contextPath}/scripts/cityHandler.js"></script>
<script>
    $(document).ready(function () {
        datepickerLocalization('${sessionScope.locale}');
        setMutedColor($('select.form-select'));
        $('#from, #to').prop('readonly', true);
        $('#flightSearch').validate({
            rules: {
                dept: {
                    required: true,
                },
                dest: {
                    required: true,
                },
                deptDate: {
                    validDate: true,
                    required: true,
                },
                destDate: {
                    validDate: true,
                    required: true,
                },
            },
            errorClass: "is-invalid error",
            errorElement: "div",
            errorPlacement: function (error, element) {
                switch (element.attr('name')) {
                    case 'deptDate':
                    case 'destDate': {
                        element.parent().after(error);
                        break;
                    }
                    default: {
                        element.after(error);
                    }
                }
            },
            messages: {
                dept: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                dest: {
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                deptDate: {
                    validDate: "<fmt:message key="validate.validDateMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
                destDate: {
                    validDate: "<fmt:message key="validate.validDateMsg"/>",
                    required: "<fmt:message key="validate.requiredMsg"/>",
                },
            },
        });
        $.validator.addMethod('validDate', function (value) {
            return /^(([0-9]){2}[.]){2}([0-9]){4}$/.test(value);
        });
    });
</script>
</body>
</html>