package chanweizhong.tojava.taskmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A <code>Storage</code> class contains methods that deals with parsing
 * user commands to extract meaningful details from it.
 */
public class Storage {

    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws FileNotFoundException {
        List<Task> loadedTasks = new ArrayList<>();
        List<String> lines = getLines(filePath);
        for (String line : lines) {
            if (line.trim().isEmpty()) { //ignore empty lines
                continue;
            }
            loadedTasks.add(createTask(line)); //convert the line to a task and add to the list
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

    private Task createTask(String line) {
        String taskType = line.split("\\|")[0].trim();
        String isDone = line.split("\\|")[1].trim();
        String description = line.split("\\|")[2].trim();
        if (taskType.equals("D")) {
            String by = line.split("\\|")[3].trim();
            return new Deadline(description, by, isDone);
        } else {
            return new Todo(description, isDone);
        }
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String isDone;
        String description;
        String by;

        for (int i = 0; i < tasks.getSize(); i++) {
            if (tasks.getIsDone(i)) {
                isDone = "1";
            }
            else {
                isDone = "0";
            }
            description = tasks.getDescriptionOfTask(i);
            if (tasks.getType(i) == "D") {
                by = (tasks.getBy(i));
                fw.write("D" + " | " + isDone + " | " + description + " | " + by + System.lineSeparator());
            } else if (tasks.getType(i) == "T"){
                fw.write("T" + " | " + isDone + " | " + description + System.lineSeparator());
            }
        }
        fw.close();
    }

}