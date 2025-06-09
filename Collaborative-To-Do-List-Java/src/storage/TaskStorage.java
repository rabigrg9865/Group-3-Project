package storage;

import model.Task;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class TaskStorage {
    private static final List<Task> taskList = new CopyOnWriteArrayList<>();

    public static void addTask(Task task) {
        taskList.add(task);
    }

    public static List<Task> getAllTasks() {
        return taskList;
    }

    public static void markTaskCompleted(int index) {
        if (index >= 0 && index < taskList.size()) {
            taskList.get(index).markCompleted();
            System.out.println("Task " + index + " marked as completed.");
        } else{
            System.out.println("Task " + index + " does not exist. Please enter valid index.");
        }
    }

    public static void removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
            System.out.println("Task " + index + " deleted.");
        } else{
            System.out.println("Task " + index + " does not exist. Please enter valid index.");
        }
    }

    public static List<Task> getTasksByUser(String userName) {
        List<Task> userTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.toString().contains("Assigned to: " + userName)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }

    public static void markTaskCompletedByUser(int index, String userName) {
        List<Task> userTasks = getTasksByUser(userName);
        if (index >= 0 && index < userTasks.size()) {
            Task task = userTasks.get(index);
            task.markCompleted();
            System.out.println("Task " + index + " marked as completed.");
        } else {
            System.out.println("Task " + index + " does not exist for user " + userName + ".");
        }
    }

    public static void removeTaskByUser(int index, String userName) {
        List<Task> userTasks = getTasksByUser(userName);
        if (index >= 0 && index < userTasks.size()) {
            Task task = userTasks.get(index);
            taskList.remove(task);
            System.out.println("Task " + index + " deleted.");
        } else {
            System.out.println("Task " + index + " does not exist for user " + userName + ".");
        }
    }
}
