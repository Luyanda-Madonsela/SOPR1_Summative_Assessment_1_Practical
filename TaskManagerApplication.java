import java.util.ArrayList;
import java.util.Scanner;
//-------------------------------------------------------------------------------------
// Task class defines the attributes of a Task object.
class Task {
    private int id;
    private String title;
    private String description;
    private boolean completed;

    public Task(int id, String title, String description, boolean completed) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completed = completed;
    }

    //Getters & Setters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

//-------------------------------------------------------------------------------------
// TaskManager class defines the methods and attributes of the TaskManager object. This object is at the core of all the functionality of this application.

class TaskManager {
    private ArrayList<Task> tasks; // Defining an ArrayList of tasks to store and retrieve tasks.
    private int taskId;

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.taskId = 1;
    }
    

    // addTask method takes a title and a description, creates a task object to store all tha values. Then the object is then stored in the ArrayList.
    public void addTask(String title, String description) {

        Task task = new Task(taskId, title, description, false); // Instantiates a new Task object and gives it the parameters of the addTask method
        tasks.add(task);
        taskId++; // Makes sure that each task will not have the same id with each instantiation.
        System.out.println("Task added successfully.");
    }

    // findTaskById method takes in an id value, iterates through the Tasks list to find the object with the matching id. The object is then returned.
    private Task findTaskById(int taskId) {
        for (Task task : tasks) {
            if (task.getId() == taskId) {
                return task;
            }
        }
        return null;
    }

    // markTaskAsCompleted takes a task id, searches for the matching object in the Tasks list, then marks it as completed
    public void markTaskAsCompleted(int taskId) {
        Task task = findTaskById(taskId); // Calling the findTaskById() method to find matching object in Tasks list
        if (task != null) {
            task.setCompleted(true); // Changing completion status
            System.out.println("Task marked as completed.");
        } else {
            System.out.println("Task not found.");
        }
    }

    //listTasks method simply displays the tasks, adjusting for their completion status.
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
        } else {
            System.out.println("Tasks:");
            for (Task task : tasks) {
                String completedStatus = task.isCompleted() ? "X" : " "; // ternary operator to check completion status and display an 'X' or nothing.
                System.out.println("[" + completedStatus + "] " + task.getId() + ": " + task.getTitle()); // Displaying the tasks
            }
        }
    }

    //deleteTask method takes in an id value and removes the object with the corresponding id from the list.
    public void deleteTask(int taskId) {
        Task task = findTaskById(taskId); // Calling the findTaskById() method to find matching object in Tasks list
        if (task != null) {
            tasks.remove(task); //Deleting the task
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }
    }

}

//-------------------------------------------------------------------------------------
public class TaskManagerApplication {
    // APPLICATION ENTRY POINT
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(); // Instantiating a new TaskManager object to provide this program access to the TaskManager methods.
        boolean nextChoice = true; //Controls the while loop that keeps prompting the user until they opt to exit the program. While true, will keep prompting.

        while (nextChoice) {
            System.out.println("\nTask Manager Application:");
            System.out.println("---------------------------");
            System.out.println("1. Add a task");
            System.out.println("2. Mark a task as completed");
            System.out.println("3. List tasks");
            System.out.println("4. Delete a task");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            String answer = scanner.nextLine(); // Reading in the user's choice and storing it in 'answer' variable. Takes in a string by default, makes verification process easier.

            switch (answer) {
                case "1":
                    System.out.println("Add a task:");
                    System.out.print("Enter task title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter task description: ");
                    String description = scanner.nextLine();
                    taskManager.addTask(title, description); // Calling method to add task to list
                    break;

                case "2":
                    System.out.println("Mark a task as completed:");
                    System.out.print("Enter the task ID to mark as completed: ");
                    int taskId = Integer.parseInt(scanner.nextLine());
                    taskManager.markTaskAsCompleted(taskId); // Calling method to mark task with given id as completed
                    break;

                case "3":
                    taskManager.listTasks(); // Calling method to display all the tasks
                    break;

                case "4":
                    System.out.println("Delete a task:");
                    System.out.print("Enter the task ID to delete: ");
                    int deleteTaskId = Integer.parseInt(scanner.nextLine());
                    taskManager.deleteTask(deleteTaskId);// Calling method remove task with given id from the list
                    break;

                case "5":
                    nextChoice = false; //Will terminate the while loop before the next iteration
                    break;

                default:
                    System.out.println("Error! Please enter a valid choice");
                    break;
            }
        }
        scanner.close();

        // Exiting the program
        System.exit(0);
    }
}
