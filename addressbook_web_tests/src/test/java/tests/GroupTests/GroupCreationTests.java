package tests.GroupTests;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import common.CommonFunctions;
import models.GroupData;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import tests.TestBase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GroupCreationTests extends TestBase {

    public static List<GroupData> groupProvider() throws IOException {
        var result = new ArrayList<GroupData>();
//                for (var name : List.of("", "group name")) {
//                    for (var header : List.of("", "group header")) {
//                        for (var footer : List.of("", "group footer")) {
//                            result.add(new GroupData()
//                                    .withName(name)
//                                    .withHeader(header)
//                                    .withFooter(footer));
//                }
//            }
//        }
//        // код для построчного чтения содержимого файла и формирования строки json,
//        // содержащей все строки файла, с использованием стандартной библиотеки
//        var json = "";
//        try (var reader = new FileReader("groups.json");
//             var breader = new BufferedReader(reader)
//        ) {
//            var line = breader.readLine();
//            while (line != null) {
//                json = json + line;
//                line = breader.readLine();
//              }
//        }
//        // код для преобразования содержимого строки в объект Java (десериализации),
//        // c использованием библиотеки Jackson:
//        ObjectMapper mapper = new ObjectMapper();
//        var value = mapper.readValue(json, new TypeReference<List<GroupData>>(){});
//        result.addAll(value);
//        return result;
//        // код для чтения данных из файла в формате json и преобразования его содержимого
//        в объект Java (десериализации), c использованием библиотеки Jackson:
//        var json = Files.readString(Paths.get("groups.json"));
//        ObjectMapper mapper = new ObjectMapper();
//        var value = mapper.readValue(json, new TypeReference<List<GroupData>>(){});
        // альтернативный код для десериализации JSON c использованием библиотеки Jackson:
        var mapper = new ObjectMapper();
        var value = mapper.readValue(new File("groups.json"), new TypeReference<List<GroupData>>(){});
        //  код для десериализации XML c использованием библиотеки Jackson:
//        var mapper = new XmlMapper();
//        var value = mapper.readValue(new File("groups.xml"), new TypeReference<List<GroupData>>(){});
        result.addAll(value);
        return result;
    }

    public static List<GroupData> singleRandomGroupProvider() {
        return List.of(new GroupData()
                .withName(CommonFunctions.randomString(10))
                .withHeader(CommonFunctions.randomString(10))
                .withFooter(CommonFunctions.randomString(10)));
    }

        public static List<GroupData> negativeGroupProvider() {
        // приложение содержит баг: группа, в имени которой есть символ ' , не создается
        return List.of(new GroupData("", "group name ' ", "", ""));
    }

    @ParameterizedTest
    @MethodSource("groupProvider")
    public void canCreateMultipleGroups(GroupData group) {

        // при первом обращении к методу groups() помощник (экземпляр GroupHelper) будет проиницализрован
        // метод для сбора с помощью JDBC
        var oldGroups = app.jdbc().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.jdbc().getGroupList();

        // сортировка по возрастанию
        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);
    }

    @ParameterizedTest
    @MethodSource("singleRandomGroupProvider")
    public void canCreateGroup(GroupData group) {

        // сравниваем новый список групп, собраный из БД, с ожидаемым списком из БД

        // сбор с помощью JDBC
        // var oldGroups = app.jdbc().getGroupList();
        // app.groups().createGroup(group);
        // var newGroups = app.jdbc().getGroupList();

        // сбор с помощью Hibernate
        var oldGroups = app.hbm().getGroupList();
        app.groups().createGroup(group);
        var newGroups = app.hbm().getGroupList();

        Comparator<GroupData> compareById = (o1, o2) -> {
            return Integer.compare(Integer.parseInt(o1.id()), Integer.parseInt(o2.id()));
        };

        newGroups.sort(compareById);
        var expectedList = new ArrayList<>(oldGroups);
        expectedList.add(group.withId(newGroups.get(newGroups.size()-1).id()));
        expectedList.sort(compareById);
        Assertions.assertEquals(expectedList, newGroups);

        // также сравниваем новый список групп, собраный через UI, с ожидаемым списком из БД
        var uiGroups = app.groups().getList();
        uiGroups.sort(compareById);
        expectedList.set(expectedList.size()-1, uiGroups.get(uiGroups.size()-1));
        // костыль из цикла: обеспечиваем соответствие по полям header и footer
        for (int i = 0; i < uiGroups.size(); i++) {
            var testHeader = expectedList.get(i).header();
            var testFooter = expectedList.get(i).footer();
            uiGroups.set(i, uiGroups.get(i).withHeader(testHeader).withFooter(testFooter));
        }
        Assertions.assertEquals(expectedList, uiGroups);
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