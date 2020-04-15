<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../user_admin/head.jsp" %>

    <title>Administrators</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<section>
    <div class="users">
        <a href="/admin/admins/add" class="btn--small btn--highlighted">Dodaj administratora</a>

            <h2>Administratorzy:</h2>
            <table border="1" cellpadding="5" align="center">
                <tr>
                    <th>Imię i Nazwisko</th>
                    <th>Email</th>
                    <th colspan="3">Akcja</th>
                </tr>
                <c:forEach items="${admins}" var="admin">
                    <tr <c:if test="${loggedUser.email ==admin.email}"> bgcolor="#5f9ea0"</c:if>>
                        <td>${admin.fullName}</td>
                        <td>${admin.email}</td>
                        <td><a href="/admin/admins/edit/${admin.id}" class="btn btn--small btn--highlighted">Edytuj</a>
                        <td>
                        <a href="/admin/admins/delete/${admin.id}" class="btn btn--small btn--highlighted">Usuń</a>
                        <c:if test="${loggedUser.email ==admin.email}">
                        <td><a href="/admin/password/${admin.id}" class="btn btn--small btn--highlighted">Zmień hasło</a>
                        </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
