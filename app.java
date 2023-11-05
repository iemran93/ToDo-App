import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * main
 */
public class app {

    public static void main(String[] args) {
        System.out.println("\tWelcome to the ToDo App");
        Scanner scanner = new Scanner(System.in);
        List<String> todos = new ArrayList<String>();

        // check if todo.txt exist
        File directory = new File(".");
        File[] files = directory.listFiles();
        System.out.println(files);
        boolean foundFile = false;
        for (File file : files) {
            if (file.getName() == "todo.txt") {
                foundFile = true;
                break;
            }
        }
        if (!foundFile) {
            File file = new File("myfile.txt");
            file.createNewFile();
        }

        todos = fileRead("todo.txt", todos);
        int option;
        while (true) {
            System.out.println("Choose an option:\n1. Add\n2. Delete\n3. Show");
            option = scanner.nextInt();
            if (option == 1 || option == 2 || option == 3) {
                switch (option) {
                    case 1:
                        System.out.println("Add todo item:");
                        String todo = scanner.next();
                        todos.add(todo);
                        System.out.printf("\t----------\n");
                        System.out.println("\t*Done*");
                        System.out.printf("\t----------\n");
                        break;
                    case 2:
                        System.out.println("What item no. to delete?");
                        int n = scanner.nextInt();
                        todos.remove(n-1);
                        System.out.printf("\t----------\n");
                        System.out.println("\t*Deleted*");
                        System.out.printf("\t----------\n");
                        break;
                    case 3:
                        System.out.printf("\t----------\n");
                        for (int i = 0; i < todos.size(); i++) {
                            System.out.printf("\t%d. %s\n",i+1 , todos.get(i));
                        }
                        System.out.printf("\t----------\n");
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
}