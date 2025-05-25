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
}
