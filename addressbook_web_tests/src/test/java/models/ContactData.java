package models;

import java.util.Objects;

public final class ContactData {
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
        this(
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
                ""
        );
    }

    public ContactData withFirstName(String first_name) {
        // возвращает новый объект, только с first_name
        return new ContactData(
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

    public String First_name() {
        return first_name;
    }

    public String Middle_name() {
        return middle_name;
    }

    public String Last_name() {
        return last_name;
    }

    public String Nickname() {
        return nickname;
    }

    public String Title() {
        return title;
    }

    public String Company() {
        return company;
    }

    public String Address() {
        return address;
    }

    public String Home_phone() {
        return home_phone;
    }

    public String Mobile() {
        return mobile;
    }

    public String Work_phone() {
        return work_phone;
    }

    public String Fax() {
        return fax;
    }

    public String Email() {
        return email;
    }

    public String Email2() {
        return email2;
    }

    public String Email3() {
        return email3;
    }

    public String Homepage() {
        return homepage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ContactData) obj;
        return Objects.equals(this.first_name, that.first_name) &&
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
        return Objects.hash(first_name, middle_name, last_name, nickname, title, company, address, home_phone, mobile, work_phone, fax, email, email2, email3, homepage);
    }

    @Override
    public String toString() {
        return "ContactData[" +
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
