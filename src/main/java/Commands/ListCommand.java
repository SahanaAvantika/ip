package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class ListCommand extends Commands {

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        ui.list();
        list.printList();
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
