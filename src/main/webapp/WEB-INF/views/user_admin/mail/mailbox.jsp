<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Mailbox</title>
    <%@include file="../head.jsp" %>
</head>
<body>

<section class="login-page">
    <h2>Twoje wiadomości:</h2>
    <a href="/" class="btn">Powrót</a>
    <div class="users">
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Odbiorca</th>
                <th>Temat</th>
                <th>Treść maila</th>
                <th>Data</th>
            </tr>
            <c:forEach items="${mails}" var="mail">
            <tr>
                <td>
                    <c:if test="${mail.opened==false}"><b> ${mail.recipient}</b></c:if>
                    <c:if test="${mail.opened==true}"> ${mail.recipient}</c:if>
                </td>
                <td>
                    <c:if test="${mail.opened==false}"><b> ${mail.title}</b></c:if>
                    <c:if test="${mail.opened==true}"> ${mail.title}</c:if>
                </td>
                <td><c:if test="${mail.opened==false}"><b><a href="/mailbox/${mail.id}">${mail.content.substring(0,20)} . .
                    . .</a> </b></c:if>
                    <c:if test="${mail.opened==true}"><a href="/mailbox/${mail.id}">${mail.content.substring(0,20)} . . .
                        .</a> </c:if>
                </td>
                <td><c:if test="${mail.opened==false}"><b> ${mail.created}</b></c:if>
                    <c:if test="${mail.opened==true}">${mail.created}</c:if>
                </td>
            </tr>
            </c:forEach>
    </div>


</section>


</body>
</html>