import service.TaskService;
import model.User;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);
        ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
        User currentUser = null;

        System.out.println("Welcome to Collaborative To-Do List");

        while (true) {
            if (currentUser == null) {
                System.out.println("\nOptions: register | login | exit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine();
                switch (command) {
                    case "register":
                        System.out.print("Choose a username: ");
                        String regUser = scanner.nextLine();
                        if (users.containsKey(regUser)) {
                            System.out.println("Username already exists.");
                        } else {
                            users.put(regUser, new User(regUser));
                            System.out.println("Registration successful. You can now login.");
                        }
                        break;
                    case "login":
                        System.out.print("Username: ");
                        String loginUser = scanner.nextLine();
                        if (users.containsKey(loginUser)) {
                            currentUser = users.get(loginUser);
                            System.out.println("Login successful. Welcome, " + currentUser.getUsername() + "!");
                        } else {
                            System.out.println("User not found. Please register first.");
                        }
                        break;
                    case "exit":
                        System.out.println("Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid command.");
                }
                continue;
            }

            System.out.println("\nOptions: add | list | complete | delete | logout | exit");
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.print("Task description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Category: ");
                    String cat = scanner.nextLine();
                    service.createTask(desc, cat, currentUser.getUsername());
                    break;

                case "list":
                    service.listTasks(currentUser.getUsername());
                    break;

                case "complete":
                    try {
                        System.out.print("Task index: ");
                        int idComplete = Integer.parseInt(scanner.nextLine());
                        service.completeTask(idComplete, currentUser.getUsername());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter correct task index/number to complete.");
                        continue;
                    }
                    break;

                case "delete":
                    try {
                        System.out.print("Task index: ");
                        int idDelete = Integer.parseInt(scanner.nextLine());
                        service.deleteTask(idDelete, currentUser.getUsername());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter correct task index/number to delete.");
                        continue;
                    }
                    break;
                case "logout":
                    currentUser = null;
                    System.out.println("Logged out.");
                    break;
                case "exit":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid command.");
            }
        }
    }
}
