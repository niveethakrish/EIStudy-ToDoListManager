import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Scanner;

class Task {
    private String description;
    private boolean completed;
    private String dueDate;

    private Task(String description, boolean completed, String dueDate) {
        this.description = description;
        this.completed = completed;
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted() {
        this.completed = true;
    }

    public String getDueDate() {
        return dueDate;
    }

    @Override
    public String toString() {
        return description + " - " + (completed ? "Completed" : "Pending") + ", Due: " + dueDate;
    }

    public static class TaskBuilder {
        private String description;
        private boolean completed = false;
        private String dueDate = "";

        public TaskBuilder(String description) {
            this.description = description;
        }

        public TaskBuilder setCompleted(boolean completed) {
            this.completed = completed;
            return this;
        }

        public TaskBuilder setDueDate(String dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Task build() {
            return new Task(description, completed, dueDate);
        }
    }
}

class TaskList {
    private List<Task> tasks = new ArrayList<>();
    private Stack<List<Task>> history = new Stack<>();
    private int currentHistoryIndex = -1;

    public void addTask(Task task) {
        tasks.add(task);
        saveToHistory();
    }

    public void markTaskCompleted(String description) {
        for (Task task : tasks) {
            if (task.getDescription().equals(description)) {
                task.markCompleted();
                saveToHistory();
                return;
            }
        }
    }

    public void deleteTask(String description) {
        tasks.removeIf(task -> task.getDescription().equals(description));
        saveToHistory();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void undo() {
        if (canUndo()) {
            currentHistoryIndex--;
            tasks = new ArrayList<>(history.get(currentHistoryIndex));
        }
    }

    public void redo() {
        if (canRedo()) {
            currentHistoryIndex++;
            tasks = new ArrayList<>(history.get(currentHistoryIndex));
        }
    }

    public boolean canUndo() {
        return currentHistoryIndex > 0;
    }

    public boolean canRedo() {
        return currentHistoryIndex < history.size() - 1;
    }

    private void saveToHistory() {
        if (currentHistoryIndex < history.size() - 1) {
            history.subList(currentHistoryIndex + 1, history.size()).clear();
        }
        history.push(new ArrayList<>(tasks));
        currentHistoryIndex++;
    }
}

class ToDoListManager {
    static String getTaskFromInput(String input){
        String[] inputSplit = input.split(": ", 2);
        String task = inputSplit[1].substring(1, inputSplit[1].length() - 1);
        return task;
    }
    public static void main(String[] args) {
        TaskList taskList = new TaskList();
        boolean proceed = true;
        while(proceed){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter the command:");
            String input = scanner.nextLine();
            if(input.toLowerCase().startsWith("add task")){
                try{
                String inputTask = ToDoListManager.getTaskFromInput(input);
                String taskName = inputTask.split(",",2)[0];
                String dueDate = inputTask.split(",",2)[1].split(": ",2)[1];
                Task task1 = new Task.TaskBuilder(taskName)
                .setDueDate(dueDate)
                .build();
                taskList.addTask(task1);
                }
                catch (IndexOutOfBoundsException e){
            System.out.println("Please check the format of the input");
        }
            }
            else if(input.toLowerCase().startsWith("mark completed")){
                try{                String inputTask = ToDoListManager.getTaskFromInput(input);
                taskList.markTaskCompleted(inputTask);
                }
                catch (IndexOutOfBoundsException e){
            System.out.println("Please check the format of the input");
        }
            }
            else if(input.toLowerCase().startsWith("show all")){
                List<Task> allTasks = taskList.getTasks();
                System.out.println("All Tasks:");
                for (Task task : allTasks) {
                    System.out.println(task.toString());
                }
            }
            else if(input.toLowerCase().startsWith("show completed")){
                List<Task> allTasks = taskList.getTasks();
                System.out.println("Completed Tasks:");
                for (Task task : allTasks) {
                    if(task.isCompleted())
                        System.out.println(task.toString());
                }
            }
            else if(input.toLowerCase().startsWith("show pending")){
                List<Task> allTasks = taskList.getTasks();
                System.out.println("Pending Tasks:");
                for (Task task : allTasks) {
                    if(!task.isCompleted())
                        System.out.println(task.toString());
                }
            }
            else if(input.toLowerCase().startsWith("delete task")){
                try{
                String inputTask = ToDoListManager.getTaskFromInput(input);
                taskList.deleteTask(inputTask);
                }
                catch (IndexOutOfBoundsException e){
            System.out.println("Please check the format of the input");
        }
            }
            else if(input.toLowerCase().startsWith("undo")){
                taskList.undo();
            }
            else if(input.toLowerCase().startsWith("redo")){
                taskList.redo();
            }
            else if(input.toLowerCase().startsWith("exit")){
                System.out.println("Exited");
                proceed = false;
            }
            else{
                System.out.println("Try again with corrent command");
            }
        }
    }
}
