package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Test;
import tests.TestBase;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        app.groups().createGroup(new GroupData("default name", "default header", "default footer"));
    }

    @Test
    public void canCreateEmptyGroup() {
        app.groups().createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        //withName не модифицирует объект, в котором он вызван, а возвращает новый объект
        app.groups().createGroup(new GroupData().withName("default name only"));
    }
}
