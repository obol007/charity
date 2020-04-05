<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="head.jsp" %>

    <title>Login</title>
</head>

<body>
<header>
<%@include file="navbar.jsp"%>
</header>

<section class="login-page">
    <h2><spring:message code="nav.login"/></h2>


    <form method="post" action="/login">
        <div class="form-group">
        <c:if test="${param.error != null}">
            <h3>Invalid credentials!</h3>
        </c:if>
        </div>
        <div class="form-group">
            <input type="email" name="email" placeholder="<spring:message code="login.email"/>" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="<spring:message code="login.pass"/>" />
            <a href="#" class="btn btn--small btn--without-border reset-password"><spring:message code="login.pass.remind"/></a>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit"><spring:message code="nav.login"/></button>
            <a href="/register" class="btn btn--without-border"><spring:message code="nav.register"/></a>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>



</section>

<%@include file="footer.jsp"%>
</body>
</html>
