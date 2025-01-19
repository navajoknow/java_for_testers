package ru.must.mantis.models;

public record UserData(String username, String email) {

    public UserData() {
        this("","");
    }

    public UserData withUsername(String username) {
        return new UserData(username, this.email);
    }

    public UserData withEmail(String email) {
        return new UserData(this.username, email);
    }

}
