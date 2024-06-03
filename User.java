public class User {
    private String username;
    private String password; // For simplicity, storing plaintext passwords (not recommended for real applications)

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    public String getPassword() {
        return password;
    }
}
