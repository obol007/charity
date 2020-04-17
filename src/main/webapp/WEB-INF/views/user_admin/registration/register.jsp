<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../head.jsp" %>
    <title>Register</title>

</head>
<body>
<%--<%@include file="header.jsp"%>--%>
<header>
    <%@include file="../navbar.jsp" %>
</header>
<section class="login-page">
    <h2>Załóż konto</h2>

    <form:form modelAttribute="userDTO" method="post" action="/register" htmlEscape="true">
        <div class="form-group">
            <form:input path="firstName" placeholder="Imię"/>
            <form:errors path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko"/>
            <form:errors path="lastName"/>
        </div>
        <div class="form-group">
            <form:input path="email" placeholder="Email"/>
            <form:errors path="email"/>
        </div>
        <div class="form-group">
            <form:input type="password" path="password" placeholder="Hasło" htmlEscape="true"/>
            <form:errors path="password"/>
        </div>
        <div class="form-group">
            <input type="password" name="rePassword" placeholder="Powtórz hasło"/>
<%--            <c:if test="${rePassword==false}">Wpisałeś różne hasła</c:if>--%>
            <form:errors path="rePassword"/>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Załóż konto</button>
            <a href="/login" class="btn btn--without-border">Zaloguj się</a>
        </div>

    </form:form>

</section>

<%@include file="../footer.jsp" %>
</body>
</html>
