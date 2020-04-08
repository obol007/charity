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

    <title>Admin Page</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<c:if test="${passwordUpdate == true}">
    <div class="users">
    <h2>Twoje hasło zostało zaktualizowane!</h2>
    <a href="${pageContext.request.contextPath}/admin/admins" class="btn btn--highlighted">OK</a>
    </div>
</c:if>



<c:if test="${showDonations == true}">
    <section>
        <div class="users">
            <h2>Dotacje:</h2>
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
                        <td>${donation.pickUpDate}</td>
                        <td>${donation.pickUpTime}</td>
                        <td>${donation.pickUpComment}</td>
                        <td>${donation.quantity}</td>
                        <td>${donation.institution.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </section>
</c:if>

<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>
