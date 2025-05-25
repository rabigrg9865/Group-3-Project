const { readData, writeData } = require('../../utils/fileHelper.js');
const Task = require('../models/Task');
const User = require('../models/User');

// This class manages tasks and users in the application.
class TaskManager {

    // Method to add a new user
  async addUser(username) {
    const data = await readData();
    if (data.users.includes(username)) return false;
    data.users.push(username);
    await writeData(data);
    return true;
  }

  // Method to check if a user exists
  async addTask(description, category, username) {
    const data = await readData();
    const task = new Task(description, category, username, 'Pending');
    data.tasks.push(task);
    await writeData(data);
  }

  // Method to list tasks assigned to a user
  async listTasks(username) {
    const data = await readData();
    return data.tasks.filter(t => t.assignedTo === username);
  }

// Method to remove a user and their tasks
async removeUser(username) {    
    const data = await readData();
    if (!data.users.includes(username)) return false;
    data.users.splice(data.users.indexOf(username), 1);
    data.tasks = data.tasks.filter(t => t.assignedTo !== username);
    await writeData(data);
    return true;

}

}

module.exports = TaskManager;
