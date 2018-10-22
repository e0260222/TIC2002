package chanweizhong.tojava.taskmanager;

/**
 * A <code>Parser</code> class contains methods that deals with parsing
 * user commands to extract meaningful details from it.
 */
public class Parser {

    public static String getCommandWord(String fullCommand) {
        return fullCommand.split(" ")[0];
    }

    public static Todo createTodo (String fullCommand) throws TaskManagerException {
        String description = fullCommand.substring("todo".length()).trim();
        if (description.isEmpty()){
            throw new TaskManagerException("Empty description for TODO");
        }
        return new Todo(description);
    }

    public static Deadline createDeadline(String fullCommand) throws TaskManagerException {
        fullCommand = fullCommand.substring("deadline".length()).trim();
        if (fullCommand.isEmpty()){
            throw new TaskManagerException("Empty description for DEADLINE");
        }
        String description = fullCommand.split("/by")[0].trim();
        String by;
        try {
            by = fullCommand.split("/by")[1].trim();
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new TaskManagerException("Empty by for DEADLINE");
        }
        return new Deadline(description, by);
    }

    public static int getTaskIndex(String fullCommand) throws TaskManagerException {
        int index;
        try {
            index = Integer.parseInt(fullCommand.replace("done", "")
                    .replace("delete", "")
                    .trim());
        } catch(NumberFormatException e) {
            throw new TaskManagerException("Invalid index");
        }
        return index;
    }

}