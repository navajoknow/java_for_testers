package ru.must.addressbook.manager.hbm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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

    // в таблице БД есть атрибут "deprecated", у которого отсутствует значение по умолчанию, поэтому необходимо
    // добавить это поле в класс-посредник; при создании объета в данное поле попадет текущая дата и время
    @Column(name = "deprecated")
    public Date deprecated = new Date();

    @ManyToMany
    // промежуточная таблица, которая реализует связь "многие ко многим"
    @JoinTable (name = "address_in_groups",
            // столбец, который ссылается на текущую сущность (группу)
            joinColumns = @JoinColumn (name = "group_id"),
            // столбец, который ссылается на связанную сущность (контакт)
            inverseJoinColumns = @JoinColumn (name = "id"))
    public List<ContactRecord> contacts;

    public GroupRecord() {
    }

    public GroupRecord(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }
}
