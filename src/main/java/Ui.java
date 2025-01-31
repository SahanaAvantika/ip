public class Ui {
    public static void introMsg(){
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");

        System.out.println("For a todo task, use: todo [task description]");
        System.out.println("Example: todo Buy groceries");

        System.out.println("For a deadline task, use: deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("Example: deadline Submit report /by 2025-03-15 23:59");

        System.out.println("For an event, use: event [event description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("Example: event Team meeting /from 2025-03-15 14:00 /to 2025-03-15 15:30");
    }

    public static void showLoadingError(){
        System.out.println("damn loading error, the list has been reset :( ");
    }

    public static void bye(){
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void markDone(TaskList list, int i){
        Task t = list.getTask(i);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + t.toString());
    }

    public static void markUndone(TaskList list, int i){
        Task t = list.getTask(i);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + t.toString());
    }

    public static void deleteTask(Task t, TaskList list) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + t.toString());
        int length = list.size();
        System.out.println("Now you have " + length + " tasks in the list.");
    }

    public static void addTask(Task t, TaskList list){
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void list(){
        System.out.println("Here are the tasks in your list:");
    }
}

