package chanweizhong.tojava.taskmanager;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The <code>TaskManager</code> class implements an application that allows users to manage tasks.
 * It contains methods that allow the creation, modification and storage of tasks.
 */
public class TaskManager {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskManager(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showToUser("Problem reading file. Starting with an empty task list");
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();

        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                String commandWord = Parser.getCommandWord(fullCommand);
                switch (commandWord) {
                    case "exit":
                    case "":
                        isExit = true;
                        break;
                    case "todo":
                        addTodo(fullCommand);
                        break;
                    case "deadline":
                        addDeadline(fullCommand);
                        break;
                    case "done":
                        markAsDone(fullCommand);
                        break;
                    case "delete":
                        delete(fullCommand);
                        break;
                    case "print":
                        ui.showToUser("Tasks: " + System.lineSeparator() + tasks.getDescriptionOfTasks());
                        break;
                    default:
                        ui.printError();
                }
            } catch (TaskManagerException e) {
                ui.printError(e.getMessage());
            }
        }
        exit();
    }

    private void addTodo(String fullCommand) throws TaskManagerException {
        tasks.addTask(Parser.createTodo(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e){
            throw new TaskManagerException(e.getMessage());
        }
        ui.showToUser("Tasks in the list: " + tasks.getSize());
    }

    private void addDeadline(String fullCommand) throws TaskManagerException {
        tasks.addTask(Parser.createDeadline(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e){
            throw new TaskManagerException(e.getMessage());
        }
        ui.showToUser("Tasks in the list: " + tasks.getSize());
    }

    private void markAsDone(String fullCommand) throws TaskManagerException {
        tasks.setTaskAsDone(Parser.getTaskIndex(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new TaskManagerException(e.getMessage());
        }
        ui.showToUser("Tasks in the list: " + tasks.getSize());
    }

    private void delete(String fullCommand) throws TaskManagerException {
        tasks.deleteTask(Parser.getTaskIndex(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new TaskManagerException(e.getMessage());
        }
        ui.showToUser("Tasks in the list: " + tasks.getSize());
    }

    private void exit() {
        ui.showToUser("Bye!");
    }

    public static void main(String[] args) {
        new TaskManager("data/tasks.txt").run();
    }

}