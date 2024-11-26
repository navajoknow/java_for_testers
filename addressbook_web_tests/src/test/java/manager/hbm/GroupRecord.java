package manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// класс-посредник, Data Transfer Object (DTO-класс), который мы используем, потому что Hibernate не работает
// с record (в последнем поля являются неизменяемыми (final))

@Entity
@Table(name = "group_list")
public class GroupRecord {

    // либо используем аннотацию, либо указываем для полей такие же имена, как у столбцов в таблице БД
    @Id
    @Column(name = "group_id")
    public int id;

    @Column(name = "group_name")
    public String name;

    @Column(name = "group_header")
    public String header;

    @Column(name = "group_footer")
    public String footer;

}
