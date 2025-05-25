class Task {
    // Constructor to initialize a task
    constructor(description, category, assignedTo) {
      this.description = description;
      this.category = category;
      this.assignedTo = assignedTo;
      this.createdAt = new Date();
    }
    
  }

  module.exports = Task;
