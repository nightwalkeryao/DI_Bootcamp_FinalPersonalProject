package club.devs.api.dtos;

public class Login {
    private String username;
    private String password;

    public Login() {
    }

    public Login(String uname, String pwd) {
        this.setUsername(uname);
        this.setPassword(pwd);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}