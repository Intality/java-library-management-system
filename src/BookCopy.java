import java.io.Serializable;
import java.time.LocalDate;

public class BookCopy implements Serializable {
    private String title;
    private String author;
    private boolean isBorrowed;
    private LocalDate dueDate;
    private final String checkoutCode;  // Unique identifier for each book copy

    public BookCopy(String title, String author, String checkoutCode) {
        this.title = title;
        this.author = author;
        this.checkoutCode = checkoutCode;
        this.isBorrowed = false;
        this.dueDate = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return !isBorrowed;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getCheckoutCode() {
        return checkoutCode;  // This returns the unique checkout code
    }

    public void borrowBook() {
        if (!isBorrowed) {
            isBorrowed = true;
            dueDate = LocalDate.now().plusDays(14);  // Set due date for 14 days
        } else {
            System.out.println("This copy is already borrowed.");
        }
    }

    public void returnBook() {
        if (isBorrowed) {
            isBorrowed = false;
            dueDate = null;
        } else {
            System.out.println("This copy was not borrowed.");
        }
    }

    public boolean isOverdue() {
        return isBorrowed && LocalDate.now().isAfter(dueDate);
    }

    @Override
    public String toString() {
        return title + " by " + author + (isBorrowed ? " (Borrowed, Due on " + dueDate + ")" : " (Available)");
    }
}
