<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Token expired</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Link stracił ważność</h2>
    <div class="users">
        <a href="${pageContext.request.contextPath}/" class="btn btn--highlighted">Strona główna</a>
    </div>
</section>


</body>
</html>