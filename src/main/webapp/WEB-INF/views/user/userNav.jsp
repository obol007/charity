<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<nav class="container container--70" id="navId">


    <ul class="nav--actions">

        <security:authorize access="hasRole('ROLE_USER')">
          <li><b><a href="/user" class="btn btn--without-border">
                  ${loggedUser.fullName}</a></b></li>
        </security:authorize>

        <security:authorize access="hasRole('ROLE_ADMIN')">
           <li><spring:message code="admin.nav.adminPage"/>:<b><a href="#" class="btn btn--without-border">
               <security:authentication property="name"/></a></b></li>
        </security:authorize>

    <security:authorize access="!isAuthenticated()">
            <li><a href="/login" class="btn btn--small btn--without-border"><spring:message code="nav.login"/></a></li>
            <li><a href="/register" class="btn btn--small btn--highlighted"><spring:message code="nav.register"/></a></li>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </security:authorize>

        <li><a href="?lang=en" id="uk" class="btn  btn--small btn--without-border">EN<img src="${pageContext.request.contextPath}/resources/images/uk.svg"/></a></li>
        <li><a href="?lang=pl" class="btn btn--small btn--without-border">PL<img src="${pageContext.request.contextPath}/resources/images/poland.svg"/></a></li>

    </ul>


    <ul>
        <security:authorize access="hasRole('ROLE_ADMIN')">
            <li><a href="/admin" class="btn btn--without-border active" style="background: indianred"><spring:message code="nav.adminPage"/></a></li>
        </security:authorize>

        <li><a href="/user/edit/${loggedUser.id}" class="btn btn--without-border"><spring:message code="nav.users.editDetails"/></a></li>
        <li><a href="/user/password/${loggedUser.id}" class="btn btn--without-border"><spring:message code="nav.users.passowrd"/></a></li>
        <li><a href="/user/donations/${loggedUser.id}" class="btn btn--without-border"><spring:message code="nav.yourDonations"/></a></li>
<%--        <li><a href="/user/sendEmail" class="btn btn--without-border">Wyślij email</a></li>--%>
        <li><a href="/" class="btn btn--without-border"><spring:message code="nav.mainPage"/></a></li>
        <li><a href="/donation" class="btn btn--without-border active">Start</a></li>

        <security:authorize access="hasAnyRole('ROLE_USER','ROLE_ADMIN')">
            <li>
                <form action="<c:url value="/logout"/>" method="post">
                    <input class="btn btn--without-border" type="submit" value="<spring:message code="nav.logout"/>">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                </form>
            </li>
        </security:authorize>
    </ul>
</nav>