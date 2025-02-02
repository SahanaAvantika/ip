package Ben.ui;
import Commands.*;
import Storage.Storage;
import Parser.Parser;
import TaskList.TaskList;
import Ui.Ui;

import java.util.Scanner;

public class Ben {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Ben(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {

        ui.introMsg(); //better msg coming
        boolean isExit = false;
        Scanner scanner = new Scanner(System.in);
        
        while (!isExit && scanner.hasNextLine()) {
            try {
                String response = scanner.nextLine();
                Commands c = Parser.executeCommand(response);

                if (c != null) {
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit(); //Boolean methods should be named to sound like booleans
                }
            } catch (Exception e) {
                System.out.println("Invalid Input, try again!");
            }
        }

        scanner.close();
    }



    public static void main(String[] args) {
        Ben ben = new Ben("data/ben.txt");
        ben.run();
    }
}
