package Test;

public class UserManagerTest {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();

        // Register users
        System.out.println("Registering alice: " + userManager.register("alice")); // true
        System.out.println("Registering bob: " + userManager.register("bob"));     // true
        System.out.println("Registering alice again: " + userManager.register("alice")); // false

        // Login as alice
        System.out.println("Login as alice: " + userManager.login("alice")); // true
        System.out.println("Current user: " + userManager.getCurrentUser().getUsername()); // alice

        // Logout
        userManager.logout();
        System.out.println("Current user after logout: " + userManager.getCurrentUser()); // null

        // Login as bob
        System.out.println("Login as bob: " + userManager.login("bob")); // true
        System.out.println("Current user: " + userManager.getCurrentUser().getUsername()); // bob

        // Try login with non-existent user
        System.out.println("Login as charlie: " + userManager.login("charlie")); // false
    }
}
