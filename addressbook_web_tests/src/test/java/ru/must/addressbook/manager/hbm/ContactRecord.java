package ru.must.addressbook.manager.hbm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactRecord {

    @Id
    @Column(name = "id")
    public int id;

    @Column(name = "firstname")
    public String first_name;

    @Column(name = "middlename")
    public String middle_name;

    @Column(name = "lastname")
    public String last_name;

    @Column(name = "photo")
    public String photo;

    // далее добавляем поля, которые требуются для создания записи в таблице БД
    // (соответствующие атрибуты в БД не могут быть null и не имеют значения по умолчанию)
    // ВАЖНО: пустая строка ("") — это не то же самое, что null, это именно строка длиной 0 символов,
    // которая существует в памяти; в базе данных будет храниться строка, но без содержания

    @Column(name = "nickname")
    public String nickname;

    @Column(name = "company")
    public String company;

    @Column(name = "title")
    public String title;

    @Column(name = "address")
    public String address;

    @Column(name = "home")
    public String home;

    @Column(name = "mobile")
    public String mobile;

    @Column(name = "work")
    public String work;

    @Column(name = "phone2")
    public String phone2;

    @Column(name = "fax")
    public String fax;

    @Column(name = "email")
    public String email;

    @Column(name = "email2")
    public String email2;

    @Column(name = "email3")
    public String email3;

    @Column(name = "homepage")
    public String homepage;

    public ContactRecord() {
    }

    public ContactRecord(
            int id,
            String first_name,
            String middle_name,
            String last_name,
            String nickname,
            String photo,
            String title,
            String address,
            String company,
            String home,
            String mobile,
            String phone2,
            String work,
            String fax,
            String email,
            String email2,
            String email3,
            String homepage) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nickname = nickname;
        this.photo = photo;
        this.title = title;
        this.address = address;
        this.company = company;
        this.home = home;
        this.mobile = mobile;
        this.phone2 = phone2;
        this.work = work;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }
}