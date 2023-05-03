import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Contact extends Contacts{
    private Scanner scanner = new Scanner(System.in);
    private String name;
    private long number;

    private String directory = "data";
    private String filename = "contacts.txt";
    private Path contactDirectory = Paths.get(directory, filename);

    public String input(){
        return scanner.nextLine();
    }

    public void mainMenu() {
        System.out.println("1. View Contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit");
    }

    public void deleteContact() throws IOException {
        System.out.println("Enter the name of the contact to delete:");
        String name = input();

        List<String> contactList = Files.readAllLines(contactDirectory);
        boolean deleted = false;
        for (int i = 0; i < contactList.size(); i++) {
            String contact = contactList.get(i);
            if (contact.startsWith(name + " ")) {
                contactList.remove(i);
                deleted = true;
                break;
            }
        }
        if (deleted) {
            try (FileWriter fw = new FileWriter(contactDirectory.toFile())) {
                for (String contact : contactList) {
                    fw.write(contact + "\n");
                }
            }
            System.out.println("Contact deleted successfully.");
        } else {
            System.out.println("Contact not found.");
        }
    }

    public void allContacts() throws IOException {
        Contacts contacts = new Contacts();
        List<String> contactList = Files.readAllLines(contactDirectory);
        System.out.println(contactList);
    }

    public void addContact() throws IOException {
        System.out.println("Enter the name.");
        String name = input();
        System.out.println("Enter the number.");
        String number = input();

        try(FileWriter fw = new FileWriter(contactDirectory.toFile(), true)){
            fw.write("\n" + name + " " + number);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }




}
