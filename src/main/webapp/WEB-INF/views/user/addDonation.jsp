<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="pl">
<head>
    <%@include file="../user_admin/head.jsp" %>

    <title>Add donation</title>
</head>

<body>


<form:form modelAttribute="donation" action="/donation" method="post">


    <form:checkboxes path="categories" items="${categories}" itemValue="id" itemLabel="name"/>
    <form:select path="institution" items="${institutions}" itemValue="id" itemLabel="name"/>
    <%--    <form:select path="publisher.id" itemLabel="name" itemValue="id" items="${publishers}"/>--%>
    <form:input path="zipCode"/>
    <form:input path="street"/>
    <form:input path="city"/>
    <form:input type="number" min="1" path="quantity"/>
    <form:textarea path="pickUpComment"/>
    <form:input type="date" path="pickUpDate"/>
    <form:input type="time" path="pickUpTime"/>

    <form:button>WYSLIJ</form:button>

</form:form>


</body>
</html>
