package tests;

import model.GroupData;
import org.junit.jupiter.api.Test;

public class GroupCreationTests extends TestBase {

    @Test
    public void canCreateGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData("group name", "group header", "group footer"));
    }

    @Test
    public void canCreateEmptyGroup() {
        app.openGroupsPage();
        app.createGroup(new GroupData());
    }

    @Test
    public void canCreateGroupWithNameOnly() {
        app.openGroupsPage();
        //withName не модифицирует объект, в котором он вызван, а возвращает новый объект
        app.createGroup(new GroupData().withName("some name"));
    }
}
