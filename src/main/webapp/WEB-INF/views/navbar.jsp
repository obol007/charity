<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>


<nav class="container container--70">

    <security:authorize access="hasRole('ROLE_USER')">
        <h1>
            <a href="#" class="btn btn--without-border"> ${loggedUser.fullName}</a>
        </h1>
    </security:authorize>

    <security:authorize access="hasRole('ROLE_ADMIN')">
        <h1>
            <a href="#" class="btn btn--without-border"> Administrator: ${loggedUser.email} </a>
        </h1>
    </security:authorize>

    <security:authorize access="!isAuthenticated()">
        <ul class="nav--actions">
            <li><a href="/login" class="btn btn--small btn--without-border">Zaloguj</a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted">Załóż konto</a></li>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </ul>
    </security:authorize>

    <ul>

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin" class="btn btn--without-border active" style="background: indianred"> Strona Administratora </a></li>
        </security:authorize>

        <li><a href="/" class="btn btn--without-border">Strona główna</a></li>
        <li><a href="/donation" class="btn btn--without-border active">Start</a></li>
        <li><a href="/#steps" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="/#about" class="btn btn--without-border">O nas</a></li>
        <li><a href="/#help" class="btn btn--without-border">Fundacje</a></li>
        <li><a href="/#contact" class="btn btn--without-border">Kontakt</a></li>
        <security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
        <li>
            <form action="<c:url value="/logout"/>" method="post">
                <input class="btn btn--without-border" type="submit" value="Wyloguj">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
        </security:authorize>
    </ul>
</nav>