package ru.must.addressbook.manager;

import ru.must.addressbook.manager.hbm.ContactRecord;
import ru.must.addressbook.manager.hbm.GroupRecord;
import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                        .addAnnotatedClass(GroupRecord.class)
                        .addAnnotatedClass(ContactRecord.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=CONVERT_TO_NULL")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    // метод для переноса данных из списка GroupRecord в список GroupData
//    static List<GroupData> convertGroupList(List<GroupRecord> records) {
//        List<GroupData> result = new ArrayList<>();
//        for (var record : records) {
//            // из базы забираем id с типом int, а в GroupData это String, поэтому нужно строковое представление
//            result.add(convertToGroupData(record));
//        }
//        return result;
//    }

    // этот же метод, написанный в стиле функционального программирования
    static List<GroupData> convertGroupList(List<GroupRecord> records) {
        return records.stream()
                .map(HibernateHelper::convertToGroupData)
                .collect(Collectors.toList());
    }

    private static GroupData convertToGroupData(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    // метод для Contact
    static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream()
                .map(HibernateHelper::convertToContactData)
                .collect(Collectors.toList());
    }

    private static ContactData convertToContactData(ContactRecord record) {
        return new ContactData(
                "" + record.id,
                record.first_name,
                record.middle_name,
                record.last_name,
                record.photo,
                record.home,
                record.mobile,
                record.work,
                record.phone2);
    }

    private static ContactRecord convertToContactRecord(ContactData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id), data.first_name(), data.middle_name(), data.last_name(), data.photo());
    }


    private static GroupRecord convertToGroupRecord(GroupData data) {
        var id = data.id();
        if ("".equals(id)) {
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id), data.name(), data.header(), data.footer());
    }

    public List<GroupData> getGroupList() {
        return convertGroupList(sessionFactory.fromSession(session -> {
            // запрос выбирает все строки из таблицы, которая соответствует сущности GroupRecord
            return session.createQuery("from GroupRecord", GroupRecord.class).list();
        }));
    }

    public List<ContactData> getContactList() {
        return convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord", ContactRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from GroupRecord", Long.class).getSingleResult();
        });
    }

    public long getContactCount() {
        return sessionFactory.fromSession(session -> {
            return session.createQuery("select count (*) from ContactRecord", Long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            // открываем транзакцию, выполняем операцию и завершаем транзакцию
            session.getTransaction().begin();
            // метод используется для сохранения объекта в базу данных
            session.persist(convertToGroupRecord(groupData));
            session.getTransaction().commit();
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin();
            session.persist(convertToContactRecord(contactData));
            session.getTransaction().commit();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
        return sessionFactory.fromSession(session -> {
            return convertContactList(session.get(GroupRecord.class, group.id()).contacts);
        });
    }
}
