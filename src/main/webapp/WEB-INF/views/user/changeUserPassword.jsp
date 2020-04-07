<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Change password</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>
<header>
    <%@include file="userNav.jsp" %>
</header>
<section class="login-page">
    <h2>Zmień hasło</h2>

    <form:form modelAttribute="userDTO" action="/user/password" method="post">

        <form:hidden path="id"/>
        <form:hidden path="firstName"/>
        <form:hidden path="lastName"/>
        <form:hidden path="password"/>

        <div class="form-group">
            <form:password path="oldPassword" placeholder="Stare hasło"/>
            <form:errors path="oldPassword"/>
        </div>
        <div class="form-group">
            <form:password path="newPassword" placeholder="Nowe hasło"/>
            <form:errors path="newPassword"/>
        </div>
        <div class="form-group">
            <form:password path="reNewPassword" placeholder="Powtórz hasło"/>
            <form:errors path="reNewPassword"/>
        </div>

        <div class="form-group">
            <form:button class="btn btn--small">Zapisz</form:button>
            <a href="${pageContext.request.contextPath}/user" class="btn--small">Anuluj</a>
        </div>

    </form:form>


</section>


</body>
</html>
