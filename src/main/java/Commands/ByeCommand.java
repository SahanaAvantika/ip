package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ByeCommand extends Commands {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        try {
            ui.bye();
            Storage.writeToFile(list.printListForStorage());

        } catch (Exception e){
            ui.showLoadingError();
        }
    }

    @Override
    public boolean isExit(){
        return true;
    }

}
