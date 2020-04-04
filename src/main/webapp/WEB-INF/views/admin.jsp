<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="head.jsp" %>

    <title>Admin Page</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<c:if test="${showUsers == true}">
<section>
    <div class="users">
        <h3>Użytkownicy:</h3>
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Imię i Nazwisko</th>
                <th>Email</th>
                <th>Liczba dotacji</th>
                <th>Liczba worków</th>
                <th>Szczegóły</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.fullName}</td>
                    <td>${user.email}</td>
                    <td>0</td>
                    <td>0</td>
                    <td><a href="/admin/users/${user.id}" class="btn btn--small btn--highlighted">Szczegóły</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</c:if>

<c:if test="${showAdmins == true}">
<section>
    <div class="users">
        <h3>Administratorzy:</h3>
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Imię i Nazwisko</th>
                <th>Email</th>
                <th>Szczegóły</th>
            </tr>
            <c:forEach items="${admins}" var="admin">
                <tr>
                    <td>${admin.fullName}</td>
                    <td>${admin.email}</td>
                    <td><a href="/admin/admins/${admin.id}" class="btn btn--small btn--highlighted">Szczegóły</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</c:if>

<c:if test="${showInstitutions == true}">
<section>
    <div class="users">
        <h3>Fundacje:</h3>
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th colspan="2">Aktywna</th>
                <th>Nazwa</th>
                <th>Opis</th>
                <th colspan="2">Akcja</th>
            </tr>
            <c:forEach items="${institutions}" var="institution">
                <tr>
                    <td><input type="checkbox" disabled id="${institution.id}" value="${institution.active}"
                            <c:if test="${institution.active == true}"> checked</c:if> /></td>
                    <td><a href="/admin/institutions/active/${institution.id}" class="btn btn--small btn--highlighted">Zmień</a></td>
                    <td>${institution.name}</td>
                    <td>${institution.description}</td>
                    <td><a href="/admin/institutions/${institution.id}" class="btn btn--small btn--highlighted">Edytuj</a></td>
                    <td><a href="/admin/institutions/${institution.id}" class="btn btn--small btn--highlighted">Usuń</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>
</c:if>
<c:if test="${showDonations == true}">
    <section>
        <div class="users">
            <h3>Dotacje:</h3>
            <table border="1" cellpadding="5" align="center">
                <tr>
                    <th>Imię i Nazwisko</th>
                    <th>Adres odbioru</th>
                    <th>Data odbioru</th>
                    <th>Czas odbioru</th>
                    <th>Komentarz</th>
                    <th>Liczba worków</th>
                    <th>Beneficjent</th>
                </tr>
                <c:forEach items="${donations}" var="donation">
                    <tr>
                        <td>${donation.user.fullName}</td>
                        <td>${donation.city}, ${donation.street}, ${donation.zipCode}</td>
<%--                        <td>${donation.street +' '+ donation.city+' '+donation.zipCode}/></td>--%>
                        <td>${donation.pickUpDate}</td>
                        <td>${donation.pickUpTime}</td>
                        <td>${donation.pickUpComment}</td>
                        <td>${donation.quantity}</td>
                        <td>${donation.institution.name}</td>
<%--                     <td><a href="/admin/users/${user.id}" class="btn btn--small btn--highlighted">Szczegóły</a></td>--%>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</c:if>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
