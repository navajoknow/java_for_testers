package ru.must.addressbook.generator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.must.addressbook.common.CommonFunctions;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;

import java.io.File;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Generator {
    // используем библиотеку jcommander для распознавания аргументов
    // командной строки, передаваемых в программу
    @Parameter(names = {"--type", "-t"})
    String type;

    @Parameter(names = {"--output", "-o"})
    String output;

    @Parameter(names = {"--format", "-f"})
    String format;

    @Parameter(names = {"--count", "-n"})
    int count;

    public static void main(String[] args) throws IOException {
        var generator = new Generator();
        // парсер командной строки
        JCommander.newBuilder()
                .addObject(generator)
                .build()
                .parse(args);
        generator.run();
    }

    private void run() throws IOException {
        var data = generate();
        save(data);
    }

    private Object generate() {
        if ("groups".equals(type)) {
            return generateGroups();
        } else if ("contacts".equals(type)) {
            return generateContacts();
        } else {
            throw new IllegalArgumentException("Неизвестный тип данных: " + type);
        }
    }

//    private Object generateGroups() {
//        var result = new ArrayList<GroupData>();
//        for (int i = 0; i < count; i++) {
//            result.add(new GroupData()
//                    .withName(CommonFunctions.randomString(i * 10))
//                    .withHeader(CommonFunctions.randomString(i * 10))
//                    .withFooter(CommonFunctions.randomString(i * 10)));
//        }
//        return result;
//    }
//
//    private Object generateContacts() {
//        var result = new ArrayList<ContactData>();
//        for (int i = 0; i < count; i++) {
//            result.add(new ContactData()
//                    .withFirstName(CommonFunctions.randomString(i * 10))
//                    .withLastName(CommonFunctions.randomString(i * 10))
//                    .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
//        }
//        return result;
//    }

    // реализация этих же методов, но в стиле функционального программирования
    private Object generateData(Supplier<Object> dataSupplier) {
        return Stream.generate(dataSupplier).limit(count).collect(Collectors.toList());
    }

          private Object generateGroups() {
              return generateData(() -> new GroupData()
                      .withName(CommonFunctions.randomString(10))
                      .withHeader(CommonFunctions.randomString(10))
                      .withFooter(CommonFunctions.randomString(10)));
      }

          private Object generateContacts() {
              return generateData(() -> new ContactData()
                      .withFirstName(CommonFunctions.randomString(10))
                      .withLastName(CommonFunctions.randomString(10))
                      .withPhoto(CommonFunctions.randomFile("src/test/resources/images")));
      }
    //

    private void save(Object data) throws IOException {
        if ("json".equals(format)) {
            // сериализация с помощью библиотеки Jackson
            ObjectMapper mapper = new ObjectMapper();
            // метод для представления содержания json файла в удобном для чтения виде, а не одной строкой
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            mapper.writeValue(new File(output), data);
//            // альтернативная реализация записи с помощью Jackson
//            var json = mapper.writeValueAsString(data);
//            // реализация записи с помощью стандартной библиотеки
//            try (var writer = new FileWriter(output)) {
//                writer.write(json);
        } else if ("yaml".equals(format)) {
            var mapper = new YAMLMapper();
            mapper.writeValue(new File(output), data);
        } else if ("xml".equals(format)) {
            var mapper = new XmlMapper();
            mapper.writeValue(new File(output), data);
        } else {
            throw new IllegalArgumentException("Неизвестный формат данных: " + format);
        }
    }
}





