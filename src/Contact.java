import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

import static java.lang.Long.parseLong;

public class Contact extends Contacts {
    private Scanner scanner = new Scanner(System.in);
    private String name;
    private long number;
    private int counter = 0;
    private String directory = "data";
    private String filename = "contacts.txt";
    private Path contactDirectory = Paths.get(directory, filename);
    AsciiBanners asciiBanners = new AsciiBanners();

    public String input() {
        return scanner.nextLine();
    }

    public void mainMenu() {
        if (counter == 0){
            asciiBanners.hello();
        }
        System.out.println("1. View Contacts.");
        System.out.println("2. Add a new contact.");
        System.out.println("3. Search a contact by name.");
        System.out.println("4. Delete an existing contact.");
        System.out.println("5. Exit");
        counter++;
    }

    public void searchContact() throws IOException {
        asciiBanners.searchContact();
        System.out.println("Enter the name of the contact to search for:");
        String name = input();
        List<String> contactList = Files.readAllLines(contactDirectory);
        boolean found = false;
        for (String contact : contactList) {
            if (contact.startsWith(name + " ")) {
                System.out.println(contact);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Contact not found.");
        }
    }

    public void deleteContact() throws IOException {
        asciiBanners.contactDeletedBanner();
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
        asciiBanners.contactListBanner();
        Contacts contacts = new Contacts();
        List<String> contactList = Files.readAllLines(contactDirectory);
        System.out.println("Name          |Number        ");
        for (String x : contactList) {
            if (!x.isEmpty()) {
                String[] spl = x.split(" ");
                String name = spl[0];
                Long number = parseLong(spl[1]);
                System.out.printf("%14s|%14s\n", name, number);
            }
        }
    }

    public void addContact() throws IOException {
        asciiBanners.contactAddBanner();
        System.out.println("Enter the name.");
        String name = input();
        System.out.println("Enter the number.");
        String number = input();
        try (FileWriter fw = new FileWriter(contactDirectory.toFile(), true)) {
            fw.write("\n" + name + " " + number);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
