package Task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone(){
        this.isDone = true;
    }

    public void markAsIncomplete(){
        this.isDone = false;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        String s = isDone ? "[X]" : "[ ]";
        return s + " " + description;
    }

}
