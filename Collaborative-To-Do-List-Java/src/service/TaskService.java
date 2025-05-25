package service;

import model.Task;
import storage.TaskStorage;

import java.util.List;

public class TaskService {
    public void createTask(String description, String category, String userName) {
        Task task = new Task(description, category, userName);
        TaskStorage.addTask(task);
    }

    public void listTasks() {
        List<Task> tasks = TaskStorage.getAllTasks();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public void completeTask(int index) {
        TaskStorage.markTaskCompleted(index);
    }

    public void deleteTask(int index) {
        TaskStorage.removeTask(index);
    }
}
