package chanweizhong.tojava.taskmanager;

import java.util.Scanner;

/**
 * A <code>Ui</code> class contains methods that interacts with the users
 * such as obtaining commands from user input and displaying messages to the users.
 */
public class Ui {

    private Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public String readUserCommand() {
        System.out.print("Your task? ");
        return in.nextLine().trim();
    }

    public void printWelcome() {
        System.out.println("Welcome to TaskManager!");
    }

    public void showToUser(String message) {
        System.out.println(message);
    }

    public void printError() {
        System.out.println("Unknown command! please try again");
    }

    public void printError(String error) {
        System.out.println(error);
    }

}