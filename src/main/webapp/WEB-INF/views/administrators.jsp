<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="head.jsp" %>

    <title>Administrators</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>


<section>
    <div class="users">
        <a href="/admin/admins/add" class="btn--small btn--highlighted">Dodaj administratora</a>
        <c:if test="${addAdmin==true}">

        <table border="1" cellpadding="3" align="center">
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email*</th>
                <th>Hasło*</th>
                <th>Powtórz hasło</th>
                <th colspan="2">Akcja</th>
            </tr>

            <tr>
                <form:form modelAttribute="adminDTO" method="post" action="/admin/admins/add">
                    <td>
                        <form:input path="firstName" placeholder="Imię" size="15px"/><form:errors path="firstName"/>
                    </td>
                    <td>
                        <form:input path="lastName" placeholder="Nazwisko" size="15px"/><form:errors path="lastName"/>
                    </td>
                    <td>
                        <form:input path="email" placeholder="Email" size="15px"/><form:errors path="email"/>
                    </td>
                    <td>
                        <form:input path="password" placeholder="Hasło" size="15px"/><form:errors path="password"/>
                    </td>
                    <td>
                        <input placeholder="Powtórz hasło" size="15px"/>
                    </td>
                    <td>
                        <button type="submit" class="btn btn--highlighted btn--small">Zapisz</button>
                    </td>
                    <td>
                        <a href="/admin/admins" class="btn btn--highlighted btn--small">Anuluj</a>
                    </td>
                </form:form>
            </tr>
            </c:if>

            <h3>Administratorzy:</h3>
            <table border="1" cellpadding="5" align="center">
                <tr>
                    <th>Imię i Nazwisko</th>
                    <th>Email</th>
                    <th colspan="2">Akcja</th>
                </tr>

                <c:forEach items="${admins}" var="admin">
                    <tr>
                        <td>${admin.fullName}</td>
                        <td>${admin.email}</td>
                        <td><a href="/admin/admins/${admin.id}" class="btn btn--small btn--highlighted">Edytuj</a>
                        <td><a href="/admin/admins/delete/${admin.id}" class="btn btn--small btn--highlighted">Usuń</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
