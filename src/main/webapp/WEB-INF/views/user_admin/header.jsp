<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ page isELIgnored="false" %>

<header class="header--main-page">
<%@include file="navbar.jsp"%>

    <div class="slogan container container--90">
        <div class="slogan--item">
            <h1>
                <spring:message code="pages.header.title"/>
            </h1>
        </div>
    </div>
</header>
