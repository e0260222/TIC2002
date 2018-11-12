package chanweizhong.tojava.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A <code>Storage</code> class contains methods that deals with parsing
 * user commands to extract meaningful details from it.
 */
public class Storage {

    private String filePath;

    /**
     *  Instantiate a <code>Storage</code> object.
     *
     * @param filePath Path of the text file used for storing app data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     *  Load data from text file used for storing app data.
     *
     * @throws TaskManagerException If text file is not found or data is not in the correct format.
     */
    public List<Task> load() throws TaskManagerException {
        List<Task> loadedTasks = new ArrayList<>();
        List<String> lines;
        try {
            lines = getLines(filePath);
        } catch (FileNotFoundException e) {
            throw new TaskManagerException("Problem reading file. Starting with an empty task list.");
        }
        for (String line : lines) {
            if (line.trim().isEmpty()) { //ignore empty lines
                continue;
            }
            try {
                loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
            } catch (ParseException e) {
                throw new TaskManagerException("Problem reading file. Starting with an empty task list.");
            }
        }
        return loadedTasks;
    }

    private List<String> getLines(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        List<String> result = new ArrayList<>();
        while (s.hasNext()) {
            result.add(s.nextLine());
        }
        return result;
    }

    private Task createTask(String line) throws ParseException {
        String taskType = line.split("\\|")[0].trim();
        String dateInput = line.split("\\|")[1].trim();
        String isDone = line.split("\\|")[2].trim();
        String description = line.split("\\|")[3].trim();
        if (taskType.equals("D")) {
            String by = line.split("\\|")[4].trim();
            return new Deadline(description, dateInput, by, isDone);
        } else {
            return new Todo(description, dateInput, isDone);
        }
    }

    /**
     *  Save information of all <code>Tasks</code> objects to text file
     *
     * @throws IOException If text file is not found or inaccessible.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String isDone;
        String description;
        String dateInput;
        String by;

        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getIsDone(i)) {
                isDone = "1";
            }
            else {
                isDone = "0";
            }
            description = tasks.getDescriptionOfTask(i);
            dateInput = tasks.getDateInputOfTask(i);
            if (tasks.getType(i) == "D") {
                by = (tasks.getBy(i));
                fw.write("D" + " | " + dateInput + " | " + isDone + " | " + description + " | " + by + System.lineSeparator());
            } else if (tasks.getType(i) == "T"){
                fw.write("T" + " | " + dateInput + " | " + isDone + " | " + description + System.lineSeparator());
            }
        }
        fw.close();
    }

}