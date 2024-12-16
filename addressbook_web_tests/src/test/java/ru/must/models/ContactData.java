package ru.must.models;

public record ContactData (String id, String first_name, String middle_name, String last_name, String photo,
                           String home, String mobile, String work, String phone2) {

    public ContactData() {
        this("", "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        // возвращает новый объект, только с id
        return new ContactData(id, this.first_name, this.middle_name, this.last_name, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withFirstName(String first_name) {
        return new ContactData(this.id, first_name, this.middle_name, this.last_name, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.id, this.first_name, middle_name, this.last_name, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withHome(String home) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.photo, home, this.mobile, this.work, this.phone2);
    }

    public ContactData withMobile(String mobile) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.photo, this.home, mobile, this.work, this.phone2);
    }

    public ContactData withWork(String work) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.photo, this.home, this.mobile, work, this.phone2);
    }

    public ContactData withPhone2(String phone2) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, this.photo, this.home, this.mobile, this.work, phone2);
    }

}
