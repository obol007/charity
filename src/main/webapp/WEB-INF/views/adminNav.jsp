<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>


<nav class="container container--70">
    <security:authorize access="hasRole('ROLE_ADMIN')">
        <h1>  <a href="#" class="btn btn--without-border"> Administrator: ${loggedUser.email} </a></h1>
    </security:authorize>
    <ul>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin" class="btn btn--without-border active" style="background: indianred"> Strona Administratora </a>
            </li>
        </security:authorize>

        <li><a href="/" class="btn btn--without-border">Strona główna</a></li>
        <li><a href="/admin/users" class="btn btn--without-border">Użytkownicy</a></li>
        <li><a href="/admin/admins" class="btn btn--without-border">Administratorzy</a></li>
        <li><a href="/admin/institutions" class="btn btn--without-border">Fundacje</a></li>
        <li><a href="/admin/donations" class="btn btn--without-border">Dotacje</a></li>
        <li>
            <form action="<c:url value="/logout"/>" method="post">
                <input class="btn btn--without-border" type="submit" value="Wyloguj">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </ul>
</nav>