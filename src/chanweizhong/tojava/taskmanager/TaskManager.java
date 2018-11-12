package chanweizhong.tojava.taskmanager;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * The <code>TaskManager</code> class implements an application that allows users to manage tasks.
 * It contains methods that allow the creation, modification and storage of tasks.
 */
public class TaskManager {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public TaskManager(String filePath) {
        assert !filePath.equals("") : "filePath is empty";
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (TaskManagerException e) {
            ui.printError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        wait(1);
        ui.showToUser("Uncompleted Tasks: ");
        wait(1);
        ui.showToUser(tasks.getDescriptionOfUndoneTasks());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readUserCommand();
                assert fullCommand != null : "fullCommand is null";
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
                    case "undone":
                        markAsUndone(fullCommand);
                        break;
                    case "delete":
                        delete(fullCommand);
                        break;
                    case "sort":
                        sortTasks(fullCommand);
                        break;
                    case "list":
                        listTasks(fullCommand);
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

    private void wait(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
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

    private void markAsUndone(String fullCommand) throws TaskManagerException {
        tasks.setTaskAsUndone(Parser.getTaskIndex(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new TaskManagerException(e.getMessage());
        }
        ui.showToUser("Tasks in the list: " + tasks.getSize());
    }

    private void listTasks(String fullCommand) throws TaskManagerException {
        if (Parser.getListType(fullCommand) == 1) {
            ui.showToUser("Tasks: " + System.lineSeparator() + tasks.getDescriptionOfTasks());
        } else if (Parser.getListType(fullCommand) == 2) {
            ui.showToUser("Uncompleted Tasks: " + System.lineSeparator() + tasks.getDescriptionOfUndoneTasks());
        } else {
            throw new TaskManagerException("Invalid list type");
        }
    }

    private void sortTasks(String fullCommand) throws TaskManagerException {
        tasks.sortTasks(Parser.getSortType(fullCommand));
        try {
            storage.save(tasks);
        } catch (IOException e) {
            throw new TaskManagerException(e.getMessage());
        }
        if (Parser.getSortType(fullCommand) == 1) {
            ui.showToUser("Tasks sorted based on added date");
        } else if (Parser.getSortType(fullCommand) == 2) {
            ui.showToUser("Tasks sorted based on by date");
        } else {
            throw new TaskManagerException("Invalid sort type");
        }
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