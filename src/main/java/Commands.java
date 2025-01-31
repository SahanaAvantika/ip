public abstract class Commands {

    public abstract void execute( TaskList tasks, Ui ui, Storage storage);

    public abstract boolean isExit();

}
