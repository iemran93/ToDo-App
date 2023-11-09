import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * doneItems
 */
public class DoneItems {
    private String filename = "done.txt";

    public void init() {
        // create done file if not existed
        File file = new File(filename);
        if (!file.exists()) {
            try {
                FileWriter writer = new FileWriter(file);
                writer.write("");
                writer.close();
            } catch (IOException e) {
                System.out.println("An error occurred" + e.getMessage());
            }
        }
    }

    public void addDone(String item) {
        File file = new File(filename);
        try {
            FileWriter writer = new FileWriter(file);
            String formattedTodo = String.format("%s\n", item);
            writer.write(formattedTodo);
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred" + e.getMessage());
        }
    }
}