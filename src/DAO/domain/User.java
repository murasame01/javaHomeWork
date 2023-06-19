package DAO.domain;

public class User {
    private String account;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    public User(String account, String password, String email){
        this.account = account;
        this.password = password;
    }
    public User(String account, String password){
        this.account = account;
        this.password = password;
        this.email = "";
    }
    public User(){

    }

    @Override
    public String toString() {
        return "User{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {this.password = password;}
}
