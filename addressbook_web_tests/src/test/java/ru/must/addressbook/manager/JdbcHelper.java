package ru.must.addressbook.manager;

import ru.must.addressbook.models.ContactData;
import ru.must.addressbook.models.GroupData;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcHelper extends HelperBase {

    public JdbcHelper(ApplicationManager manager) {
        super(manager);
    }

    public List<GroupData> getGroupList() {
        var groups = new ArrayList<GroupData>();
        // конструкция try-with-resources для автоматического закрытия ресурсов (Connection, Statement, ResultSet)
        // при выходе из блока try, независимо от того, завершается блок успешно или выбрасывается исключение
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             // выполняем запрос к БД
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list")) {
            // читаем строки результата запроса (ResultSet); после завершения работы соответствующие
            // ресурсы будут автоматически закрыты в обратном порядке: сначала ResultSet, потом Statement, потом Connection
            while (result.next()) {
                groups.add(new GroupData()
                        .withId(result.getString("group_id"))
                        .withName(result.getString("group_name"))
                        .withHeader(result.getString("group_header"))
                        .withFooter(result.getString("group_footer")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return groups;
    }

    public List<ContactData> getContactList() {
        var contacts = new ArrayList<ContactData>();
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery("SELECT id, firstname, middlename, lastname, photo FROM addressbook")) {
            while (result.next()) {
                contacts.add(new ContactData()
                        .withId(result.getString("id"))
                        .withFirstName(result.getString("firstname"))
                        .withMiddleName(result.getString("middlename"))
                        .withLastName(result.getString("lastname"))
                        .withPhoto(result.getString("photo")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return contacts;
    }

    public void checkConsistency() {
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root", "");
             var statement = conn.createStatement();
             var result = statement.executeQuery(
                     "SELECT * FROM address_in_groups ag LEFT JOIN addressbook ab on ag.id = ab.id WHERE ab.id is NULL")) {
            if (result.next()) {
                throw new IllegalStateException("DB is corrupted");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
