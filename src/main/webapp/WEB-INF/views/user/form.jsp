<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
            // let x = $("[name='categories']");
            // let y = [];
            // console.log("X table ", x);
            // for (i = 0; i < x.length; i++) {
            //     if (x[i].checked === true) {
            //         y.push(x[i])
            //     }
            // }
            // console.log("Y-table: ", y);
            // let text = " ";
            //
            // for (let i = 0; i < y.length; i++) {
            //     text += y[i].dataset.category + " ";
            // }

            let text2 = " ";
            let categoriesName = $('input[name=categories]:checked', '#donationForm').data('category');
            $('input[name=categories]:checked', '#donationForm').each(function(){
                text2 +=($(this).data('category'))+" ";
            });

            let institutionName = $('input[name=institution]:checked', '#donationForm').data('institutions');
            console.log("Instytution: ",institutionName);

            $('#categoryDisplay').text(text2);
            let bags = $('#bagId').val();
            $('#bagDisplay').text(bags + ' worki z kategorii: ');
            $('#institutionDisplay').text(institutionName);

            let city = $('input[name=city', '#donationForm').val();
            let street = $('input[name=street','#donationForm').val();
            let zipCode = $('input[name=zipCode','#donationForm').val();
            let address = $('#addressDisplay');
            address.append("<li>" + street + "</li>").append("<li>" + zipCode + "</li>").append("<li>" + city + "</li>");

            let details = $('#detailsDisplay');
            let date = $('input[name=pickUpDate', '#donationForm').val();
            let time = $('input[name=pickUpTime','#donationForm').val();
            let comment = $('textarea[name=pickUpComment','#donationForm').val();
            details.append("<li>" + date + "</li>").append("<li>" + time + "</li>").append("<li>" + comment + "</li>");

        }
        function checkout1(){
            let checkBoxes = document.querySelectorAll('.checkbox1:checked');
            if (checkBoxes.length < 1){
                alert('Please, check at least one checkbox!');
                return false;
            }
        }
    </script>
</head>

<body>
<header>
<%@include file="../user_admin/navbar.jsp" %>
</header>

<section class="form--steps">

<%--    <div class="form--steps-instructions">--%>
<%--        <div class="form--steps-container">--%>
<%--            <h3>Ważne!</h3>--%>
<%--            <p data-step="1" class="active">--%>
<%--                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy--%>
<%--                wiedzieć komu najlepiej je przekazać.--%>
<%--            </p>--%>
<%--            <p data-step="2">--%>
<%--                Uzupełnij szczegóły dotyczące Twoich rzeczy. Dzięki temu będziemy--%>
<%--                wiedzieć komu najlepiej je przekazać.--%>
<%--            </p>--%>
<%--            <p data-step="3">--%>
<%--                Wybierz jedną organizację, do której trafi Twoja przesyłka.--%>
<%--            </p>--%>
<%--            <p data-step="4">Podaj adres oraz termin odbioru rzeczy.</p>--%>
<%--        </div>--%>
<%--    </div>--%>

    <div class="form--steps-container">
        <div class="form--steps-counter">Krok <span>1</span>/5</div>
        <form:form modelAttribute="donation" action="/donation" method="post" id="donationForm">
            <!-- STEP 1: class .active is switching steps -->
            <div data-step="1" class="active">
                <h3>Zaznacz co chcesz oddać:</h3>


                <c:forEach items="${categories}" var="category">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="checkbox" id="checkbox1" name="categories" value="${category.id}"
                                   data-category="${category.name}"/>
                            <span class="checkbox"></span>
                            <span class="description">${category.name}</span>
                        </label>
                    </div>
                </c:forEach>

<%--           TODO:     Obiekt typu categoryDTO--%>
                <div class="form-group form-group--inline">
                    <label> Dodaj swoją kategorię <input type="text" name="categories"/> </label>
                </div>


                <div class="form-group form-group--buttons">
                    <button type="button" class="btn next-step">Dalej</button>
                </div>
            </div>

            <!-- STEP 2 -->
            <div data-step="2">
                <h3>Podaj liczbę 60l worków, w które spakowałeś/aś rzeczy:</h3>

                <div class="form-group form-group--inline">
                    <label>
                        Liczba 60l worków:
                            <%--<input type="number" name="bags" step="1" min="1"/>--%>
                        <form:input type="number" min="1" path="quantity" id="bagId"/>
                        <form:errors path="quantity"/>
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
                <c:forEach items="${institutions}" var="institution">
                    <div class="form-group form-group--checkbox">
                        <label>
                            <input type="radio" name="institution" value="${institution.id}" data-institutions="${institution.name}"/>
                            <span class="checkbox radio"></span>
                            <span class="description">
                  <div class="title">${institution.name}</div>
                  <div class="subtitle">
                    Cel i misja: ${institution.description}
                  </div>
                </span>
                        </label>
                    </div>
                </c:forEach>


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
                            <label> Ulica <input type="text" name="street"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Miasto <input type="text" name="city"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label>Kod pocztowy <input type="text" name="zipCode"/></label>
                            <form:errors path="zipCode"/>
                        </div>
                    </div>

                    <div class="form-section--column">
                        <h4>Termin odbioru</h4>
                        <div class="form-group form-group--inline">
                            <label> Data <input type="date" name="pickUpDate"/> </label>
                        </div>

                        <div class="form-group form-group--inline">
                            <label> Godzina <input type="time" name="pickUpTime"/> </label>
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
                                <span class="summary--text">Dla&nbsp</span>
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
