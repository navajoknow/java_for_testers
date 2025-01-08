package ru.must.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        app.session().login("administrator", "root");
        Assertions.assertTrue(app.session().isLoggedIn());
    }

    @Test
    void canLoginHTTP() {
        app.httpSession().login("administrator", "root");
        Assertions.assertTrue(app.httpSession().isLoggedIn());
    }
}
