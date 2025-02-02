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

    public void printList(){
        for(int i = 0; i < tasks.size(); i++){
            int s = i + 1;
            System.out.println(s + "." + tasks.get(i).toString());
        }
    }

    public void mark(int i){
        Task c = getTask(i);
        c.markAsDone();
    }

    public void unmark(int i){
        Task c = getTask(i);
        c.markAsIncomplete();
    }

    public void delete(int i){
        tasks.remove(i);
    }

    public void addTask(Task t){
        tasks.add(t);
    }

    public Task getTask(int i){
        return tasks.get(i);
    }

    public int size(){
        return tasks.size();
    }

    public String printListForStorage(){
        String s = "";
        for (Task task : tasks) {
            s = s + task.toString() + "\n";
        }
        return s;
    }

    /** Return all Task with the word given by the user
     *
     * @param des The word given by the user.
     * @return ArrayList with the tasks that contain that des.
     */
    public ArrayList<Task> getTasks(String des){
        ArrayList<Task> aptTask = new ArrayList<>();
        for (Task task : tasks) {
            if(task.getDescription().contains(des)){
                aptTask.add(task);
            }
        }
        return aptTask;
    }

}
