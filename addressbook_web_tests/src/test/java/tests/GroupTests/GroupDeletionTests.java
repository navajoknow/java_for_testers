package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Random;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        if (app.groups().getCount() == 0) {
            app.groups().createGroup(new GroupData("id", "name", "header", "footer"));
        }
        var oldGroups = app.groups().getList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.groups().getList();
        var expectedLists = new ArrayList<>(oldGroups);
        expectedLists.remove(index);
        Assertions.assertEquals(expectedLists, newGroups);
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
