<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Edit User</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>
<header>
    <%@include file="userNav.jsp" %>
</header>
<section class="login-page">
    <h2>Edytuj dane użytkownika</h2>

<form:form modelAttribute="userDTO" action="/user/edit" method="post">

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
    <form:button class="btn btn--small">Zapisz</form:button>
        <a href="${pageContext.request.contextPath}/user" class="btn--small">Anuluj</a>
    </div>

</form:form>



</section>


</body>
</html>
