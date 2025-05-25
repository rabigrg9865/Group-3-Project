import service.TaskService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        TaskService service = new TaskService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Collaborative To-Do List");

        while (true) {
            System.out.println("\nOptions: add | list | complete | delete | exit");
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            switch (command) {
                case "add":
                    System.out.print("Task description: ");
                    String desc = scanner.nextLine();
                    System.out.print("Category: ");
                    String cat = scanner.nextLine();
                    System.out.print("Enter the username to assign: ");
                    String user = scanner.nextLine();
                    service.createTask(desc, cat, user);
                    break;

                case "list":
                    service.listTasks();
                    break;

                case "complete":
                    try {
                        System.out.print("Task index: ");
                        int idComplete = Integer.parseInt(scanner.nextLine());
                        service.completeTask(idComplete);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter correct task index/number to complete.");
                        continue;
                    }
                    break;

                case "delete":
                    try {
                        System.out.print("Task index: ");
                        int idDelete = Integer.parseInt(scanner.nextLine());
                        service.deleteTask(idDelete);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter correct task index/number to delete.");
                        continue;
                    }
                    break;

                case "exit":
                    return;

                default:
                    System.out.println("Invalid command! Please select one of these options: add | list | complete | delete | exit");
            }
        }
    }
}
