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
<header>
    <%@include file="userNav.jsp" %>
</header>


<section>
    <div class="users">
        <h2>Dotacje:</h2>
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Dary</th>
                <th>Liczba worków</th>
                <th>Adres odbioru</th>
                <th>Data odbioru</th>
                <th>Czas odbioru</th>
                <th>Komentarz</th>
                <th>Beneficjent</th>
                <th>Akcja</th>
            </tr>
            <c:forEach items="${donations}" var="donation">
                <tr><td>
                    <c:forEach items="${donation.categories}" var="category">
                    ${category.name}
                    </c:forEach>
                </td>
                    <td>${donation.quantity}</td>
                    <td>${donation.city}, ${donation.street}, ${donation.zipCode}</td>
                    <td>${donation.pickUpDate}</td>
                    <td>${donation.pickUpTime}</td>
                    <td>${donation.pickUpComment}</td>
                    <td>${donation.institution.name}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</section>

</body>
</html>
