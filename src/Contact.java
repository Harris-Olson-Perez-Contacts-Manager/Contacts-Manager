import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Contact {
    private String name;
    private long number;
    private ArrayList<String> contacts;


    private String directory = "data";
    private String filename = "contacts.txt";
    private Path contactDirectory = Paths.get(directory, filename);


    public void allContacts() throws IOException {
        List<String> contactsList = Files.readAllLines(contactDirectory);
        contacts.addAll(contactsList);
    }

}
