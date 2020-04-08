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

    <title>Confirmation</title>
</head>

<body>
<header>
    <%@include file="userNav.jsp" %>
</header>

<div class="slogan container container--90">
    <h2>
        Dziękujemy za przesłanie formularza.
        Na maila prześlemy wszelkie informacje o odbiorze.
    </h2>
    <div class="users">
        <a href="/user" class="btn btn--highlighted">OK</a>
    </div>
</div>


</body>
</html>
