package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("default name", "default header", "default footer"));
        }
        app.groups().deleteGroup();
    }

}
