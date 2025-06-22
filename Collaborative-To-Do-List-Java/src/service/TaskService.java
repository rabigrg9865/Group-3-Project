package service;

import model.Task;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class TaskService {
    private static final String FILE = "tasks.json";
    private List<Task> tasks = loadTasks();

    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(Task.fromJson(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    private static void saveTasks(List<Task> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE))) {
            for (Task t : tasks) {
                writer.println(t.toJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createTask(String description, String category, String userName) {
        Task task = new Task(description, category, userName);
        tasks.add(task);
        saveTasks(tasks);
    }

    public void listTasks(String userName) {
        List<Task> userTasks = tasks.stream()
            .filter(t -> t.getUserName().equalsIgnoreCase(userName))
            .collect(Collectors.toList());
        for (int i = 0; i < userTasks.size(); i++) {
            System.out.println(i + ": " + userTasks.get(i));
        }
    }

    public void completeTask(int index, String userName) {
        List<Task> userTasks = tasks.stream()
            .filter(t -> t.getUserName().equalsIgnoreCase(userName))
            .collect(Collectors.toList());
        if (index >= 0 && index < userTasks.size()) {
            Task taskToComplete = userTasks.get(index);
            taskToComplete.markCompleted();
            saveTasks(tasks);
        } else {
            System.out.println("Invalid task index.");
        }
    }

    public void deleteTask(int index, String userName) {
        List<Task> userTasks = tasks.stream()
            .filter(t -> t.getUserName().equalsIgnoreCase(userName))
            .collect(Collectors.toList());
        if (index >= 0 && index < userTasks.size()) {
            Task taskToDelete = userTasks.get(index);
            tasks.remove(taskToDelete);
            saveTasks(tasks);
        } else {
            System.out.println("Invalid task index.");
        }
    }
}
