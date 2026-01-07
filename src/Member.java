import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Member implements Serializable {
    private String name;
    private String memberID;
    private List<BookCopy> borrowedBooks;
    private static final int MAX_BORROW_LIMIT = 5;
    private static final Random random = new Random();

    // Constructor to initialize a new member with their name
    public Member(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
        this.memberID = generateMemberID(name);  // Generate a simple unique member ID
    }

    /* There are 36 possible characters (A–Z, 0–9) and a 4-character suffix,
    resulting in 36^4 (1,679,616) possible combinations.
    This provides a sufficiently large ID space for this project,
    though uniqueness is not strictly guaranteed. */


    // Method to generate a unique member ID using the first 3 letters of the name and a random suffix
    private String generateMemberID(String name) {
        String namePart = name.replaceAll("\\s+", "").substring(0, 3);  // First 3 characters of the name
        String randomSuffix = generateRandomSuffix();  // Random suffix for uniqueness
        return namePart + "-" + randomSuffix;
    }

    // Method to generate a 4-character random suffix
    private String generateRandomSuffix() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder suffix = new StringBuilder();
        for (int i = 0; i < 4; i++) {  // 4-character random suffix
            int randomIndex = random.nextInt(characters.length());
            suffix.append(characters.charAt(randomIndex));
        }
        return suffix.toString();
    }

    // Getter for the member's name
    public String getName() {
        return name;
    }

    // Getter for the member's unique ID
    public String getMemberID() {
        return memberID;
    }

    // Method for borrowing a book, checks if the limit is reached and if the book is available
    public void borrowBook(Library library, String bookTitle) {
        if (borrowedBooks.size() < MAX_BORROW_LIMIT) {  // Check if the member can borrow more books
            BookCopy bookCopy = library.findAvailableBook(bookTitle);  // Get the first available copy
            if (bookCopy != null) {
                bookCopy.borrowBook();  // Borrow the book copy for 14 days
                borrowedBooks.add(bookCopy);  // Add the borrowed book copy to the list
                System.out.println(name + " borrowed " + bookCopy.getTitle() + " for 14 days. Checkout Code: " + bookCopy.getCheckoutCode());
            } else {
                System.out.println("No available copies of the book.");
            }
        } else {
            System.out.println("Borrowing limit reached. Cannot borrow more books.");
        }
    }

    // Method for returning a borrowed book by its checkout code
    public void returnBook(String checkoutCode) {
        BookCopy bookToReturn = null;
        for (BookCopy bookCopy : borrowedBooks) {
            if (bookCopy.getCheckoutCode().equals(checkoutCode)) {
                bookToReturn = bookCopy;
                break;
            }
        }

        if (bookToReturn != null) {
            bookToReturn.returnBook();
            borrowedBooks.remove(bookToReturn);
            System.out.println(name + " returned " + bookToReturn.getTitle() + " with checkout code: " + checkoutCode);
        } else {
            System.out.println("This book was not borrowed or the checkout code is incorrect.");
        }
    }

    // Method for viewing all the books currently checked out by the member
    public void viewCheckedOutBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has no books checked out.");
        } else {
            System.out.println(name + "'s currently checked out books:");
            for (BookCopy bookCopy : borrowedBooks) {
                String dueStatus = bookCopy.isOverdue() ? " (OVERDUE!)" : "";  // Mark overdue books
                System.out.println("Checkout Code: " + bookCopy.getCheckoutCode() + " - "
                        + bookCopy.getTitle() + " by " + bookCopy.getAuthor() + " - Due on: "
                        + bookCopy.getDueDate() + dueStatus);
            }
        }
    }

    // Method for checking for overdue books
    public void checkForOverdueBooks() {
        for (BookCopy bookCopy : borrowedBooks) {
            if (bookCopy.isOverdue()) {  // If the book is overdue
                System.out.println("Overdue: " + bookCopy.getTitle() + " by " + bookCopy.getAuthor());
            }
        }
    }

    // Method to display the checkout code for the last borrowed book
    public void displayCheckoutCode() {
        // Assuming the last borrowed book is the one that was just borrowed
        if (!borrowedBooks.isEmpty()) {
            BookCopy lastBorrowedBook = borrowedBooks.get(borrowedBooks.size() - 1);
            System.out.println("Checkout Code: " + lastBorrowedBook.getCheckoutCode());
        } else {
            System.out.println("No books are borrowed yet.");
        }
    }
}
