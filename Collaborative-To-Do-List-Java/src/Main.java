import service.TaskService;
import service.UserManager;
import model.User;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        UserManager userManager = new UserManager();
        Scanner scanner = new Scanner(System.in);
        User currentUser = null;

        System.out.println("Welcome to Collaborative To-Do List");

        while (true) {
            if (currentUser == null) {
                System.out.println("\nOptions: register | login | exit");
                System.out.print("Enter command: ");
                String command = scanner.nextLine().trim();

                switch (command) {
                    case "register":
                        System.out.print("Choose a username: ");
                        String regUser = scanner.nextLine().trim();
                        if (!userManager.register(regUser)) {
                            System.out.println("Username already exists.");
                        } else {
                            System.out.println("Registration successful. You can now login.");
                        }
                        break;

                    case "login":
                        System.out.print("Username: ");
                        String loginUser = scanner.nextLine().trim();
                        if (userManager.login(loginUser)) {
                            currentUser = userManager.getCurrentUser();
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
            String command = scanner.nextLine().trim();

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
                        System.out.println("Invalid input! Please enter a valid task number.");
                    }
                    break;

                case "delete":
                    try {
                        System.out.print("Task index: ");
                        int idDelete = Integer.parseInt(scanner.nextLine());
                        service.deleteTask(idDelete, currentUser.getUsername());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a valid task number.");
                    }
                    break;

                case "logout":
                    userManager.logout();
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
