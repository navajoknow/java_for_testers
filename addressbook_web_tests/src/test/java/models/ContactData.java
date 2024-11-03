package models;

import java.util.Objects;

public final class ContactData {
    private final String First_name;
    private final String Middle_name;
    private final String Last_name;
    private final String Nickname;
    private final String Title;
    private final String Company;
    private final String Address;
    private final String Home_phone;
    private final String Mobile;
    private final String Work_phone;
    private final String Fax;
    private final String Email;
    private final String Email2;
    private final String Email3;
    private final String Homepage;

    // основной конструктор
    public ContactData(
            String First_name,
            String Middle_name,
            String Last_name,
            String Nickname,
            //Photo
            String Title,
            String Company,
            String Address,
            //
            String Home_phone,
            String Mobile,
            String Work_phone,
            String Fax,
            //
            String Email,
            String Email2,
            String Email3,
            String Homepage
            //Birthday
            //Anniversary
            //Group
    ) {
        this.First_name = First_name;
        this.Middle_name = Middle_name;
        this.Last_name = Last_name;
        this.Nickname = Nickname;
        this.Title = Title;
        this.Company = Company;
        this.Address = Address;
        this.Home_phone = Home_phone;
        this.Mobile = Mobile;
        this.Work_phone = Work_phone;
        this.Fax = Fax;
        this.Email = Email;
        this.Email2 = Email2;
        this.Email3 = Email3;
        this.Homepage = Homepage;
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
                this.Middle_name,
                this.Last_name,
                this.Nickname,
                this.Title,
                this.Company,
                this.Address,
                this.Home_phone,
                this.Mobile,
                this.Work_phone,
                this.Fax,
                this.Email,
                this.Email2,
                this.Email3,
                this.Homepage
        );
    }

    public String First_name() {
        return First_name;
    }

    public String Middle_name() {
        return Middle_name;
    }

    public String Last_name() {
        return Last_name;
    }

    public String Nickname() {
        return Nickname;
    }

    public String Title() {
        return Title;
    }

    public String Company() {
        return Company;
    }

    public String Address() {
        return Address;
    }

    public String Home_phone() {
        return Home_phone;
    }

    public String Mobile() {
        return Mobile;
    }

    public String Work_phone() {
        return Work_phone;
    }

    public String Fax() {
        return Fax;
    }

    public String Email() {
        return Email;
    }

    public String Email2() {
        return Email2;
    }

    public String Email3() {
        return Email3;
    }

    public String Homepage() {
        return Homepage;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ContactData) obj;
        return Objects.equals(this.First_name, that.First_name) &&
                Objects.equals(this.Middle_name, that.Middle_name) &&
                Objects.equals(this.Last_name, that.Last_name) &&
                Objects.equals(this.Nickname, that.Nickname) &&
                Objects.equals(this.Title, that.Title) &&
                Objects.equals(this.Company, that.Company) &&
                Objects.equals(this.Address, that.Address) &&
                Objects.equals(this.Home_phone, that.Home_phone) &&
                Objects.equals(this.Mobile, that.Mobile) &&
                Objects.equals(this.Work_phone, that.Work_phone) &&
                Objects.equals(this.Fax, that.Fax) &&
                Objects.equals(this.Email, that.Email) &&
                Objects.equals(this.Email2, that.Email2) &&
                Objects.equals(this.Email3, that.Email3) &&
                Objects.equals(this.Homepage, that.Homepage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(First_name, Middle_name, Last_name, Nickname, Title, Company, Address, Home_phone, Mobile, Work_phone, Fax, Email, Email2, Email3, Homepage);
    }

    @Override
    public String toString() {
        return "ContactData[" +
                "First_name=" + First_name + ", " +
                "Middle_name=" + Middle_name + ", " +
                "Last_name=" + Last_name + ", " +
                "Nickname=" + Nickname + ", " +
                "Title=" + Title + ", " +
                "Company=" + Company + ", " +
                "Address=" + Address + ", " +
                "Home_phone=" + Home_phone + ", " +
                "Mobile=" + Mobile + ", " +
                "Work_phone=" + Work_phone + ", " +
                "Fax=" + Fax + ", " +
                "Email=" + Email + ", " +
                "Email2=" + Email2 + ", " +
                "Email3=" + Email3 + ", " +
                "Homepage=" + Homepage + ']';
    }

}
