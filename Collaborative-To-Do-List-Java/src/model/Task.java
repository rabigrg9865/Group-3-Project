package model;

public class Task {
    private String description;
    private boolean completed;
    private String category;
    private String userName;

    public Task(String description, String category, String userName) {
        this.description = description;
        this.category = category;
        this.userName = userName;
        this.completed = false;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public String toString() {
        return "[ " + (completed ? "X" : " ") + " ] " + description + " | Category: " + category + " | Assigned to: " + userName;
    }
}
