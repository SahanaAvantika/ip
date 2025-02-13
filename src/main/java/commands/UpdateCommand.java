package commands;

import storage.Storage;
import task.Task;
import taskList.TaskList;
import command.Section;
import ui.Ui;

public class UpdateCommand extends Commands{
    private Section section;
    private int i;
    private String update;

    public UpdateCommand(Section section, int i , String update){
        this.section = section;
        this.i = i;
        this.update = update;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage){
        list.updateTask(section, i, update);
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
