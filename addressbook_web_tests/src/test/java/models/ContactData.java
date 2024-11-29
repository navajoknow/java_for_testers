package models;

public record ContactData (
            String id,
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

    public ContactData() {
        this("", "", "", "", "", "", "", "", "",
                "", "", "", "", "", "", "", "");
    }

    public ContactData withId(String id) {
        // возвращает новый объект, только с id
        return new ContactData(
                id,
                this.first_name, this.middle_name, this.last_name, this.nickname, this.photo, this.title,
                this.company, this.address, this.home_phone, this.mobile, this.work_phone, this.fax,
                this.email, this.email2, this.email3, this.homepage);
    }

    public ContactData withFirstName(String first_name) {
        return new ContactData(
                this.id,
                first_name,
                this.middle_name, this.last_name, this.nickname, this.photo, this.title, this.company,
                this.address, this.home_phone, this.mobile, this.work_phone, this.fax, this.email,
                this.email2, this.email3, this.homepage);
    }

    public ContactData withMiddleName(String middle_name) {
        return new ContactData(
                this.id, this.first_name,
                middle_name,
                this.last_name, this.nickname, this.photo, this.title, this.company, this.address,
                this.home_phone, this.mobile, this.work_phone, this.fax, this.email, this.email2,
                this.email3, this.homepage
        );
    }

    public ContactData withLastName(String last_name) {
        return new ContactData(
                this.id, this.first_name, this.middle_name,
                last_name,
                this.nickname, this.photo, this.title, this.company, this.address, this.home_phone,
                this.mobile, this.work_phone, this.fax, this.email, this.email2, this.email3, this.homepage
        );
    }

    public ContactData withPhoto(String photo) {
        return new ContactData(
                this.id, this.first_name, this.middle_name, this.last_name, this.nickname,
                photo,
                this.title, this.company, this.address, this.home_phone, this.mobile, this.work_phone,
                this.fax, this.email, this.email2, this.email3, this.homepage
        );
    }
}
