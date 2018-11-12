package chanweizhong.tojava.taskmanager;

import java.text.ParseException;

/**
 * Represents a task with completion status. A <code>Todo</code> object has a
 * description string and a boolean indicating whether the task is done.
 */
public class Todo extends Task {

    private boolean isDone;

    /**
     *  Instantiate a <code>Todo</code> object.
     *
     * @param description Description of a <code>Todo</code> object.
     * @return <code>Deadline</code> object.
     */
    public Todo(String description) {
        super(description);
        isDone = false;
    }

    /**
     *  Instantiate a <code>Deadline</code> object.
     *
     * @param description Description of a <code>Todo</code> object.
     * @param dateInput Added Date of a <code>Todo</code> object.
     * @param isDone Completion status of a <code>Deadline</code> object.
     * @return <code>Todo</code> object.
     * @throws ParseException If dateInput is not in valid input date format.
     */
    public Todo(String description, String dateInput, String isDone) throws ParseException {
        super(description, dateInput);
        if (isDone.equals("1"))
            this.isDone = true;
        else
            this.isDone = false;
    }

    /**
     *  Set the completion status of a <code>Todo</code> object to done.
     *
     *  @param isDone Completion status of a <code>Todo</code> object.
     */
    public void setDone(boolean isDone){
        this.isDone = isDone;
    }

    /**
     *  Get the completion status of a <code>Todo</code> object.
     *
     *  @return Completion status of a <code>Todo</code> object.
     */
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
        return super.toString() + System.lineSeparator() + "Is Done? " + status;
    }

}
