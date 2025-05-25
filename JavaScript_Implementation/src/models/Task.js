class Task {
    // Constructor to initialize a task
    constructor(description, category, assignedTo, status = 'Pending') {
      this.description = description;
      this.category = category;
      this.assignedTo = assignedTo;
      this.status = status;
      this.createdAt = new Date();
      this.updatedAt = new Date();
    }

    // Method to update the task's status as Complete
    markComplete() {
      this.status = 'Completed';
      this.updatedAt = new Date();
    }

    // Method to update the task's status as In progress
    startTask() {
    this.status = 'In Progress';
    this.updatedAt = new Date();
    }
    
  }

  module.exports = Task;
