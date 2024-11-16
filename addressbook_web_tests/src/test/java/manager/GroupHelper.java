package manager;

import models.GroupData;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        // класс-помощник использует родительский -HelperBase- конструктор как свой собственный
        super(manager);
    }

    public void openGroupsPage() {
        if (!manager.isElementPresent(By.name("new"))) {
            click(By.linkText("groups"));
        }
    }

    public void createGroup(GroupData group) {
        openGroupsPage();
        initGroupCreation();
        fillGroupForm(group);
        submitCreation();
        returnToGroupsPage();
    }

    public void deleteGroup(GroupData group) {
        openGroupsPage();
        selectGroup(group);
        deleteSelectedGroups();
        returnToGroupsPage();
    }

    public void selectGroup(GroupData group) {
        click(By.cssSelector(String.format("input[value = '%s']", group.id())));
    }

    public void modifyGroup(GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(null);
        initGroupModification();
        fillGroupForm(modifiedGroup);
        submitGroupModification();
        returnToGroupsPage();
    }

    public void fillGroupForm(GroupData group) {
        type(By.name("group_name"), group.name());
        type(By.name("group_header"), group.header());
        type(By.name("group_footer"), group.footer());
    }

    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void returnToGroupsPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupModification() {
        click(By.name("update"));
    }

    public void initGroupModification() {
        click(By.name("edit"));
    }

    public int getCount() {
        openGroupsPage();
        return manager.driver.findElements(By.name("selected[]")).size();
    }

    public void deleteMultipleGroups() {
        openGroupsPage();
        selectAllItems();
        deleteSelectedGroups();
    }

    public List<GroupData> getList() {
        var groups = new ArrayList<GroupData>();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
         for (var span : spans) {
             var name = span.getText();
             var checkbox = span.findElement(By.name("selected[]"));
             var id = checkbox.getAttribute("value");
             groups.add(new GroupData().withId(id).withName(name));
         }
         return groups;
    }
}
