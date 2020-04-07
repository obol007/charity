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

    <title>Cant delete</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>


<section>
    <div class="users">

        <h2>Nie możesz usunąć samego siebie!</h2>
        <a href="${pageContext.request.contextPath}/admin/admins" class="btn btn--highlighted">OK</a>

    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
