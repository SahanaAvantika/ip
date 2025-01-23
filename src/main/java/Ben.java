import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Ben {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<Task>();
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");
        String response = scanner.nextLine();
        while (!Objects.equals(response, "bye")){
            try{
                Command cmd = Command.valueOf(response.split("\\s+")[0].toUpperCase());
                switch (cmd){
                    case LIST:
                        System.out.println("Here are the tasks in your list:");
                        for(int i = 0; i < tasks.size(); i++) {
                            int s = i + 1;
                            System.out.println(s + "." + tasks.get(i).toString());
                        }
                        break;
                    case MARK:
                        try {
                            int i = Integer.parseInt((String) response.subSequence(5, response.length()));
                            Task c = tasks.get(i - 1);
                            c.markAsDone();
                            System.out.println("Nice! I've marked this task as done:");
                            System.out.println("  " + c.toString());
                        } catch (Exception e) {
                            System.out.println("OOPS, please provide a number after mark, mark ____");
                        }
                        break;
                    case UNMARK:
                        try {
                            int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                            Task c = tasks.get(i - 1);
                            c.markAsIncomplete();
                            System.out.println("OK, I've marked this task as not done yet:");
                            System.out.println("  " + c.toString());
                        } catch (Exception e) {
                            System.out.println("OOPS, please provide a number after unmark, unmark ____");
                        }
                        break;
                    case DELETE:
                        try {
                            int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                            System.out.println(i);
                            Task c = tasks.get(i - 1);
                            tasks.remove(i - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + c.toString());
                            int length = tasks.size();
                            System.out.println("Now you have " + length + " tasks in the list.");
                        } catch (Exception e) {
                            System.out.println(e);
                            System.out.println("OOPS, please provide a number after delete, delete ___");
                        }
                        break;
                    case TODO:
                        try {
                            String x = (String) response.subSequence(5, response.length());
                            ToDos c = new ToDos(x);
                            tasks.add(c);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + c.toString());
                            int length = tasks.size();
                            System.out.println("Now you have " + length + " tasks in the list.");
                        } catch (Exception e) {
                            System.out.println("OOPS please provide description after todo");
                        }
                        break;
                    case DEADLINE:
                        try {
                            String des = response.split("deadline ")[1].split("/by")[0].strip();
                            String by = response.split("/by")[1].strip();
                            Deadlines c = new Deadlines(des, by);
                            tasks.add(c);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + c.toString());
                            int length = tasks.size();
                            System.out.println("Now you have " + length + " tasks in the list.");
                        } catch (Exception e) {
                            System.out.println("OOPS try giving this input, deadline _____ /by ___");
                        }
                        break;
                    case EVENT:
                        try {
                            String des = response.split("event ")[1].split("/from")[0].strip();
                            String from_time = response.split("/from")[1].split("/to")[0].strip();
                            String to_time = response.split("/to")[1].strip();
                            Events c = new Events(des, from_time, to_time);
                            tasks.add(c);
                            System.out.println("Got it. I've added this task:");
                            System.out.println("  " + c.toString());
                            int length = tasks.size();
                            System.out.println("Now you have " + length + " tasks in the list.");
                        } catch (Exception e) {
                            System.out.println("OOPS try giving this input, event _____ /from __ /to __ ");
                        }
                        break;
                    default:
                        System.out.println("Invalid Input, try again!");
                }
                response = scanner.nextLine();
            } catch (Exception e){
                System.out.println("Invalid Input, try again!");
                response = scanner.nextLine();
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
