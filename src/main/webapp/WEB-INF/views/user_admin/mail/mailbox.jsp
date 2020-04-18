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
    <div class="users">
        <table border="1" cellpadding="5" align="center">
            <tr>
                <th>Odbiorca</th>
                <th>Temat</th>
                <th>Treść maila</th>
                <th>Data</th>
            </tr>
            <c:forEach items="${mails}" var="mail">
            <tr <c:if test="${mail.read==false}"> style="color: cadetblue"</c:if> >
                <td colspan="4"><a href="/mailbox/${mail.id}"> ${mail.recipient} ${mail.title} ${mail.text} ${mail.created} </a></td>
                <td></td>
                <td></td>
                <td></td>
            </tr>
            </c:forEach>
    </div>


</section>


</body>
</html>