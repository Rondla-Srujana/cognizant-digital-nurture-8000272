// Exercise 5: Task Management System using Singly Linked List

class Task {
    int taskId;
    String taskName;
    String status;
    Task next; // pointer to next node

    public Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
        this.next = null;
    }

    @Override
    public String toString() {
        return "Task{ID=" + taskId + ", Name='" + taskName + "', Status='" + status + "'}";
    }
}

class TaskLinkedList {
    private Task head;

    // Add at end - O(n)
    public void addTask(Task newTask) {
        if (head == null) {
            head = newTask;
        } else {
            Task current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newTask;
        }
        System.out.println("Added: " + newTask);
    }

    // Add at beginning - O(1)
    public void addTaskAtBeginning(Task newTask) {
        newTask.next = head;
        head = newTask;
        System.out.println("Added at beginning: " + newTask);
    }

    // Search by taskId - O(n)
    public Task searchTask(int taskId) {
        Task current = head;
        while (current != null) {
            if (current.taskId == taskId) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse - O(n)
    public void traverseTasks() {
        System.out.println("--- Task List ---");
        if (head == null) {
            System.out.println("No tasks in the list.");
            return;
        }
        Task current = head;
        int idx = 1;
        while (current != null) {
            System.out.println("  " + idx++ + ". " + current);
            current = current.next;
        }
    }

    // Delete by taskId - O(n)
    public boolean deleteTask(int taskId) {
        if (head == null) {
            System.out.println("List is empty.");
            return false;
        }
        // If head is the target
        if (head.taskId == taskId) {
            System.out.println("Deleted: " + head);
            head = head.next;
            return true;
        }
        // Find the node before the target
        Task current = head;
        while (current.next != null) {
            if (current.next.taskId == taskId) {
                System.out.println("Deleted: " + current.next);
                current.next = current.next.next;
                return true;
            }
            current = current.next;
        }
        System.out.println("Task ID " + taskId + " not found.");
        return false;
    }
}

public class Exercise5_TaskManagement {
    public static void main(String[] args) {
        System.out.println("=== Exercise 5: Task Management System (Linked List) ===\n");

        TaskLinkedList taskList = new TaskLinkedList();

        // Add tasks
        taskList.addTask(new Task(1, "Design Database Schema", "Pending"));
        taskList.addTask(new Task(2, "Develop API", "In Progress"));
        taskList.addTask(new Task(3, "Write Unit Tests", "Pending"));
        taskList.addTaskAtBeginning(new Task(0, "Gather Requirements", "Done"));

        // Traverse
        System.out.println();
        taskList.traverseTasks();

        // Search
        System.out.println();
        Task found = taskList.searchTask(2);
        System.out.println("Search ID 2: " + (found != null ? found : "Not found"));
        Task notFound = taskList.searchTask(99);
        System.out.println("Search ID 99: " + (notFound != null ? notFound : "Not found"));

        // Delete
        System.out.println();
        taskList.deleteTask(1);   // Middle node
        taskList.deleteTask(0);   // Head node
        taskList.deleteTask(99);  // Not found

        System.out.println();
        taskList.traverseTasks();

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Add at end      : O(n) — must traverse to end");
        System.out.println("Add at beginning: O(1)");
        System.out.println("Search          : O(n)");
        System.out.println("Traverse        : O(n)");
        System.out.println("Delete          : O(n) — must find the node");
        System.out.println("\nAdvantage over arrays: Dynamic size; no shifting on deletion.");
    }
}
