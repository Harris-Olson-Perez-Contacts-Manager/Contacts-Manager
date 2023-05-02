import java.io.*;
import java.util.Scanner;

public class Contact {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean isContinued = true;
        AsciiBanners asciiBanners = new AsciiBanners();
        asciiBanners.hello();
        do {
            System.out.println("1. View contacts.\n" +
                    "2. Add a new contact.\n" +
                    "3. Search a contact by name.\n" +
                    "4. Delete an existing contact.\n" +
                    "5. Exit.\n" +
                    "Enter an option (1, 2, 3, 4 or 5):");
            String userInput = scanner.nextLine();
            if (userInput.equals("1")) {
                String fileName = "contacts.txt";
                asciiBanners.contactListBanner();
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] contactInfo = line.split(",");
                        String name = contactInfo[0];
                        String phone = contactInfo[1];
                        String formattedContact = ContactFormatter.format(name, phone);
                        System.out.println(formattedContact);
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + e.getMessage());
                    asciiBanners.trollBanner();
                }
            } else if (userInput.equals("2")) {
                String fileName = "contacts.txt";
                System.out.println("Enter New Contact Name: ");
                String name = scanner.nextLine();
                System.out.println("Enter New Contact Number: ");
                String phone = scanner.nextLine();
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
                    String contactLine = name + "," + phone;
                    bw.write(contactLine);
                    bw.newLine();
                    asciiBanners.contactAddedBanner();
                    System.out.println(name + " - " + phone);
                } catch (IOException e) {
                    System.err.println("Error writing to file: " + e.getMessage());
                    asciiBanners.trollBanner();
                }
            } else if (userInput.equals("3")) {
                asciiBanners.monopolyBanner();
                String fileName = "contacts.txt";
                System.out.println("Enter Search Name: ");
                String name = scanner.nextLine();
                String phone = null;
                try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
                    String line;
                    while ((line = br.readLine()) != null) {
                        String[] parts = line.split(",");
                        if (parts.length == 2 && parts[0].equals(name)) {
                            phone = parts[1];
                            break;
                        }
                    }