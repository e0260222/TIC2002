package chanweizhong.tojava.taskmanager;

/**
 * The <code>TaskManagerException</code> class overrides the Exception class's constructor
 * to implement custom error messages for user-defined exceptions.
 */
public class TaskManagerException extends Exception {

    public TaskManagerException(String message) {
        super(message);
    }

}