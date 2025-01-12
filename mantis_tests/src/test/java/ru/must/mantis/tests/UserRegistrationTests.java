package ru.must.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static ru.must.mantis.common.CommonFunctions.randomString;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser() {
        // 1.Тест регистрирует новый адрес на почтовом сервере James.
        var username = String.format("%s", randomString(10));
        var email = String.format("%s@localhost", username);
        var password = "password";
        app.jamesCliHelper().addUser(email, password);
        // 2.Пользователь с главной страницы начинает регистрацию.
        app.signupHelper().signup(username, email);
        // 3.1. Mantis отправляет письмо на указанный адрес, тест должен получить это письмо,
        var messages = app.mailHelper().receive(email, password, Duration.ofSeconds(10));
        // 3.2. извлечь из него ссылку для подтверждения,
        var url = app.mailHelper().extractURL(messages);
        // 3.3. пройти по этой ссылке и завершить регистрацию.
        app.signupHelper().accountActivation(url);
        app.signupHelper().editAccount(username,password);
        // 4.Затем тест должен проверить, что пользователь может войти в систему с новым паролем. Этот шаг можно выполнить на уровне протокола HTTP.
        app.httpSessionHelper().login(username, password);
        Assertions.assertTrue(app.httpSessionHelper().isLoggedIn());
    }
}
