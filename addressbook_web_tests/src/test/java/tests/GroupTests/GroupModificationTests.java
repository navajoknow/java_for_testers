package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupModificationTests extends TestBase {

    @Test
    void canModifyGroup() {
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("default name", "default header", "default footer"));
        }
        app.groups().modifyGroup(new GroupData().withName("modified name"));

    }
}
