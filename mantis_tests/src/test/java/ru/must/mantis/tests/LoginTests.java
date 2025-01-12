package ru.must.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoginTests extends TestBase {

    @Test
    void canLogin() {
        app.sessionHelper().login("administrator", "root");
        Assertions.assertTrue(app.sessionHelper().isLoggedIn());
    }

    @Test
    void canLoginHTTP() {
        app.httpSessionHelper().login("administrator", "root");
        Assertions.assertTrue(app.httpSessionHelper().isLoggedIn());
    }
}
