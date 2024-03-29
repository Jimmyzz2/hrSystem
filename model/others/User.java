package model.others;

public class User{
    private String username;
    private String password;
    private MailBox mailBox;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.mailBox = new MailBox(username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public MailBox getMailBox(){return mailBox;}

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return getUsername();
    }
}