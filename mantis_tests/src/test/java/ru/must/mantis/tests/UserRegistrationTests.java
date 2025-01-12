package ru.must.mantis.tests;

import org.junit.jupiter.api.Test;

public class UserRegistrationTests extends TestBase {

    @Test
    void canRegisterUser(String username) {
        var email = String.format("%s@localhost", username);
        /*
        1.Тест регистрирует новый адрес на почтовом сервере James.
        2.Пользователь с главной страницы начинает регистрацию.
        3.Mantis отправляет письмо на указанный адрес, тест должен получить это письмо, извлечь из него ссылку для подтверждения, пройти по этой ссылке и завершить регистрацию.
        4.Затем тест должен проверить, что пользователь может войти в систему с новым паролем. Этот шаг можно выполнить на уровне протокола HTTP.
        */
    }
}
