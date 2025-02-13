package storage;

import task.Task;
import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }

    /**
     * Return the ArrayList of tasks from the text file.
     *
     * @return ArrayList<Task> from the text file.
     * @throws FileNotFoundException if the text file cannot be found.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(printFileContents());
        while (s.hasNext()){
            String x = s.nextLine();
            tasks.add(Parser.txtToTask(x));
        }
        return tasks;
    }

    /**
     * Reads the text file.
     *
     * @return String from the text file.
     * @throws FileNotFoundException if the text file cannot be found.
     */
    private String printFileContents() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        String list = "";
        while (s.hasNext()) {
            list = list + s.nextLine() + "\n";
        }
        return list;
    }

    /**
     * Writes the string of tasks to save to the file.
     *
     * @param textToAdd String of tasks.
     * @throws IOException  If the file cannot be found or an error occurs during writing.
     */
    public static void writeToFile(String textToAdd) throws IOException {
        File f = new File("data/ben.txt");
        f.delete();
        FileWriter fw = new FileWriter("data/ben.txt");
        fw.write(textToAdd);
        fw.close();
    }
}
