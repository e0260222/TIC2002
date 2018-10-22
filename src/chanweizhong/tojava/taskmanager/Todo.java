package chanweizhong.tojava.taskmanager;

/**
 * Represents a task with completion status. A <code>Todo</code> object has a
 * description string and a boolean indicating whether the task is done.
 */
public class Todo extends Task {

    protected boolean isDone;

    public Todo(String description) {
        super(description);
        isDone = false;
    }

    public Todo(String description, String isDone) {
        super(description);
        if (isDone.equals("1"))
            this.isDone = true;
        else
            this.isDone = false;
    }

    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    public boolean getIsDone() {
        return isDone;
    }

    @Override
    public String toString() {
        String status;
        if (isDone){
            status = "Yes";
        } else {
            status = "No";
        }
        return super.toString() + System.lineSeparator() + "\tis done? " + status;
    }

}
