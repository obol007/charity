<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>ReRegistration</title>
    <%@include file="../head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Poprzedni link aktywacyjny wygasł!
    Wysłaliśmy do Ciebie nowy link</h2>
    <div class="users">
        <a href="${pageContext.request.contextPath}/login" class="btn btn--highlighted">OK</a>
    </div>
</section>

</body>
</html>