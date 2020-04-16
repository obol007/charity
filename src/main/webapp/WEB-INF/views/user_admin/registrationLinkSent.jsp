<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Registration link sent</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Na Twoją pocztę wysłaliśmy link aktywujący konto!</h2>
    <div class="users">
        <a href="${pageContext.request.contextPath}/login" class="btn btn--highlighted">OK</a>
    </div>
</section>

</body>
</html>