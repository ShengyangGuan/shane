public interface UserAuthentication {
    void register(String username, String password);
    boolean authenticateUser(String username, String password);
}
