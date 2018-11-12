package chanweizhong.tojava.taskmanager;

import java.text.ParseException;

/**
 * A <code>Parser</code> class contains methods that deals with parsing
 * user commands to extract meaningful details from it.
 */
public class Parser {

    /**
     *  Get the command word from user's input string.
     *  @param fullCommand User's full input string.
     *  @return Command word from user's input string.
     */
    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    /**
     *  Creates a <code>Todo</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Todo</code> object.
     * @throws TaskManagerException If description for <code>Todo</code> is missing.
     */
    public static Todo createTodo(String fullCommand) throws TaskManagerException {
        String description = fullCommand.substring("todo".length()).trim();
        if (description.isEmpty()){
            throw new TaskManagerException("Missing description for TODO");
        }
        return new Todo(description);
    }

    /**
     *  Creates a <code>Deadline</code> object.
     *
     * @param fullCommand User's full input string.
     * @return <code>Deadline</code> object.
     * @throws TaskManagerException If description for <code>Deadline</code> is missing.
     */
    public static Deadline createDeadline(String fullCommand) throws TaskManagerException {
        fullCommand = fullCommand.substring("deadline".length()).trim();
        if (fullCommand.isEmpty()){
            throw new TaskManagerException("Missing description for DEADLINE");
        }
        String description = fullCommand.split("/by")[0].trim();
        String by;
        try {
            by = fullCommand.split("/by")[1].trim();
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new TaskManagerException("Missing by for DEADLINE");
        }
        try {
            return new Deadline(description, by);
        } catch (ParseException e) {
            throw new TaskManagerException("Wrong by format for DEADLINE");
        }

    }

    /**
     *  Gets task index from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Task index.
     * @throws TaskManagerException If task index is missing or invalid.
     */
    public static int getTaskIndex(String fullCommand) throws TaskManagerException {
        int index;
        String command = fullCommand.replace("undone", "")
                .replace("done", "")
                .replace("delete", "")
                .trim();
        if (command.equals("")) {
            throw new TaskManagerException("Missing task index");
        }
        try {
            index = Integer.parseInt(command);
        } catch(NumberFormatException e) {
            throw new TaskManagerException("Invalid task index");
        }
        return index;
    }

    /**
     *  Gets sort type from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return Sort type.
     * @throws TaskManagerException If sort type is missing or invalid.
     */
    public static int getSortType(String fullCommand) throws TaskManagerException {
        int type;
        String command = fullCommand.replace("sort", "").trim();
        if (command.equals("")) {
            throw new TaskManagerException("Empty sort type");
        }
        try {
            type = Integer.parseInt(command);
        } catch(NumberFormatException e) {
            throw new TaskManagerException("Invalid sort type");
        }
        return type;
    }

    /**
     *  Gets list type from user's full input string.
     *
     * @param fullCommand User's full input string.
     * @return List type.
     * @throws TaskManagerException If list type is missing or invalid.
     */
    public static int getListType(String fullCommand) throws TaskManagerException {
        int type;
        String command = fullCommand.replace("list", "").trim();
        if (command.equals("")) {
            throw new TaskManagerException("Empty list type");
        }
        try {
            type = Integer.parseInt(command);
        } catch(NumberFormatException e) {
            throw new TaskManagerException("Invalid list type");
        }
        return type;
    }

}