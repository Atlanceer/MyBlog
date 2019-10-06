package atlan.ceer.service;

public interface UserService {
    boolean login(String username, String password);
    boolean register();
}
