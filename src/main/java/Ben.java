import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class Ben {
    private ArrayList<Task> tasks = new ArrayList<Task>();

    private static String printFileContents() throws FileNotFoundException {
        File f = new File("data/ben.txt");
        Scanner s = new Scanner(f);
        String list = "";
        while (s.hasNext()) {
            list = list + s.nextLine() + "\n";
        }
        return list;
    }

    private static void writeToFile(String textToAdd) throws IOException {
        File f = new File("data/ben.txt");
        f.delete();
        FileWriter fw = new FileWriter("data/ben.txt");
        fw.write(textToAdd);
        fw.close();
    }

    private void addTaskFromFile(String list){
        Scanner s = new Scanner(list);
        while (s.hasNext()){
            String x = s.nextLine();
            fileToCommand(x);

        }
    }

    private void fileToCommand(String x) {
        if (x.charAt(1) == 'T') {
            ToDos t = new ToDos(x.substring(7));
            if (x.charAt(4) == 'X') {
                t.markAsDone();
            }
            tasks.add(t);
        } else if (x.charAt(1) == 'D') {
            String des = x.split("] ")[1].split(" \\(")[0].strip();
            LocalDateTime by = LocalDateTime.parse(x.split("by: ")[1].split("\\)")[0].strip());
            Deadlines d = new Deadlines(des, by);
            if (x.charAt(4) == 'X') {
                d.markAsDone();
            }
            tasks.add(d);
        } else if (x.charAt(1) == 'E') {
            String des = x.split("] ")[1].split(" \\(")[0].strip();
            LocalDateTime from_time = LocalDateTime.parse(x.split("from: ")[1].split(" to:")[0].strip());
            LocalDateTime to_time = LocalDateTime.parse(x.split("to: ")[1].strip());
            Events e = new Events(des, from_time, to_time);
            if (x.charAt(4) == 'X') {
                e.markAsDone();
            }
            tasks.add(e);
        }
    }


    private void findFunction(String response){
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
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, todo _____");
                }
                break;
            case DEADLINE:
                try {
                    String des = response.split("deadline ")[1].split("/by")[0].strip();
                    String date = response.split("/by")[1].strip();
                    LocalDateTime by = LocalDateTime.parse(date.replace(" ", "T") + ":00");
                    Deadlines c = new Deadlines(des, by);
                    tasks.add(c);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + c.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println(e);
                    System.out.println("OOPS try giving this input, deadline _____ /by ___");
                }
                break;
            case EVENT:
                try {
                    String des = response.split("event ")[1].split("/from")[0].strip();
                    String from = response.split("/from")[1].split("/to")[0].strip();
                    LocalDateTime from_time = LocalDateTime.parse(from.replace(" ", "T") + ":00");
                    String to = response.split("/to")[1].strip();
                    LocalDateTime to_time = LocalDateTime.parse(to.replace(" ", "T") + ":00");
                    Events c = new Events(des, from_time, to_time);
                    tasks.add(c);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + c.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, event _____ /from __ /to __ ");
                }
                break;
            default:
                System.out.println("Invalid Input, try again!");
        }

    }

    public static void main(String[] args) {
        Ben ben = new Ben();
        Scanner scanner = new Scanner(System.in);
        try {
            String s = printFileContents();
            ben.addTaskFromFile(s);
        } catch (Exception e){
            System.out.println("omg error obtaining task from file");
        }
        System.out.println("Hello! I'm Ben");
        System.out.println("What can I do for you?");

        System.out.println("For a todo task, use: todo [task description]");
        System.out.println("Example: todo Buy groceries");

        System.out.println("For a deadline task, use: deadline [task description] /by [yyyy-MM-dd HH:mm]");
        System.out.println("Example: deadline Submit report /by 2025-03-15 23:59");

        System.out.println("For an event, use: event [event description] /from [yyyy-MM-dd HH:mm] /to [yyyy-MM-dd HH:mm]");
        System.out.println("Example: event Team meeting /from 2025-03-15 14:00 /to 2025-03-15 15:30");


        String response = scanner.nextLine();
        while(!Objects.equals(response, "bye")){
            try{
                ben.findFunction(response);
                response = scanner.nextLine();
            } catch (Exception e){
                System.out.println("Invalid Input, try again!");
                response = scanner.nextLine();
            }
        }
        String s = "";
        for (Task task : ben.tasks) {
            s = s + task.toString() + "\n";
        }
        try {
            writeToFile(s);
        } catch (IOException e){
            System.out.println("omg error saving the tasks");
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }
}
