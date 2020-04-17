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

        <h2>Użytkownicy:</h2>
        <c:if test="${passReset==true}">
        <div class="users">
            <h3>Hasło zresetowane!</h3>
            <a href="/admin/users" class="btn--small btn--highlighted">OK</a>
        </div>
        </c:if>

        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Aktywny</th>
                <th>Imię i Nazwisko</th>
                <th>Email</th>
                <th colspan="4">Akcja</th>
            </tr>

            <c:forEach items="${users}" var="user">
                <tr>
                    <td><input type="checkbox" disabled id="${user.id}" value="${user.blocked}"
                            <c:if test="${user.blocked == false}"> checked</c:if> /></td>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td><a href="/admin/users/edit/${user.id}" class="btn btn--small btn--highlighted">Edytuj</a> </td>
                    <td><a href="/admin/users/delete/${user.id}" class="btn btn--small btn--highlighted">Usuń</a> </td>
                    <td><a href="/admin/users/active/${user.id}" class="btn btn--small btn--highlighted">
                        <c:if test="${user.blocked == false}">Zablokuj</c:if>
                        <c:if test="${user.blocked == true}">Oblokuj</c:if>
                    </a></td>
                    <td><a href="/admin/users/password/${user.id}" class="btn btn--small btn--highlighted">Resetuj hasło</a>
                    </td>
              </tr>
            </c:forEach>
        </table>
    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
