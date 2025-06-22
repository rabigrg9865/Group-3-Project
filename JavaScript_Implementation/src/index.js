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
 try{
 const { username } = await inquirer.prompt({
    type: 'input',
    name: 'username',
    message: 'Enter your username:',
    validate: input => input ? true : 'Username cannot be empty.' // ✅ Input validation

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
    // Add Task
    if (action === 'Add Task') {
      const { desc, cat } = await inquirer.prompt([
        { type: 'input', name: 'desc', message: 'Task description:' },
        { type: 'input', name: 'cat', message: 'Category:' }
      ]);

      if (!desc || !cat) {
          console.log('Description and Category are required.');
          continue;
        }

      await manager.addTask(desc, cat, username);
      console.log('Task added.');

     // List Tasks
    } else if (action === 'List Tasks') {

      const tasks = await manager.listTasks(username);
      if (tasks.length === 0) {
          console.log('No tasks found.');
        } else {
          console.log(tasks.map((t, i) =>
            `${i + 1}. [${getStatusLabel(t.status)}] ${t.description} (${t.category})`
          ).join('\n'));
        }

      // Start Task
    } else if (action === 'Start Task') {

     const tasks = await manager.listTasks(username);
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to start:',
        validate: i => {
            const n = parseInt(i, 10);
            return !isNaN(n) && n > 0 && n <= tasks.length
              ? true
              : `Enter a number between 1 and ${tasks.length}`;
          }
      });

       const success = await manager.startTask(parseInt(index, 10) - 1, username);
       if (success) console.log('✅ Task started.');
       else console.log('Failed to start task (maybe already completed).');

    // Complete Task
    } else if (action === 'Complete Task') {
      const tasks = await manager.listTasks(username);
      if (tasks.length === 0) {
          console.log('No tasks available to complete.');
          continue;
      }
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to mark complete:',
        validate: i => {
            const n = parseInt(i, 10);
            return !isNaN(n) && n > 0 && n <= tasks.length
              ? true
              : `Enter a number between 1 and ${tasks.length}`;
          }


      });

      const success = await manager.completeTask(parseInt(index, 10) - 1, username);
      if (success) console.log('Task marked as complete.');
      else console.log('Task was already completed or invalid.');

    // Remove Task
    } else if (action === 'Remove Task') {
      const tasks = await manager.listTasks(username);
      if (tasks.length === 0) {
          console.log('No tasks to remove.');
          continue;
      }
      const { index } = await inquirer.prompt({
        type: 'input',
        name: 'index',
        message: 'Enter task number to remove:',
        validate: i => {
            const n = parseInt(i, 10);
            return !isNaN(n) && n > 0 && n <= tasks.length
              ? true
              : `Enter a number between 1 and ${tasks.length}`;
        }
      });

      const success = await manager.removeTask(parseInt(index, 10) - 1, username);
      if (success) console.log('Task removed.');
      else console.log('Could not remove task.');

    // list By Category
    } else if (action === 'List by Category') {
      const { category } = await inquirer.prompt({
        type: 'input',
        name: 'category',
        message: 'Enter category to filter:',
        validate: input => input.trim() ? true : 'Category cannot be empty.'

      });

      const tasks = await manager.listByCategory(category);
      if (tasks.length === 0) {
          console.log(`ℹ️ No tasks found in category "${category}".`);
      } else {
          console.log(tasks.map((t, i) =>
            `${i + 1}. [${getStatusLabel(t.status)}] ${t.description} (${t.category})`
          ).join('\n'));
      }

    // Remove User
    } else if (action === 'Remove User') {
      const { username: delUser } = await inquirer.prompt({
        type: 'input',
        name: 'username',
        message: 'Enter the username to remove:',
        validate: input => input.trim() ? true : 'Username cannot be empty.'

      });

      const success = await manager.removeUser(delUser);
      if (success) console.log(`User "${delUser}" removed.`);
      else console.log(`User "${delUser}" not found.`);
    // Exit
    } else if (action === 'Exit') {
      console.log('👋 Goodbye!');
      break;
    }
  }
 } catch (error) {
    console.error('An error occurred in the application:', error.message);
  }

}
main();
