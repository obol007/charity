<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<header>
<nav class="container container--70">
    <ul class="nav--actions">
       <li><form action="<c:url value="/logout"/>" method="post">
            <input class="btn btn--small btn--without-border" type="submit" value="Wyloguj">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
       </form></li>
      </ul>

    <ul>
        <li><a href="/" class="btn btn--without-border">Strona główna</a></li>
        <li><a href="/donation" class="btn btn--without-border active">Start</a></li>
        <li><a href="#" class="btn btn--without-border">O co chodzi?</a></li>
        <li><a href="#" class="btn btn--without-border">O nas</a></li>
        <li><a href="#" class="btn btn--without-border">Fundacje i organizacje</a></li>
        <li><a href="#" class="btn btn--without-border">Kontakt</a></li>
    </ul>
</nav>
</header>