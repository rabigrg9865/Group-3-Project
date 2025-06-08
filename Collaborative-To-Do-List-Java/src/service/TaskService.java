package service;

import model.Task;
import storage.TaskStorage;

import java.io.*;
import java.util.*;

public class TaskService {
    private List<Task> tasks = loadTasks();
    private static final String FILE = "tasks.json";

    // Inner Task class
    public static class Task {
        private String description;
        private String category;
        private String userName;
        private boolean completed;

        public Task(String description, String category, String userName) {
            this.description = description;
            this.category = category;
            this.userName = userName;
            this.completed = false;
        }

        public void markCompleted() {
            this.completed = true;
        }

        public String toJson() {
            return String.format("{\"description\":\"%s\",\"category\":\"%s\",\"userName\":\"%s\",\"completed\":%b}",
                    description, category, userName, completed);
        }

        public static Task fromJson(String json) {
            String[] parts = json.replaceAll("[{}\"]", "").split(",");
            String desc = parts[0].split(":")[1];
            String cat = parts[1].split(":")[1];
            String user = parts[2].split(":")[1];
            boolean comp = Boolean.parseBoolean(parts[3].split(":")[1]);
            Task t = new Task(desc, cat, user);
            if (comp) t.markCompleted();
            return t;
        }

        @Override
        public String toString() {
            return "[ " + (completed ? "✓" : " ") + " ] " + description + " (" + category + ") - " + userName;
        }
    }

    // Storage methods
    private static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        File file = new File(FILE);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
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
        try (PrintWriter out = new PrintWriter(new FileWriter(FILE))) {
            for (Task t : tasks) {
                out.println(t.toJson());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Public task methods
    public void createTask(String desc, String cat, String user) {
        tasks.add(new Task(desc, cat, user));
        saveTasks(tasks);
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public void completeTask(int index) {
        tasks.get(index).markCompleted();
        saveTasks(tasks);
    }

    public void deleteTask(int index) {
        tasks.remove(index);
        saveTasks(tasks);
    }
}
