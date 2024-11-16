package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
                for (var name : List.of("", "group name")) {
                    for (var header : List.of("", "group header")) {
                        for (var footer : List.of("", "group footer")) {
                            result.add(new GroupData(name, header, footer));
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            result.add(new GroupData(randomString(i * 10), randomString(i * 10),randomString(i * 10)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        // приложение содержит баг: группа, в имени которой есть символ ' , не создается
        return List.of(new GroupData("group name ' ","",""));
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount + 1, newGroupCount);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        int groupCount = app.groups().getCount();
        app.groups().createGroup(group);
        int newGroupCount = app.groups().getCount();
        Assertions.assertEquals(groupCount, newGroupCount);
    }

}