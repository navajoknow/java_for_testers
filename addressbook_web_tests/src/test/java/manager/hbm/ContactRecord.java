package manager.hbm;

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
    @Column(name = "nickname")
    public String nickname;
    @Column(name = "photo")
    public String photo;
    @Column(name = "title")
    public String title;
    @Column(name = "company")
    public String company;
    @Column(name = "address")
    public String address;
    @Column(name = "home")
    public String home_phone;
    @Column(name = "mobile")
    public String mobile;
    @Column(name = "work")
    public String work_phone;
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
            String company,
            String address,
            String home_phone,
            String mobile,
            String work_phone,
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
        this.company = company;
        this.address = address;
        this.home_phone = home_phone;
        this.mobile = mobile;
        this.work_phone = work_phone;
        this.fax = fax;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.homepage = homepage;
    }
}