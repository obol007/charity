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

    <title>Edit Admin</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<section class="login-page">
    <h2>Edytuj dane administratora</h2>
    <form:form modelAttribute="adminDTO" method="post" action="/admin/admins/edit">
        <form:hidden path="id"/>


        <div class="form-group">
            <form:input path="firstName" placeholder="Imię"/>
            <form:errors path="firstName"/>
        </div>
        <div class="form-group">
            <form:input path="lastName" placeholder="Nazwisko"/>
            <form:errors path="lastName"/>
        </div>

        <div class="form-group">
            <button class="btn" type="submit">Zapisz</button>
            <a href="/admin/admins" class="btn btn--without-border">Anuluj</a>
        </div>
    </form:form>

</section>
</body>
</html>