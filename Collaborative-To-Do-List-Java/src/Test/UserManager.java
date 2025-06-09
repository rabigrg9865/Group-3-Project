package Test;

import model.User;
import java.util.HashMap;

public class UserManager {
    private HashMap<String, User> users = new HashMap<>();
    private User currentUser = null;

    public boolean register(String username) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username));
        return true;
    }

    public boolean login(String username) {
        if (users.containsKey(username)) {
            currentUser = users.get(username);
            return true;
        }
        return false;
    }

    public void logout() {
        currentUser = null;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean userExists(String username) {
        return users.containsKey(username);
    }
}
