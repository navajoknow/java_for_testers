package ru.must.addressbook.manager;
import ru.must.addressbook.models.GroupData;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class GroupHelper extends HelperBase {

    public GroupHelper(ApplicationManager manager) {
        // передача параметра конструктору родительского класса
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

    public void modifyGroup(GroupData group, GroupData modifiedGroup) {
        openGroupsPage();
        selectGroup(group);
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

//    public List<GroupData> getList() {
//        openGroupsPage();
//        var groups = new ArrayList<GroupData>();
//        var spans = manager.driver.findElements(By.cssSelector("span.group"));
//         for (var span : spans) {
//             var name = span.getText();
//             var checkbox = span.findElement(By.name("selected[]"));
//             var id = checkbox.getAttribute("value");
//             groups.add(new GroupData().withId(id).withName(name));
//         }
//         return groups;
//    }

    // этот же метод в стиле функционального програмиирования
        public List<GroupData> getList() {
        openGroupsPage();
        var spans = manager.driver.findElements(By.cssSelector("span.group"));
        return spans.stream()
                .map(span -> {
                    var name = span.getText();
                    var checkbox = span.findElement(By.name("selected[]"));
                    var id = checkbox.getAttribute("value");
                    return new GroupData().withId(id).withName(name);
                })
                .collect(Collectors.toList());
    }
}
