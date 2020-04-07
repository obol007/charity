<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>
<header>
    <%@include file="userNav.jsp" %>
</header>

<c:if test="${detailsUpdate == true}">
    <h2>Twoje dane zostały zaktualizowane!</h2>
    <div class="users">
    <a href="${pageContext.request.contextPath}/user" class="btn btn--highlighted">OK</a>
    </div>
</c:if>

<c:if test="${passwordUpdate == true}">
    <h2>Twoje hasło zostało zaktualizowane!</h2>
    <div class="users">
        <a href="${pageContext.request.contextPath}/user" class="btn btn--highlighted">OK</a>
    </div>
</c:if>


</body>
</html>
