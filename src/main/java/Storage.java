import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private final String filepath;

    public Storage(String filepath){
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<Task>();
        Scanner s = new Scanner(printFileContents());
        while (s.hasNext()){
            String x = s.nextLine();
            tasks.add(Parser.txtToTask(x));
        }
        return tasks;
    }

    private String printFileContents() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner s = new Scanner(f);
        String list = "";
        while (s.hasNext()) {
            list = list + s.nextLine() + "\n";
        }
        return list;
    }

    public static void writeToFile(String textToAdd) throws IOException {
        File f = new File("data/ben.txt");
        f.delete();
        FileWriter fw = new FileWriter("data/ben.txt");
        fw.write(textToAdd);
        fw.close();
    }
}
