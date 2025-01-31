import java.util.Objects;
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
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        Ui.introMsg();
        Scanner scanner = new Scanner(System.in);
        String response = scanner.nextLine();
        while(!Objects.equals(response, "bye")){
            try{
                Parser.excuteCommand(response, tasks, ui);
                response = scanner.nextLine();
            } catch (Exception e){
                System.out.println("Invalid Input, try again!");
                response = scanner.nextLine();
            }
        }

        try {
            Ui.bye();
            Storage.writeToFile(tasks.printListForStorage());
        } catch (Exception e){
            Ui.showLoadingError();
        }

        scanner.close();
    }



    public static void main(String[] args) {
        Ben ben = new Ben("data/ben.txt");
        ben.run();
    }
}
