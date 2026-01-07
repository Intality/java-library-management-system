import java.io.*;
import java.util.*;

public class Library implements Serializable {
    private Map<String, List<BookCopy>> books;  // Map of book titles to lists of BookCopy objects
    private List<Member> members;

    public Library() {
        books = new HashMap<>();
        members = new ArrayList<>();
    }

    // Method to add a book copy to the library (handling multiple copies)
    public void addBook(Book book, int numberOfCopies) {
        List<BookCopy> bookCopies = books.computeIfAbsent(book.getTitle(), k -> new ArrayList<>());
        for (int i = 0; i < numberOfCopies; i++) {
            String checkoutCode = book.getTitle() + "-" + (bookCopies.size() + 1);  // Unique code for each copy
            bookCopies.add(new BookCopy(book.getTitle(), book.getAuthor(), checkoutCode));
        }
        System.out.println("Added " + numberOfCopies + " copies of '" + book.getTitle() + "' to the library.");
    }

    // Method to find an available book copy by title
    public BookCopy findAvailableBook(String title) {
        List<BookCopy> bookCopies = books.get(title);
        if (bookCopies != null) {
            for (BookCopy copy : bookCopies) {
                if (copy.isAvailable()) {
                    return copy;  // Return the first available copy
                }
            }
        }
        return null;  // Return null if no available copy is found
    }

    // Method to add a member to the library
    public void addMember(Member member) {
        members.add(member);
    }

    // Method to find a member by their unique Member ID
    public Member findMemberByID(String memberID) {
        for (Member member : members) {
            if (member.getMemberID().equals(memberID)) {
                return member;
            }
        }
        return null;  // Return null if the member ID is not found
    }

    // Method to get the list of all members
    public List<Member> getMembers() {
        return members;
    }

    // Method to list members in the library with name and member ID
    public void listMembers() {
        if (members.isEmpty()) {
            System.out.println("No members found.");
        } else {
            for (Member member : members) {
                System.out.println("Name: " + member.getName() + " - Member ID: " + member.getMemberID());
            }
        }
    }

    // Method to list available books in the library
    public void listAvailableBooks() {
        boolean found = false;
        for (List<BookCopy> bookCopies : books.values()) {
            for (BookCopy copy : bookCopies) {
                if (copy.isAvailable()) {
                    System.out.println(copy.toString());  // Display each available book copy
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No books are available.");
        }
    }

    // Method to save the library data to a file
    public void saveLibrary(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);  // Serialize the library object
            System.out.println("Library data saved.");
        } catch (IOException e) {
            System.out.println("Error saving library data: " + e.getMessage());
        }
    }

    // Method to load the library data from a file
    public static Library loadLibrary(String filename) {
        Library library = new Library();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            library = (Library) ois.readObject();
            System.out.println("Library data loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("Library file not found. Starting a new library.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading library data: " + e.getMessage());
        }
        return library;
    }
}
