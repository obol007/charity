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

    <title>Institutions</title>
</head>

<body>

<header>
    <%@include file="adminNav.jsp" %>
</header>

<section>
    <div class="users">


        <h2>Fundacje:</h2>
        <c:if test="${isActive == null}">
            <a href="${pageContext.request.contextPath}/admin/institutions/add" class="btn btn--highlighted">Dodaj
                fundację</a>
        </c:if>

        <c:if test="${isActive == true}">
            <div class="users">
                <p> Nie można usunąć aktywnej fundacji <b> ${institutionToDelete.name}</b>! Najpierw ją dezaktywuj!</p>
                <a href="${pageContext.request.contextPath}/admin/institutions" class="btn">OK</a>
            </div>
        </c:if>

        <c:if test="${isActive == false}">
            <p> Czy na pewno chcesz usunąć fundację <b> ${institutionToDelete.name}</b> ?
                <a href="${pageContext.request.contextPath}/admin/institutions" class="btn btn--highlighted">Anuluj</a>
                <a href="/admin/institutions/delete/confirmed/${institutionToDelete.id}" class="btn">Tak</a>
            </p>
        </c:if>

        <div class="users">
            <table border="1" cellpadding="5" align="center">
                <tr>
                    <th colspan="2">Aktywna</th>
                    <th>Nazwa</th>
                    <th>Opis</th>
                    <th colspan="2">Akcja</th>
                </tr>

                <c:if test="${newInstitution != null}">
                    <tr>
                        <form:form modelAttribute="newInstitution" action="/admin/institutions/add" method="post">

                            <form:hidden path="id"/>

                            <td colspan="2"><form:checkbox path="active"/></td>
                            <td><form:input path="name" placeholder="Nazwa fundacji" size="20px"/>
                                <form:errors path="name"/></td>
                            <td><form:input path="description" placeholder="Opis fundacji" size="40px"/>
                                <form:errors path="description"/></td>

                            <td>
                                <button type="submit" class="btn btn--highlighted">Zapisz</button>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/institutions"
                                   class="btn btn--highlighted">Anuluj</a>
                            </td>

                        </form:form>
                    </tr>

                </c:if>


                <c:forEach items="${institutions}" var="institution">
                    <tr <c:if test="${institution.id == newInstitution.id}"> bgcolor="#5f9ea0"</c:if>>
                        <td><input type="checkbox" disabled id="${institution.id}" value="${institution.active}"
                                <c:if test="${institution.active == true}"> checked</c:if> /></td>
                        <td><a href="/admin/institutions/active/${institution.id}"
                               class="btn btn--small btn--highlighted">Zmień</a></td>
                        <td>${institution.name}</td>
                        <td>${institution.description}</td>
                        <td><a href="/admin/institutions/edit/${institution.id}"
                               class="btn btn--small btn--highlighted">Edytuj</a>
                        </td>
                        <td><a href="/admin/institutions/delete/${institution.id}"
                               class="btn btn--small btn--highlighted">Usuń</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>

    </div>
</section>


<script src="<c:url value="/resources/js/app.js"/>"></script>
</body>
</html>

