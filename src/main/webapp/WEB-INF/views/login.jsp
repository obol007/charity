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
    <h2>Zaloguj się</h2>
    <form>
        <div class="form-group">
            <input type="email" name="email" placeholder="Email" />
        </div>
        <div class="form-group">
            <input type="password" name="password" placeholder="Hasło" />
            <a href="#" class="btn btn--small btn--without-border reset-password">Przypomnij hasło</a>
        </div>

        <div class="form-group form-group--buttons">
            <button class="btn" type="submit">Zaloguj się</button>
            <a href="#" class="btn btn--without-border">Załóż konto</a>
        </div>
    </form>
</section>

<%@include file="footer.jsp"%>
</body>
</html>
