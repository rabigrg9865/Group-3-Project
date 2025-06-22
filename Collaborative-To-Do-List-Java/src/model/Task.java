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

    public boolean isCompleted() {
        return completed;
    }

    public String getUserName() {
        return userName;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String toJson() {
        return String.format(
            "{\"description\":\"%s\",\"category\":\"%s\",\"userName\":\"%s\",\"completed\":%b}",
            description, category, userName, completed
        );
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
        return "[ " + (completed ? "✓" : " ") + " ] " + description + " | Category: " + category + " | Assigned to: " + userName;
    }
}
