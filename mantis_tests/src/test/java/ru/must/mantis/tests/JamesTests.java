package ru.must.mantis.tests;

import org.junit.jupiter.api.Test;

import static java.lang.String.format;
import static ru.must.mantis.common.CommonFunctions.randomString;

public class JamesTests extends TestBase {

    @Test
    void canCreateUser() {
        app.jamesCli().addUser(String.format("%s@localhost", randomString(10)), "password");
    }
}
