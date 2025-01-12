package ru.must.mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Duration;

public class MailTests extends TestBase {

    @Test
    void canDrainInbox() {
        app.mailHelper().drain("user1@localhost", "password");
    }

    @Test
    void canReceiveEmail() {
        var messages = app.mailHelper().receive("user1@localhost", "password", Duration.ofSeconds(10));
        Assertions.assertEquals(1, messages.size());
        System.out.println(messages);
    }

    @Test
    void canExtractURL() {
        var messages = app.mailHelper().receive("user1@localhost", "password", Duration.ofSeconds(10));
        var url = app.mailHelper().extractURL(messages);
        Assertions.assertNotEquals("URL is absent", url);
        System.out.println(url);
    }
}

