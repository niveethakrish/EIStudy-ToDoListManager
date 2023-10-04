# EIStudy-ToDoListManager
This is a Java program that implements a simple To-Do List Manager with the following features:

1. **Add New Tasks**: Users can add tasks with descriptions and optional due dates.

2. **Mark Tasks as Completed**: Tasks can be marked as 'completed.'

3. **Delete Tasks**: Users can delete tasks.

4. **View Tasks**: Tasks can be viewed either all at once or filtered by 'completed' or 'pending' status.

5. **Undo/Redo**: The program supports undo and redo functionality using the Memento Pattern.

## Usage

1. Compile and run the `ToDoListManager.java` file.

2. Use the following commands to interact with the To-Do List Manager:

   - `addTask`: Add a new task with an optional due date. Command:`Add Task: "Buy Grocerries, Due Date: 23-10-2023"`
   - `markTaskCompleted`: Mark a task as completed. Command:`Mark Completed: "Buy Grocerries"`
   - `deleteTask`: Delete a task. Command:`Delete Task: "Buy Grocerries"`
   - `viewTasks all`: View all tasks. Command:`Show all`
   - `viewTasks completed`: View completed tasks. Command:`Show completed`
   - `viewTasks pending`: View pending tasks. Command:`Show pending`
   - `undo`: Undo the last action. Command:`undo`
   - `redo`: Redo the last undone action. Command:`redo`
