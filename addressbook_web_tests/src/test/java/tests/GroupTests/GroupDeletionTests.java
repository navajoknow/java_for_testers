package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("default name", "default header", "default footer"));
        }
        int groupCount = app.groups().getCount();
        app.groups().deleteGroup();
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount - 1, newGroupCount);
    }

    @Test
    public  void canDeleteAllGroups() {
        if (app.groups().getCount() < 2) {
            app.groups().createGroup(new GroupData());
            app.groups().createGroup(new GroupData());
        }
        app.groups().deleteMultipleGroups();
        Assertions.assertEquals(0, app.groups().getCount());
    }

}
