<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../user_admin/head.jsp" %>

    <title>Password Updated</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<section>
    <div class="users">

        <c:if test="${passwordUpdate == true}">
            <div class="users">
                <h2>Twoje hasło zostało zaktualizowane!</h2>
                <a href="${pageContext.request.contextPath}/admin/admins" class="btn btn--highlighted">OK</a>
            </div>
        </c:if>
    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
