package ru.must.addressbook.models;

public record ContactData (String id, String first_name, String middle_name,
                           String last_name, String nickname, String photo,
                           String title, String home, String mobile, String work,
                           String phone2, String address, String company,
                           String fax, String email, String email2, String email3,
                           String homepage) {

    public ContactData() {
        this(
                "", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        // возвращает новый объект, только с id
        return new ContactData(id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withFirstName(String first_name) {
        return new ContactData(this.id, first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.id, this.first_name, middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withNickname(String nickname) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, this.nickname, photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withTitle(String title) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, this.nickname, this.photo, title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, phone2, this.address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, address, this.company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withCompany(String company) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, company, this.fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withFax(String fax) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, fax, this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withEmail(String email) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, email, this.email2, this.email3, this.homepage);
    }

    public ContactData withEmail2(String email2) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, email2, this.email3, this.homepage);
    }

    public ContactData withEmail3(String email3) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, this.company, this.fax, this.email, this.email2, email3, this.homepage);
    }

    public ContactData withHomepage(String homepage) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.home, this.mobile,
                this.work, this.phone2, this.address, company, this.fax, this.email, this.email2, this.email3, homepage);
    }

}
