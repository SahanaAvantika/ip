package Commands;
import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;


public class UnmarkCommand extends Commands {
    int i;

    public UnmarkCommand(int i) {
        this.i = i;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.unmark(i);
        ui.markUndone(list, i);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
