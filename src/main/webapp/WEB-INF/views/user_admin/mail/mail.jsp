<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Mail</title>
    <%@include file="../head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Twoja wiadomość:</h2>
    <div class="users">
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Nadawca:</th>
                <td>${mail.sender}
            </tr>
            <tr>
                <th>Odbiorca:</th>
                <td>${mail.recipient}
            </tr>
            <tr>
                <th>Tytuł:</th>
                <td>${mail.title}
            </tr>
            <tr>
                <th>Wiadomość:</th>
                <td>${mail.text}
            </tr>

        </table>
    </div>


</section>


</body>
</html>
