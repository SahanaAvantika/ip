package TaskList;

import Task.Task;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public TaskList(){
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints all tasks in the task list with their respective indices.
     */
    public void printList(){
        for(int i = 0; i < tasks.size(); i++){
            int s = i + 1;
            System.out.println(s + "." + tasks.get(i).toString());
        }
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param i The index of the task to be marked as done.
     */
    public void mark(int i){
        Task c = getTask(i);
        c.markAsDone();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void unmark(int i){
        Task c = getTask(i);
        c.markAsIncomplete();
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param i The index of the task to be deleted.
     */
    public void delete(int i){
        tasks.remove(i);
    }

    /**
     * Adds a task to the ArrayList.
     *
     * @param t The task to add to the list.
     */
    public void addTask(Task t){
        tasks.add(t);
    }

    /**
     * Gets a task at the specified index.
     *
     * @param i The index of the task to be obtain.
     */
    public Task getTask(int i){
        return tasks.get(i);
    }

    /**
     * Returns the length of the list
     *
     * @return The length of the list.
     */
    public int size(){
        return tasks.size();
    }

    /**
     * Returns the string of the tasks to be stored in the text file.
     *
     * @return String of the tasks.
     */
    public String printListForStorage(){
        String s = "";
        for (Task task : tasks) {
            s = s + task.toString() + "\n";
        }
        return s;
    }

}
