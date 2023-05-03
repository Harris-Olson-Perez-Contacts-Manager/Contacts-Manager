import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AsciiBanners asciiBanners = new AsciiBanners();
        Contact c1 = new Contact();
        int choice = 0;
        do {
            c1.mainMenu();
            choice = Integer.parseInt(c1.input());
            switch (choice) {
                case 1:
                    c1.allContacts();
                    break;
                case 2:
                    c1.addContact();
                    break;
                case 3:
                    c1.searchContact();
                    break;
                case 4:
                    c1.deleteContact();
                    break;
                case 5:
                    asciiBanners.endBanner();
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 5);
    }
}
