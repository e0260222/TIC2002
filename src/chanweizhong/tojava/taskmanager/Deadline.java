package chanweizhong.tojava.taskmanager;

/**
 * Represents a task with completion status and deadline. A <code>Deadline</code> object has a
 * description string, a boolean indicating whether the task is done, and a deadline string.
 */
public class Deadline extends Todo {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, String isDone) {
        super(description, isDone);
        this.by = by;
    }

    public String getBy() {
        return by;
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "\tdo by: " + by;
    }

}
