<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>User</title>
    <%@include file="../user_admin/head.jsp" %>
</head>
<body>


<header class="header--main-page">
    <%@include file="userNav.jsp" %>
    <div class="users">


        <c:if test="${passwordUpdate == true}">
            <h2>Twoje hasło zostało zaktualizowane!</h2>
            <div class="users">
                <a href="${pageContext.request.contextPath}/user" class="btn btn--highlighted">OK</a>
            </div>
        </c:if>

        <c:if test="${detailsUpdate == true}">
            <h2>Twoje dane zostały zaktualizowane!</h2>
            <div class="users">
                <a href="${pageContext.request.contextPath}/user" class="btn btn--highlighted">OK</a>
            </div>
        </c:if>

    </div>
    <div class="slogan container container--90">


        <div class="slogan--item">
            <div class="users">
                <c:if test="${loggedUser.hasImage==true}">
            <img width="200" height="300" src="/upload/${loggedUser.email}">
                </c:if>
            </div>

            <div class="users">
                <form:form method="post" action="/upload" modelAttribute="photoDTO" enctype="multipart/form-data">
                    <c:if test="${loggedUser.hasImage==false}">
                        <div class="users">Dodaj zdjęcie</div>
                    </c:if>
                     <c:if test="${loggedUser.hasImage==true}">
                       <div class="form-group">Zmień zdjęcie</div>
                    </c:if>

                    <div class="users"> <input type="file" name="photo" accept="image/*"></div>
                <div class="users">
                    <form:errors path="*"/>
                <button type="submit" class="btn btn--highlighted">ZAPISZ</button></div>
                </form:form>

            </div>
            <c:if test="${passwordUpdate != true and detailsUpdate != true}">
                <h1>
                    <spring:message code="pages.user.home"/>
                </h1>
            </c:if>
        </div>
    </div>
</header>


</body>
</html>
