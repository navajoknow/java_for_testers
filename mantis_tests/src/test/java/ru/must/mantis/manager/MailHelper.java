package ru.must.mantis.manager;

import jakarta.mail.*;
import ru.must.mantis.models.MailMessage;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

public class MailHelper extends HelperBase {

    public MailHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<MailMessage> receive(String email, String password, Duration duration) {
        var start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + duration.toMillis()) {
            try {
                var inbox = getInbox(email, password);
                inbox.open(Folder.READ_ONLY);
                var messages = inbox.getMessages();
                var result = Arrays.stream(messages)
                        .map(m -> {
                            try {
                                return new MailMessage()
                                        .withFrom(m.getFrom()[0].toString())
                                        .withContent((String) m.getContent());
                            } catch (MessagingException | IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .toList();
                inbox.close();
                inbox.getStore().close();
                if (!result.isEmpty()) {
                    return result;
                }
            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        throw new RuntimeException("No mail received in time");
    }

    private static Folder getInbox(String username, String password) {
        try {
            var session = Session.getInstance(new Properties());
            // получение доступа к хранилищу почты
            Store store = session.getStore("pop3");
            store.connect("localhost", username, password);
            return store.getFolder("INBOX");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void drain(String username, String password) {
        try {
            var inbox = getInbox(username, password);
            inbox.open(Folder.READ_WRITE);
            Arrays.stream(inbox.getMessages()).forEach(m -> {
                try {
                    m.setFlag(Flags.Flag.DELETED, true);
                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                }
            });
            inbox.close();
            inbox.getStore().close();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String extractURL(List<MailMessage> messages) {
        var text = messages.get(0).content();
        var pattern = Pattern.compile("http://\\S*");
        var matcher = pattern.matcher(text);
        String url = "URL is absent";
        if (matcher.find()) {
            url = text.substring(matcher.start(), matcher.end());
        }
        return url;
    }
}
