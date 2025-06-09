package service;

import model.Task;
import storage.TaskStorage;

import java.util.List;

public class TaskService {
    public void createTask(String description, String category, String userName) {
        Task task = new Task(description, category, userName);
        TaskStorage.addTask(task);
    }

    public void listTasks(String userName) {
        List<Task> tasks = TaskStorage.getTasksByUser(userName);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + ": " + tasks.get(i));
        }
    }

    public void completeTask(int index, String userName) {
        TaskStorage.markTaskCompletedByUser(index, userName);
    }

    public void deleteTask(int index, String userName) {
        TaskStorage.removeTaskByUser(index, userName);
    }
}
