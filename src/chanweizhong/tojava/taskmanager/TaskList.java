package chanweizhong.tojava.taskmanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 * A <code>TaskList</code> class manages the in-memory task list.
 * It contains an ArrayList that stores individual <code>Task</code> objects and methods
 * to perform operations such as add and delete to the task list.
 */
public class TaskList {

    private List<Task> tasks = new ArrayList<>();

    /**
     *  Instantiate a <code>List<Task></code> object.
     *
     * @param tasks List of tasks.
     * @return <code>List<Task></code> object.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     *  Instantiate a <code>List<Task></code> object.
     *
     * @return <code>List<Task></code> object.
     */
    public TaskList() {
    }

    /**
     *  Add a task to the <code>List<Task></code> object.
     *
     * @param t <code>Task</code>.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     *  Set the completion status of a <code>Task</code> object to done.
     *
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @throws TaskManagerException If index is invalid.
     */
    public void setTaskAsDone(int index) throws TaskManagerException {
        try {
            ((Todo)tasks.get(index - 1)).setDone(true);
        } catch(IndexOutOfBoundsException e) {
            throw new TaskManagerException("Invalid index");
        }
    }

    /**
     *  Set the completion status of a <code>Task</code> object to undone.
     *
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @throws TaskManagerException If index is invalid.
     */
    public void setTaskAsUndone(int index) throws TaskManagerException {
        try {
            ((Todo)tasks.get(index - 1)).setDone(false);
        } catch(IndexOutOfBoundsException e) {
            throw new TaskManagerException("Invalid index");
        }
    }

    /**
     *  Delete a <code>Task</code> object.
     *
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @throws TaskManagerException If index is invalid.
     */
    public void deleteTask(int index) throws TaskManagerException {
        try {
            tasks.remove(tasks.get(index - 1));
        } catch(IndexOutOfBoundsException e) {
            throw new TaskManagerException("Invalid index");
        }
    }

    /**
     *  Sort a <code>List<Task></code> based on added date or by date.
     *
     *  @param type Type of sort.
     *  @throws TaskManagerException If type is invalid.
     */
    public void sortTasks(int type) throws TaskManagerException {
        if (type == 1) {
            Collections.sort(tasks, new SortByDateAdded());
        } else if (type == 2) {
            Collections.sort(tasks, new SortByDateBy());
        } else {
            throw new TaskManagerException("Invalid sort type");
        }

    }

    /**
     *  Get description of all tasks including completion status and date added.
     *
     *  @return Description of all tasks.
     */
    public String getDescriptionOfTasks() {
        String description = "";
        for (int i = 0; i < tasks.size(); i++) {
            description += "[" + (i + 1) + "] " + tasks.get(i) + System.lineSeparator();
        }
        return description;
    }

    /**
     *  Get description of all undone tasks including completion status and date added.
     *
     *  @return Description of all undone tasks.
     */
    public String getDescriptionOfUndoneTasks() {
        String description = "";
        for (int i = 0; i < tasks.size(); i++) {
            if (((Todo)tasks.get(i)).getIsDone() == false) {
                description += "[" + (i + 1) + "] " + tasks.get(i) + System.lineSeparator();
            }
        }
        return description;
    }

    /**
     *  Get description of a <code>Task</code> object including completion status and date added.
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @return Description of a <code>List<Task></code>.
     */
    public String getDescriptionOfTask(int index) {
        return tasks.get(index).getDescription();
    }

    /**
     *  Get Added date of a <code>Task</code> object including completion status and date added.
     *  @param index Index of a <code>Task</code> object in <code>List<Task></code>.
     *  @return Added date of a <code>Task</code> object.
     */
    public String getDateInputOfTask(int index) {
        return tasks.get(index).getDate();
    }

    /**
     *  Get size of a <code>List<Task></code> object.
     *  @return Size of a <code>List<Task></code> object.
     */
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

    /**
     *  Get the completion status of a <code>Task</code> object.
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @return Completion of a <code>Task</code> object.
     */
    public boolean getIsDone(int index) {
        return ((Todo)tasks.get(index)).getIsDone();
    }

    /**
     *  Get the by date of a <code>Deadline</code> object.
     *  @param index Index of a <code>Task</code> in <code>List<Task></code>.
     *  @return By date of a <code>Deadline</code> object.
     */
    public String getBy(int index) {
        return ((Deadline)tasks.get(index)).getBy();
    }

}