package chanweizhong.tojava.taskmanager;

/**
 * Represents a task. A <code>Task</code> object has a description
 * string.
 */
public abstract class Task {

    protected String description;

    public Task(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "description: " + description;
    }

}
