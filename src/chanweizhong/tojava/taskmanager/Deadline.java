package chanweizhong.tojava.taskmanager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents a task with completion status and deadline. A <code>Deadline</code> object has a
 * description string, a boolean indicating whether the task is done, and a deadline string.
 */
public class Deadline extends Todo {

    private DateFormat inputDateFormat = new SimpleDateFormat("ddMMyyyy HHmm");
    private DateFormat fileDateFormat = new SimpleDateFormat("ddMMyyyyHHmm");
    private DateFormat outputDateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm");
    protected Date by;

    /**
     *  Instantiate a <code>Deadline</code> object.
     *
     * @param description Description of a <code>Deadline</code> object.
     * @param by By date of a <code>Deadline</code> object.
     * @return <code>Deadline</code> object.
     * @throws ParseException If by is not in valid input date format.
     */
    public Deadline(String description, String by) throws ParseException {
        super(description);
        this.by = inputDateFormat.parse(by);
    }

    /**
     *  Instantiate a <code>Deadline</code> object.
     *
     * @param description Description of a <code>Deadline</code> object.
     * @param dateInput Added Date of a <code>Deadline</code> object.
     * @param by By date of a <code>Deadline</code> object.
     * @param isDone Completion status of a <code>Deadline</code> object.
     * @return <code>Deadline</code> object.
     * @throws ParseException If by is not in valid input date format.
     */
    public Deadline(String description, String dateInput, String by, String isDone) throws ParseException {
        super(description, dateInput, isDone);
        this.by = fileDateFormat.parse(by);
    }

    /**
     *  Get the by date of a <code>Deadline</code> object.
     *
     *  @return By date of a <code>Deadline</code> object.
     */
    public String getBy() {
        return fileDateFormat.format(by);
    }

    @Override
    public String toString() {
        return super.toString() + System.lineSeparator() + "Do By: " + outputDateFormat.format(by);
    }

}
