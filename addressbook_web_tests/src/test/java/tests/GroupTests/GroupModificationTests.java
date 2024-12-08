package tests.GroupTests;
import common.CommonFunctions;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.TestBase;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Set;

public class GroupModificationTests extends TestBase {

//    @Test
//    void canModifyGroup() {
//        if (app.hbm().getGroupCount() == 0) {
//            app.hbm().createGroup(new GroupData("","name", "header","footer"));
//        }
//        var oldGroups = app.hbm().getGroupList();
//        var rnd = new Random();
//        var index = rnd.nextInt(oldGroups.size());
//        var testData = new GroupData().withName("modified name");
//        app.groups().modifyGroup(oldGroups.get(index), testData);
//        var newGroups = app.hbm().getGroupList();
//        var expectedList = new ArrayList<>(oldGroups);
//        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
//
//        // сортировка по возрастанию идентификаторов с использованием comparator: функция сортировки передается
//        // как параметр, что свойственно функциональным языкам программирования
//        Comparator<GroupData> compareById = (o1, o2) -> {
//            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
//        };
//
//        newGroups.sort(compareById);
//        expectedList.sort(compareById);
//        Assertions.assertEquals(expectedList, newGroups);
//    }

    // реализация этого же метода через множества (set)
    @Test
    void canModifyGroup() {
        if (app.hbm().getGroupCount() == 0) {
            app.hbm().createGroup(new GroupData("","name", "header","footer"));
        }
        var oldGroups = app.hbm().getGroupList();
        var rnd = new Random();
        var index = rnd.nextInt(oldGroups.size());
        var testData = new GroupData().withName(CommonFunctions.randomString(10));
        app.groups().modifyGroup(oldGroups.get(index), testData);
        var newGroups = app.hbm().getGroupList();
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.set(index, testData.withId(oldGroups.get(index).id()));
        // преобразуем в множества, которые, в отличии от списков, сравниваются без учета порядка элементов
        Assertions.assertEquals(Set.copyOf(expectedList), Set.copyOf(newGroups));
    }
}
