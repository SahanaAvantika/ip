package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;
import Task.*;


public class AddCommand extends Commands {
    private Task t;

    public AddCommand(Task t){
        this.t = t;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.addTask(t);
        ui.addTask(t, list);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
