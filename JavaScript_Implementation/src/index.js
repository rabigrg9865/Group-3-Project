//Authors: Bibek Itani, Rabi Gurung
//Date: 2025-05-25
// Course: Advanced Programming Languages
// This is a simple task manager application that allows users to add, list, start, complete, and remove tasks.

const inquirer = require('inquirer');
const TaskManager = require('./Services/TaskManager.js');

const manager = new TaskManager();

// This function returns the status label for the task
function getStatusLabel(status) {
  switch (status) {
    case 'Pending': return '! Pending';
    case 'In Progress': return '~ In Progress';
    case 'Completed': return '✓ Completed';
    default: return '? Unknown';
  }
}

// This function initializes the application and prompts the user for input.
async function main() {
  const { username } = await inquirer.prompt({
    type: 'input',
    name: 'username',
    message: 'Enter your username:',
  });

  await manager.addUser(username);

  while (true) {
    // Prompt the user for an action
    const { action } = await inquirer.prompt({
      type: 'list',
      name: 'action',
      message: 'What do you want to do?',
      choices: ['Add Task', 'List Tasks', 'Start Task', 'Complete Task', 'Remove Task', 'List by Category', 'Remove User','Exit']
    });

    // Perform the action based on user input
    if (action === 'Add Task') {
      const { desc, cat } = await inquirer.prompt([
        { type: 'input', name: 'desc', message: 'Task description:' },
        { type: 'input', name: 'cat', message: 'Category:' }
      ]);

      await manager.addTask(desc, cat, username);

    } else if (action === 'List Tasks') {

      const tasks = await manager.listTasks(username);
      console.log(tasks.map((t, i) => `${i + 1}. [${getStatusLabel(t.status)}] ${t.description} (${t.category})`).join('\n'));

    } else if (action === 'Start Task') {

     const tasks = await manager.listTasks(username);
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to start:'
      });

      await manager.startTask(parseInt(index) - 1, username);
      console.log('Task started.');

    } else if (action === 'Complete Task') {
      const tasks = await manager.listTasks(username);
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to mark complete:'

      });

      await manager.completeTask(parseInt(index) - 1, username);
      console.log('Task marked as complete.');

    } else if (action === 'Remove Task') {
      const tasks = await manager.listTasks(username);
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to remove:'
      });

      await manager.removeTask(parseInt(index) - 1, username);

    } else if (action === 'List by Category') {
      const { category } = await inquirer.prompt({
        type: 'input',
        name: 'category',
        message: 'Enter category to filter:'
      });

      const tasks = await manager.listByCategory(category);
      
      console.log(tasks.map((t, i) => `${i + 1}. [${getStatusLabel(t.status)}] ${t.description} (${t.category})`).join('\n'));

    } else if (action === 'Remove User') {
      const { username } = await inquirer.prompt({
        type: 'input',
        name: 'username',
        message: 'Enter the username to remove:'
      });

      await manager.removeUser(username);
      console.log(`User ${username} removed.`);
    } else if (action === 'Exit') {
      break;
    }
  }
}
main();
