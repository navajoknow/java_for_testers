package manager;

import models.GroupData;

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
        try (var conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook", "root","");
             var statement = conn.createStatement();
             // выполняем запрос к БД
             var result = statement.executeQuery("SELECT group_id, group_name, group_header, group_footer FROM group_list"))
        {
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
}
