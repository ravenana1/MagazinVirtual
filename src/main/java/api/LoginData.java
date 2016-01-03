package api;

/**
 * Created by ana on 02.01.2016.
 */
public class LoginData {
    private String email;
    private String password;

    public LoginData() {
    }

    public LoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
