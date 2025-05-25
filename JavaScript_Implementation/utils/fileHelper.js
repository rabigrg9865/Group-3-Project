const fs = require('fs').promises;
const FILE_PATH = './tasks.json';

// This function reads data from a JSON file (tasks.json) and returns it as a JavaScript object.
async function readData() {
  try {
    const data = await fs.readFile(FILE_PATH, 'utf-8');
    return JSON.parse(data);
  } catch (err) {
    return { users: [], tasks: [] }; // Default structure
  }
}

// This function writes data to a JSON file (tasks.json) by converting the JavaScript object to a JSON string.
async function writeData(data) {
  await fs.writeFile(FILE_PATH, JSON.stringify(data, null, 2));
}

module.exports = { readData, writeData };
