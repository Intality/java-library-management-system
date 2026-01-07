import java.util.Scanner;

public class LibraryManagementSystem {

    public static void main(String[] args) {
        String filename = "library_data.ser"; // File name for saving and loading data
        Library library = Library.loadLibrary(filename); // Load library data from the file

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display menu options
            System.out.println("\nLibrary Management System");
            System.out.println("1. Add Book");
            System.out.println("2. Add Member");
            System.out.println("3. Borrow Book");
            System.out.println("4. Return Book");
            System.out.println("5. List Available Books");
            System.out.println("6. List Members");
            System.out.println("7. Display Overdue Books");
            System.out.println("8. View Member's Checked-Out Books");
            System.out.println("9. Save and Exit");
            System.out.print("Choose an option: ");

            int option = getValidMenuChoice(scanner);  // Ensure valid menu choice

            switch (option) {
                case 1:
                    // Add book to the library
                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter book author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter number of copies to add: ");
                    int numberOfCopies = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character
                    Book book = new Book(title, author);
                    library.addBook(book, numberOfCopies);  // Add book copies to the library
                    break;

                case 2:
                    // Add member to the library: first name and last name separately
                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();  // Scanner for first name
                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();  // Scanner for last name

                    // Combine first name and last name to create the full name
                    String fullName = firstName + " " + lastName;

                    Member newMember = new Member(fullName);  // Create new member with full name
                    library.addMember(newMember);  // Add new member to the library

                    // Print a blank line for cleaner layout
                    System.out.println();

                    // Print confirmation message
                    System.out.println(fullName + " added to list of library members!");

                    // Print another blank line between the name added and the Member ID
                    System.out.println();

                    // Print Member ID message
                    System.out.println("YOUR MEMBER ID IS: " + newMember.getMemberID());
                    System.out.println("Please write this down as you will need it for checking out books!");
                    break;

                case 3:
                    // Borrow a book for a member using Member ID
                    System.out.print("Enter member ID: ");
                    String memberID = scanner.nextLine();
                    Member memberByID = library.findMemberByID(memberID);  // Find member by ID
                    if (memberByID != null) {
                        System.out.print("Enter book title: ");
                        String bookTitle = scanner.nextLine();

                        // Borrow the book for the member
                        memberByID.borrowBook(library, bookTitle);

                        // After borrowing, display the checkout code and instructions
                        System.out.println();  // Blank line for clarity
                        System.out.println("Please write down your checkout code for the book you borrowed.");

                        // Call the method from Member class to display the checkout code
                        memberByID.displayCheckoutCode();
                        System.out.println();  // Blank line for clarity
                    } else {
                        System.out.println("Member not found with ID: " + memberID);
                    }
                    break;

                case 4:
                    // Return a book for a member using Member ID
                    System.out.print("Enter member ID: ");
                    String returnMemberID = scanner.nextLine();
                    Member returningMember = library.findMemberByID(returnMemberID);
                    if (returningMember != null) {
                        System.out.print("Enter checkout code for the book: ");
                        String checkoutCode = scanner.nextLine();
                        returningMember.returnBook(checkoutCode);  // Return the book by checkout code
                    } else {
                        System.out.println("Member not found with ID: " + returnMemberID);
                    }
                    break;

                case 5:
                    // List all available books in the library
                    library.listAvailableBooks();
                    break;

                case 6:
                    // List all members in the library
                    library.listMembers();
                    break;

                case 7:
                    // Display overdue books for all members
                    for (Member memberWithBooks : library.getMembers()) {
                        memberWithBooks.checkForOverdueBooks();
                    }
                    break;

                case 8:
                    // View checked-out books for a member
                    System.out.print("Enter member ID: ");
                    String memberName = scanner.nextLine();
                    Member targetMember = library.findMemberByID(memberName);
                    if (targetMember != null) {
                        targetMember.viewCheckedOutBooks();  // Display the books the member has checked out
                    } else {
                        System.out.println("Member not found with ID: " + memberName);
                    }
                    break;

                case 9:
                    // Save and exit the program
                    library.saveLibrary(filename);
                    System.out.println("Exiting and saving data...");
                    scanner.close();
                    return;
            }
        }
    }

    // Method to validate menu choice and ensure a valid integer input
    public static int getValidMenuChoice(Scanner scanner) {
        int choice = -1;
        while (choice == -1) {
            System.out.print("Choose an option: ");
            try {
                choice = Integer.parseInt(scanner.nextLine());  // Parse input as an integer
                if (choice < 1 || choice > 9) {  // Ensure choice is within valid range
                    System.out.println("Invalid option. Please select a number between 1 and 9.");
                    choice = -1;  // Force the loop to continue
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid whole number.");
            }
        }
        return choice;
    }
}

