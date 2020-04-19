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
    <title>Things to give</title>
    <script
            src="https://code.jquery.com/jquery-3.4.1.min.js"
            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
            crossorigin="anonymous"></script>
    <script>
        function showInput() {

            // Pobranie nazwy wybranych kategorii
            let tablica = [];
            $('.categorie').each(function () {
                tablica.push($(this).val());
            });

            let text2 = " ";
            $('.myCheckbox:checked').each(function () {
                // text2 += ($(this).val())+" ";
                text2 += tablica[$(this).val()-1] + " ";
            });
            // Pobranie nazwy wybranej instytucji
            let tablica2 = [];
            $('.instytucje').each(function () {
                tablica2.push($(this).val());
            });
             let institutionName = tablica2[$('.myRadio:checked').val()];



            $('#categoryDisplay').text(text2);
            let bags = $('#bagId').val();
            $('#bagDisplay').text('Liczba worków: '+bags+ ' Kategorie: ');
            $('#institutionDisplay').text(institutionName);

            let city = $('input[name=city', '#donationForm').val();
            let street = $('input[name=street', '#donationForm').val();
            let zipCode = $('input[name=zipCode', '#donationForm').val();
            let address = $('#addressDisplay');
            address.append("<li>" + street + "</li>").append("<li>" + zipCode + "</li>").append("<li>" + city + "</li>");

            let details = $('#detailsDisplay');
            let date = $('input[name=pickUpDate', '#donationForm').val();
            let time = $('input[name=pickUpTime', '#donationForm').val();
            let comment = $('textarea[name=pickUpComment', '#donationForm').val();
            details.append("<li>" + date + "</li>").append("<li>" + time + "</li>").append("<li>" + comment + "</li>");

        }

    </script>
</head>

<body>
<header>
    <%@include file="../user_admin/navbar.jsp" %>
</header>

<section class="form--steps">

    <div class="form--steps-container">

        <c:if test="${errors == true}">
            <div class="error">
                <h3>Popraw błędy w formularzu </h3>
            </div>
        </c:if>

        <div class="form--steps-counter">Krok <span>1</span>/5</div>


        <form:form modelAttribute="donation" action="/donation" method="post" id="donationForm" htmlEscape="true">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>
                <div class="error">
                <form:errors path="categories"/>
                </div>
                <div class="checkmark">
                 <form:checkboxes cssClass="myCheckbox" path="categories" items="${allCategories}" itemValue="id" itemLabel="name" />
                </div>

                <c:forEach items="${allCategories}" var="category">
                    <input type="hidden" class="categorie" value="${category.name}"/>
                </c:forEach>


                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>
                <div class="error"><form:errors path="quantity"/></div>
                <div class="form-group form-group--inline">
                    <label>
                       Liczba 60l worków:
                        <form:input type="number" min="1" path="quantity" id="bagId"/>
                    </label>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>


            <!-- STEP 3 -->
            <div data-step="3">
                <h3>Wybierz organizacje, której chcesz pomóc:</h3>
                <div class="error">
                    <form:errors path="institution"/>
                </div>
                <div class="checkmark">
                <form:radiobuttons cssClass="myRadio" path="institution" items="${institutions}" itemLabel="name" itemValue="id"/>
                </div>
                <c:forEach items="${institutions}" var="institution">
                    <input type="hidden" class="instytucje" value="${institution.name}"/>
                </c:forEach>

<%--                <c:forEach items="${institutions}" var="institution">--%>
<%--                    <form:radiobutton path="institution" label="${institution.name}" value="id"/>--%>
<%--                    <c:out value="${institution.description}"/>--%>
<%--                </c:forEach>--%>

<%--                <c:forEach items="${institutions}" var="institution">--%>
<%--                    <div class="form-group form-group--checkbox">--%>
<%--                        <label>--%>
<%--                            <input type="radio" name="institution" value="${institution.id}"--%>
<%--                                   data-institutions="${institution.name}"/>--%>
<%--                            <span class="checkbox radio"></span>--%>
<%--                            <span class="description">--%>
<%--                  <div class="title">${institution.name}</div>--%>
<%--                  <div class="subtitle">--%>
<%--                    Cel i misja: ${institution.description}--%>
<%--                  </div>--%>
<%--                </span>--%>
<%--                        </label>--%>
<%--                    </div>--%>
<%--                </c:forEach>--%>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 4 -->
            <div data-step="4">
                <h3>Podaj adres oraz termin odbioru rzecz przez kuriera:</h3>

                <div class="form-section form-section--columns">
                    <div class="form-section--column">
                        <h4>Adres odbioru</h4>
                        <div class="form-group form-group--inline">
                           <div class="error"><form:errors path="street"/></div>
                            <label> Ulica <form:input type="text" name="street" path="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <div class="error"><form:errors path="city"/></div>
                            <label> Miasto <form:input type="text" name="city" path="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <div class="error"><form:errors path="zipCode"/></div>
                            <label>Kod pocztowy <form:input type="text" name="zipCode" path="zipCode"/></label>
                         </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <div class="error"><form:errors path="pickUpDate"/></div>
                            <label> Data <form:input type="date" name="pickUpDate" path="pickUpDate"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <div class="error"><form:errors path="pickUpTime"/></div>
                            <label> Godzina <form:input type="time" name="pickUpTime" path="pickUpTime"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>
                                Uwagi dla kuriera
                                <textarea name="pickUpComment" rows="5"></textarea>
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="button" class="btn next-step" onclick="showInput()">Dalej</button>
                </div>
            </div>

            <!-- STEP 5 -->
            <div data-step="5">


                <h3>Podsumowanie Twojej darowizny</h3>

                <div class="summary">
                    <div class="form-section">
                        <h4>Oddajesz:</h4>
                        <ul>
                            <li>
                                <span class="icon icon-bag"></span>

                                <span class="summary--text" id="bagDisplay"></span>&nbsp
                                <span class="summary--text">&nbsp</span>
                                <span class="summary--text" id="categoryDisplay"></span>
                            </li>

                            <li>
                                <span class="icon icon-hand"></span>
                                <span class="summary--text">Dla fundacji:&nbsp</span>
                                <span class="summary--text" id="institutionDisplay"></span>
                            </li>
                        </ul>
                    </div>

                    <div class="form-section form-section--columns">
                        <div class="form-section--column">
                            <h4>Adres odbioru:</h4>
                            <ul id="addressDisplay">

                            </ul>
                        </div>

                        <div class="form-section--column">
                            <h4>Termin odbioru:</h4>
                            <ul id="detailsDisplay">

                            </ul>
                        </div>
                    </div>
                </div>

                <div class="form-group form-group--buttons">
                    <button type="button" class="btn prev-step">Wstecz</button>
                    <button type="submit" class="btn">Potwierdzam</button>
                </div>
            </div>

        </form:form>

    </div>
</section>


<%@include file="../user_admin/footer.jsp" %>
</body>
</html>
