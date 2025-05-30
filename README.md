# Group-3-Project

# 📝 Collaborative To-Do List Application

## Overview
Create a command-line or GUI-based to-do list that supports multiple users. Each user can add, remove, or mark tasks as complete, and the application should allow tasks to be categorized and assigned to users.

---

## Core Requirements

1. **Data storage** for tasks and user-specific views of tasks.

2. **Task categorization and status tracking** (e.g., completed, pending).

3. **Support for concurrency**, so multiple users can access and edit tasks concurrently.

---

## Language-Specific Feature Examples

### Java
- Use **classes** for users and tasks
- Demonstrate **object-oriented principles**
- Implement concurrency using **threads**

### JavaScript
- Use **asynchronous operations** (like Promises or async/await) to simulate concurrent access
- Employ **JSON** for data storage

# Collaborative To-Do List (Java CLI)

### Features:
- Adds, completes, deletes tasks
- Categorizes and assigns tasks to users

### How to Run:
```bash
javac -d out src\model\*.java src\service\*.java src\storage\*.java src\Main.java
java -cp src Main
```

# Collaborative To-Do List (Javascript CLI)

### Features:
- Add, List, Start, Complete, Remove, and List tasks by category
- Categorizes and assigns tasks to each user
- Allows concurrent users
- Stores data in a json file

### How to Run:
```bash
npm install
node src/index.js
```
