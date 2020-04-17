<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Reset password</title>
    <%@include file="../head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Resetuj hasło</h2>
    <div class="users">
        <form:form modelAttribute="resetPassDTO" action="/resetPassword" method="post">

            <div class="form-group">
                <form:errors path="email"/>
            </div>
            <div class="form-group">
                <form:input path="email" placeholder="Podaj email"/>
            </div>

            <div class="form-group">
                <form:button class="btn">Resetuj hasło</form:button>
                <a href="${pageContext.request.contextPath}/login" class="btn--small">Anuluj</a>
            </div>

        </form:form>
    </div>

</section>


</body>
</html>