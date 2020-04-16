<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>New password</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Utwórz nowe hasło</h2>
<div class="users">
    <form:form modelAttribute="resetPassDTO" action="/resetPassword/newPassword" method="post">
        <form:hidden path="email"/>

        <div class="form-group">
            <form:errors path="password"/>
       </div>
        <div class="form-group">
         <form:password path="password" placeholder="Nowe hasło"/>
        </div>

        <div class="form-group">
            <form:errors path="rePassword"/>
        </div>
        <div class="form-group">
            <form:password path="rePassword" placeholder="Powtórz hasło"/>
        </div>

        <div class="form-group">
            <form:button class="btn btn--small">Zapisz</form:button>
            <a href="${pageContext.request.contextPath}/login" class="btn--small">Anuluj</a>
        </div>

    </form:form>
</div>

</section>


</body>
</html>
