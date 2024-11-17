package tests.GroupTests;

import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() {
        var result = new ArrayList<GroupData>();
                for (var name : List.of("", "group name")) {
                    for (var header : List.of("", "group header")) {
                        for (var footer : List.of("", "group footer")) {
                            result.add(new GroupData().
                                    withName(name).
                                    withHeader(header).
                                    withFooter(footer));
                }
            }
        }
        for (int i = 1; i < 5; i++) {
            result.add(new GroupData().
                    withName(randomString(i * 10)).
                    withHeader(randomString(i * 10)).
                    withFooter(randomString(i * 10)));
        }
        return result;
    }

    public static List<GroupData> negativeGroupProvider() {
        // приложение содержит баг: группа, в имени которой есть символ ' , не создается
        return List.of(new GroupData("", "group name ' ", "", ""));
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {
        //при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };
        newGroups.sort(compareById);

        var expectedList = new ArrayList<>(oldGroups);
        // в т.ч. присваиваем группам пустые поля header и footer, чтобы при их сравнениии тест не сообщал о несоответствии
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()).withHeader("").withFooter(""));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @ParameterizedTest
    @MethodSource("negativeGroupProvider")
    public void canNotCreateGroup(GroupData group) {
        var oldGroups = app.groups().getList();
        app.groups().createGroup(group);
        var newGroups = app.groups().getList();
        Assertions.assertEquals(oldGroups, newGroups);
    }

}