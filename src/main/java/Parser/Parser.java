package Parser;

import Commands.*;
import Command.Command;
import Task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Parses the user input and returns the corresponding command to execute.
     * If the input is unclear or invalid, null is returned.
     *
     * @param response String input by the user
     * @return Commands command to execute.
     */
    public static Commands executeCommand(String response){
        Command cmd = Command.valueOf(response.split("\\s+")[0].toUpperCase());
        switch (cmd){
            case FIND:
                try {
                    String des = response.split("\\s+")[1];
                    return new FindCommand(des);
                } catch (Exception e) {
                    System.out.println("OOPS, please valid input after find, find ____");
                }
            case BYE:
                return new ByeCommand();

            case LIST:
                return new ListCommand();

            case MARK:
                try {
                    int i = Integer.parseInt((String) response.subSequence(5, response.length()));
                    return new MarkCommand(i - 1);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after mark, mark ____");
                }
            case UNMARK:
                try {
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    return new UnmarkCommand(i - 1);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after unmark, unmark ____");
                }
            case DELETE:
                try {
                    int i = Integer.parseInt((String) response.subSequence(7, response.length()));
                    return new DeleteCommand(i - 1);
                } catch (Exception e) {
                    System.out.println("OOPS, please provide a number after delete, delete ___");
                }
            case TODO:
                try {
                    String x = (String) response.subSequence(5, response.length());
                    ToDos c = new ToDos(x);
                    return new AddCommand(c);
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, todo _____");
                }
            case DEADLINE:
                try {
                    String des = response.split("deadline ")[1].split("/by")[0].strip();
                    String date = response.split("/by")[1].strip();
                    LocalDateTime by = LocalDateTime.parse(date.replace(" ", "T") + ":00");
                    Deadlines c = new Deadlines(des, by);
                    return new AddCommand(c);
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, deadline _____ /by ___");
                }
            case EVENT:
                try {
                    String des = response.split("event ")[1].split("/from")[0].strip();
                    String from = response.split("/from")[1].split("/to")[0].strip();
                    LocalDateTime from_time = LocalDateTime.parse(from.replace(" ", "T") + ":00");
                    String to = response.split("/to")[1].strip();
                    LocalDateTime to_time = LocalDateTime.parse(to.replace(" ", "T") + ":00");
                    Events c = new Events(des, from_time, to_time);
                    return new AddCommand(c);
                } catch (Exception e) {
                    System.out.println("OOPS try giving this input, event _____ /from __ /to __ ");
                }
            default:
                System.out.println("Invalid Input, try again!");
                return null;
        }

    }

    /**
     * Parses the string input form the file and returns the corresponding task.
     * If the input is unclear or invalid, null is returned.
     *
     * @param x String input by the user
     * @return Task that the string represents.
     */
    public static Task txtToTask(String x) {
        try {
            if (x.charAt(1) == 'T') {
                ToDos t = new ToDos(x.substring(7));
                if (x.charAt(4) == 'X') {
                    t.markAsDone();
                }
                return t;
            } else if (x.charAt(1) == 'D') {
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String byStr = x.split("by: ")[1].split("\\)")[0].strip();
                LocalDateTime by = Parser.listToTask(byStr);
                Deadlines d = new Deadlines(des, by);
                if (x.charAt(4) == 'X') {
                    d.markAsDone();
                }
                return d;
            } else if (x.charAt(1) == 'E') {
                String des = x.split("] ")[1].split(" \\(")[0].strip();
                String fromStr = x.split("from: ")[1].split(" to:")[0].strip();
                String toStr = x.split("to: ")[1].split("\\)")[0].strip();
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

    /**
     * Parses the string input into LocalDateTime object.
     *
     * @param s String in [MMM dd yyyy hh:mm a] format.
     * @return LocalDateTime object that the string represents.
     */
    public static LocalDateTime listToTask(String s) {
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return LocalDateTime.parse(s, inputFormatter);
    }

    /**
     * Parses the LocalDateTime object into String.
     *
     * @param dateTime LocalDateTime object.
     * @return String  that the LocalDateTime represents.
     */
    public static String fromatToString(LocalDateTime dateTime){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy hh:mm a");
        return dateTime.format(formatter);

    }

}
