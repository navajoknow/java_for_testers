package models;

import java.util.Objects;

public final class ContactData {
    private final String id;
    private final String first_name;
    private final String middle_name;
    private final String last_name;
    private final String nickname;
    private final String title;
    private final String company;
    private final String address;
    private final String home_phone;
    private final String mobile;
    private final String work_phone;
    private final String fax;
    private final String email;
    private final String email2;
    private final String email3;
    private final String homepage;

    // основной конструктор
    public ContactData(
            String id,
            String first_name,
            String middle_name,
            String last_name,
            String nickname,
            //Photo
            String title,
            String company,
            String address,
            //
            String home_phone,
            String mobile,
            String work_phone,
            String fax,
            //
            String email,
            String email2,
            String email3,
            String homepage
            //Birthday
            //Anniversary
            //Group
    ) {
        this.id = id;
        this.first_name = first_name;
        this.middle_name = middle_name;
        this.last_name = last_name;
        this.nickname = nickname;
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

    // конструктор по умолчанию, вызывающий основной конструктор
    public ContactData() {
        this("",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "",
                "");
    }

    public ContactData withId(String id) {
        // возвращает новый объект, только с id
        return new ContactData(
                id,
                this.first_name,
                this.middle_name,
                this.last_name,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.home_phone,
                this.mobile,
                this.work_phone,
                this.fax,
                this.email,
                this.email2,
                this.email3,
                this.homepage);
    }

    public ContactData withFirstName(String first_name) {
        return new ContactData(
                this.id,
                first_name,
                this.middle_name,
                this.last_name,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.home_phone,
                this.mobile,
                this.work_phone,
                this.fax,
                this.email,
                this.email2,
                this.email3,
                this.homepage
        );
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(
                this.id,
                this.first_name,
                middle_name,
                this.last_name,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.home_phone,
                this.mobile,
                this.work_phone,
                this.fax,
                this.email,
                this.email2,
                this.email3,
                this.homepage
        );
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(
                this.id,
                this.first_name,
                this.middle_name,
                last_name,
                this.nickname,
                this.title,
                this.company,
                this.address,
                this.home_phone,
                this.mobile,
                this.work_phone,
                this.fax,
                this.email,
                this.email2,
                this.email3,
                this.homepage
        );
    }

    public String id() {
        return id;
    }

    public String first_name() {
        return first_name;
    }

    public String middle_name() {
        return middle_name;
    }

    public String last_name() {
        return last_name;
    }

    public String nickname() {
        return nickname;
    }

    public String title() {
        return title;
    }

    public String company() {
        return company;
    }

    public String address() {
        return address;
    }

    public String home_phone() {
        return home_phone;
    }

    public String mobile() {
        return mobile;
    }

    public String work_phone() {
        return work_phone;
    }

    public String fax() {
        return fax;
    }

    public String email() {
        return email;
    }

    public String email2() {
        return email2;
    }

    public String email3() {
        return email3;
    }

    public String homepage() {
        return homepage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ContactData) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.first_name, that.first_name) &&
                Objects.equals(this.middle_name, that.middle_name) &&
                Objects.equals(this.last_name, that.last_name) &&
                Objects.equals(this.nickname, that.nickname) &&
                Objects.equals(this.title, that.title) &&
                Objects.equals(this.company, that.company) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.home_phone, that.home_phone) &&
                Objects.equals(this.mobile, that.mobile) &&
                Objects.equals(this.work_phone, that.work_phone) &&
                Objects.equals(this.fax, that.fax) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.email2, that.email2) &&
                Objects.equals(this.email3, that.email3) &&
                Objects.equals(this.homepage, that.homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first_name, middle_name, last_name, nickname, title, company, address, home_phone, mobile, work_phone, fax, email, email2, email3, homepage);
    }

    @Override
    public String toString() {
        return "ContactData[" +
                "Id=" + id + ", " +
                "First_name=" + first_name + ", " +
                "Middle_name=" + middle_name + ", " +
                "Last_name=" + last_name + ", " +
                "Nickname=" + nickname + ", " +
                "Title=" + title + ", " +
                "Company=" + company + ", " +
                "Address=" + address + ", " +
                "Home_phone=" + home_phone + ", " +
                "Mobile=" + mobile + ", " +
                "Work_phone=" + work_phone + ", " +
                "Fax=" + fax + ", " +
                "Email=" + email + ", " +
                "Email2=" + email2 + ", " +
                "Email3=" + email3 + ", " +
                "Homepage=" + homepage + ']';
    }

}
