import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Parser {

    public static void excuteCommand(String response, TaskList list, Ui ui){
        Command cmd = Command.valueOf(response.split("\\s+")[0].toUpperCase());
        switch (cmd){
            case LIST:
                Ui.list();
                list.printList();
                break;
            case MARK:
                try {
                    int i = Integer.parseInt((String) response.subSequence(5, response.length()));
                    list.mark(i - 1);
                    Ui.markDone(list, i - 1);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after mark, mark ____");
                }
                break;
            case UNMARK:
                try {
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    list.unmark(i - 1);
                    Ui.markUndone(list, i - 1);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after unmark, unmark ____");
                }
                break;
            case DELETE:
                try {
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    Task c = list.getTask(i - 1);
                    list.delete(i - 1);
                    Ui.deleteTask(c, list);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after delete, delete ___");
                }
                break;
            case TODO:
                try {
                    String x = (String) response.subSequence(5, response.length());
                    ToDos c = new ToDos(x);
                    list.addTask(c);
                    Ui.addTask(c, list);
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
                    list.addTask(c);
                    Ui.addTask(c, list);
                } catch (Exception e) {
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
                    list.addTask(c);
                    Ui.addTask(c, list);
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, event _____ /from __ /to __ ");
                }
                break;
            default:
                System.out.println("Invalid Input, try again!");
        }

    }

    public static Task txtToTask(String x) {
        try {
            if (x.charAt(1) == 'T') { // ToDos
                ToDos t = new ToDos(x.substring(7));
                if (x.charAt(4) == 'X') {
                    t.markAsDone();
                }
                return t;
            } else if (x.charAt(1) == 'D') { // Deadlines
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String byStr = x.split("by: ")[1].split("\\)")[0].strip();
                LocalDateTime by = Parser.listToTask(byStr);
                Deadlines d = new Deadlines(des, by);
                if (x.charAt(4) == 'X') {
                    d.markAsDone();
                }
                return d;
            } else if (x.charAt(1) == 'E') { // Events
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String fromStr = x.split("from: ")[1].split(" to:")[0].strip();
                String toStr = x.split("to: ")[1].strip();
                LocalDateTime from_time = Parser.listToTask(fromStr);
                LocalDateTime to_time = Parser.listToTask(toStr);
                Events e = new Events(des, from_time, to_time);
                if (x.charAt(4) == 'X') {
                    e.markAsDone();
                }
                return e;
            } else {
                throw new IllegalArgumentException("Unknown task type");
            }
        } catch (Exception e) {
            System.out.println("Error parsing task: " + e.getMessage());
            return null;
        }
    }


    public static LocalDateTime listToTask(String s) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return LocalDateTime.parse(s, inputFormatter);
    }

    public static String fromatToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);

    }

}
