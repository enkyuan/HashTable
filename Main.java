/*
 * Name:    Enkang Yuan
 * Sect.:   CS 3345.503
 * Desc.:   This is the driver class for this project. It reads input via command line arguments and performs hashtable operations based on the user's choice.
 */

import static java.lang.System.out;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HashTable<Integer, String> hashTable = new HashTable<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            out.println("Menu (type the designated integer value based on the operation you would like to perform):");
            out.println("To insert: Type 1");
            out.println("To find: Type 2");
            out.println("To delete: Type 3");
            out.println("To quit: Press 4");
            out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter key: ");
                    int key = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter value: ");
                    String value = scanner.nextLine();
                    boolean inserted = hashTable.insert(key, value);
                    System.out.println(inserted ? "Inserted successfully" : "Duplicate entry or error");
                    break;
                case 2:
                    System.out.print("Enter key to find: ");
                    int findKey = scanner.nextInt();
                    scanner.nextLine();
                    String foundValue = hashTable.find(findKey);
                    System.out.println(foundValue != null ? "Found value: " + foundValue : "Not found");
                    break;
                case 3:
                    System.out.print("Enter key to delete: ");
                    int deleteKey = scanner.nextInt();
                    scanner.nextLine(); 
                    boolean deleted = hashTable.delete(deleteKey);
                    System.out.println(deleted ? "Deleted successfully" : "Not found");
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
