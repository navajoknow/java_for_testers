package models;

public record ContactData (String id, String first_name, String middle_name, String last_name, String photo) {

    public ContactData() {
        this("", "", "", "", "");
    }

    public ContactData withId(String id) {
        // возвращает новый объект, только с id
        return new ContactData(id, this.first_name, this.middle_name, this.last_name, this.photo);
    }

    public ContactData withFirstName(String first_name) {
        return new ContactData(this.id, first_name, this.middle_name, this.last_name, this.photo);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(this.id, this.first_name, middle_name, this.last_name, this.photo);
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(this.id, this.first_name, this.middle_name, last_name, this.photo);
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.first_name, this.middle_name, this.last_name, photo);
    }
}
