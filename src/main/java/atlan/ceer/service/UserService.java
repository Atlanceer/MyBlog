package atlan.ceer.service;

import atlan.ceer.model.UserInfSimple;

import java.util.Map;

public interface UserService {
    boolean login(String username, String password);
    boolean register();
    UserInfSimple getUserInfSimple(Map<String, Object> map);
}
