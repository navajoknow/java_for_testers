package tests;

import models.GroupData;
import org.junit.jupiter.api.Test;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        if (!app.groups().isGroupPresent()) {
            app.groups().createGroup(new GroupData("name", "header", "footer"));
        }
        app.groups().deleteGroup();
    }

}
