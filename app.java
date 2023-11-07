import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main
 */
/**
 * Author
 * Emran Marei
 * https://github.com/iemran93
 */
public class app {

    public static void main(String[] args) {
        System.out.println("\tWelcome to the ToDo App");
        Scanner scanner = new Scanner(System.in);
        List<String> todos = new ArrayList<String>();
        String filename = "todo.txt";

        // check if todo.txt exist
        File file = new File(filename);
        if (file.exists()) {
        } else {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred" + e.getMessage());
            }
        }

        todos = fileRead(filename, todos);
        String option;
        boolean running = true;
        while (running) {
            System.out.println("Choose an option:\n1. Add\n2. Delete\n3. Show\n4. Exit");
            option = scanner.nextLine();
            if (option.equals("1") || option.equals("2") || option.equals("3") || option.equals("4")) {
                switch (option) {
                    case "1":
                        System.out.println("Add todo item:");
                        String todo = scanner.nextLine();
                        todos.add(todo);
                        // write to txt file
                        fileWrite(filename, todos);
                        System.out.printf("\t----------\n");
                        System.out.println("\t*Added*");
                        System.out.printf("\t----------\n");
                        break;
                    case "2":
                        System.out.println("What item no. to delete?");
                        try {
                            String nS = scanner.nextLine();
                            int n = Integer.parseInt(nS);
                            if (n > todos.size() || n <= 0) {
                                System.out.printf("\t----------\n");
                                System.out.println("\t*Item not exist*");
                                System.out.printf("\t----------\n");
                                break;
                            }
                            todos.remove(n - 1);
                            fileWrite(filename, todos);
                            System.out.printf("\t----------\n");
                            System.out.println("\t*Deleted*");
                            System.out.printf("\t----------\n");
                            break;
                        } catch (NumberFormatException e) {
                            System.out.println("\t*Invalid input*");
                            break;
                        }
                    case "3":
                        System.out.printf("\t----------\n");
                        for (int i = 0; i < todos.size(); i++) {
                            System.out.printf("\t%d. %s\n", i + 1, todos.get(i));
                        }
                        System.out.printf("\t----------\n");
                        break;
                    case "4":
                        running = false;
                        scanner.close();
                        break;
                }
            } else {
                System.out.println("\t*Enter a valid option!*");
            }
        }
    }

    public static List<String> fileRead(String filePath, List<String> todos) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            // Read the file line by line
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                todos.add(line);
            }
        } catch (FileNotFoundException e) {
            // Handle the exception
            System.out.println("The file does not exist.");
        }
        return todos;
    }

    public static void fileWrite(String filePath, List<String> todos) {
        File file = new File(filePath);
        try {
            FileWriter writer = new FileWriter(file);
            for (String todo : todos) {
                String formattedTodo = String.format("%s\n", todo);
                writer.write(formattedTodo);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }
}