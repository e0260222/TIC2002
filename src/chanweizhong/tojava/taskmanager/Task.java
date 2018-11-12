package chanweizhong.tojava.taskmanager;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

/**
 * Represents a task. A <code>Task</code> object has a description
 * string.
 */
public abstract class Task {

    private DateFormat fileDateFormat = new SimpleDateFormat("ddMMyyyyHHmm");
    private DateFormat outputDateFormat = new SimpleDateFormat("EEE dd/MM/yyyy HH:mm");
    protected Date date;
    private String description;

    public Task(String description) {
        this.description = description;
        date = new Date();
    }

    public Task(String description, String dateInput) throws ParseException {
        this.description = description;
        date = fileDateFormat.parse(dateInput);
    }

    /**
     *  Get the description of a <code>Task</code> object.
     *  @return Description of a <code>Task</code> object.
     */
    public String getDescription() {
        return description;
    }

    /**
     *  Get the added date of a <code>Task</code> object.
     *  @return Added date of a <code>Task</code> object.
     */
    public String getDate() {
        return fileDateFormat.format(date);
    }

    @Override
    public String toString() {
        return "Added: " + outputDateFormat.format(date) + System.lineSeparator() + "Description: " + description;
    }

}
