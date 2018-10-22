package chanweizhong.tojava.taskmanager;

import java.util.ArrayList;
import java.util.List;

/**
 * A <code>TaskList</code> class manages the in-memory task list.
 * It contains an ArrayList that stores individual <code>Task</code> objects and methods
 * to perform operations such as add and delete to the task list.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
    }

    public void addTask(Task t) {
        tasks.add(t);
    }

    public void setTaskAsDone(int index) throws TaskManagerException {
        try {
            ((Todo)tasks.get(index - 1)).setDone(true);
        } catch(IndexOutOfBoundsException e) {
            throw new TaskManagerException("Invalid index");
        }
    }

    public void deleteTask(int index) throws TaskManagerException {
        try {
            tasks.remove(tasks.get(index - 1));
        } catch(IndexOutOfBoundsException e) {
            throw new TaskManagerException("Invalid index");
        }
    }

    public String getDescriptionOfTasks() {
        String description = "";
        for (int i = 0; i < tasks.size(); i++) {
            description += "[" + (i + 1) + "] " + tasks.get(i) + System.lineSeparator();
        }
        return description;
    }

    public String getDescriptionOfTask(int index) {
        return tasks.get(index).getDescription();
    }

    public int getSize() {
        return tasks.size();
    }

    public String getType(int index) {
        if (tasks.get(index) instanceof Deadline) {
            return "D";
        } else {
            return "T";
        }
    }

    public boolean getIsDone(int index) {
        return ((Todo)tasks.get(index)).getIsDone();
    }

    public String getBy(int index) {
        return ((Deadline)tasks.get(index)).getBy();
    }

}