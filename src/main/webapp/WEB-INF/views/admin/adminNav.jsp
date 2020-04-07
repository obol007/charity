<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>




<nav class="container container--70">
<%--    <h1><a href="#" class="btn btn--without-border"> <spring:message code="admin.nav.mainPage"/>:</a> <security:authentication property="name"/> </a></h1>--%>

    <ul class="nav--actions">

        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><spring:message code="admin.nav.adminPage"/>:<b><a href="#" class="btn btn--without-border">
                <security:authentication property="name"/></a></b></li>
        </security:authorize>


        <li><a href="?lang=en" id="uk" class="btn  btn--small btn--without-border">EN<img src="${pageContext.request.contextPath}/resources/images/uk.svg"/></a></li>
        <li><a href="?lang=pl" class="btn btn--small btn--without-border">PL<img src="${pageContext.request.contextPath}/resources/images/poland.svg"/></a></li>

    </ul>

    <ul>
        <li><a href="/admin" class="btn btn--without-border active" style="background: cadetblue">
            <spring:message code="admin.nav.adminPage"/> </a></li>
        <li><a href="/" class="btn btn--without-border"><spring:message code="admin.nav.mainPage"/></a></li>
        <li><a href="/admin/users" class="btn btn--without-border"><spring:message code="admin.nav.users"/></a></li>
        <li><a href="/admin/admins" class="btn btn--without-border"><spring:message code="admin.nav.admins"/></a></li>
        <li><a href="/admin/institution" class="btn btn--without-border"><spring:message code="admin.nav.institutions"/></a></li>
        <li><a href="/admin/donations" class="btn btn--without-border"><spring:message code="admin.nav.donations"/></a></li>
        <li><a href="/admin/messages" class="btn btn--without-border"><spring:message code="admin.nav.messages"/></a></li>
        <li>
            <form action="<c:url value="/logout"/>" method="post">
                <input class="btn btn--without-border" type="submit" value="<spring:message code="nav.logout"/>">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </li>
    </ul>
</nav>