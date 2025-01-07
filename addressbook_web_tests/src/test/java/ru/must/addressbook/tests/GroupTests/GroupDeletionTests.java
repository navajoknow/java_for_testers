package ru.must.addressbook.tests.GroupTests;

import ru.must.addressbook.models.GroupData;
import ru.must.addressbook.tests.TestBase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Random;

public class GroupDeletionTests extends TestBase {

    @Test
    public void canDeleteGroup() {
        // при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        // проверяемую функцию (удаление группы) осуществляем через GUI, остальное для ускорения - через БД
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("","name", "header","footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        app.groups().deleteGroup(oldGroups.get(index));
        var newGroups = app.hbm().getGroupList();
        var expectedLists = new ArrayList<>(oldGroups);
        expectedLists.remove(index);
        Assertions.assertEquals(expectedLists, newGroups);
    }

    @Test
    public  void canDeleteAllGroups() {
        if (app.hbm().getGroupCount() < 2) {
            for (int i = 0; i < 2; i++) {
                app.hbm().createGroup(new GroupData());
            }
        }
        app.groups().deleteMultipleGroups();
        Assertions.assertEquals(0, app.hbm().getGroupCount());
    }

}
