package service;

import model.User;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
    private ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private User currentUser = null;
    private static final String FILE = "users.json";

    // Load users when object is created
    public UserManager() {
        loadUsers(); 
    }

    public boolean register(String username) {
        if (users.containsKey(username)) {
            return false;
        }
        users.put(username, new User(username));
        // Save to file
        saveUsers();  
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

    private void saveUsers() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            for (String username : users.keySet()) {
                writer.println(username);
            }
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    private void loadUsers() {
        File file = new File(FILE);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.put(line.trim(), new User(line.trim()));
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }
}
