import java.util.Arrays;
import java.util.Comparator;

// Exercise 6: Library Management System

class Book {
    int bookId;
    String title;
    String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{ID=" + bookId + ", Title='" + title + "', Author='" + author + "'}";
    }
}

public class Exercise6_LibraryManagement {

    // Linear Search by title - O(n)
    public static Book linearSearchByTitle(Book[] books, String targetTitle) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(targetTitle)) {
                return book;
            }
        }
        return null;
    }

    // Linear Search by author - O(n)
    public static Book linearSearchByAuthor(Book[] books, String targetAuthor) {
        for (Book book : books) {
            if (book.author.equalsIgnoreCase(targetAuthor)) {
                return book;
            }
        }
        return null;
    }

    // Binary Search by title - O(log n) — requires sorted array
    public static Book binarySearchByTitle(Book[] sortedBooks, String targetTitle) {
        int left = 0, right = sortedBooks.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = sortedBooks[mid].title.compareToIgnoreCase(targetTitle);

            if (cmp == 0) return sortedBooks[mid];
            else if (cmp < 0) left = mid + 1;
            else right = mid - 1;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println("=== Exercise 6: Library Management System ===\n");

        Book[] books = {
            new Book(1, "The Great Gatsby", "F. Scott Fitzgerald"),
            new Book(2, "To Kill a Mockingbird", "Harper Lee"),
            new Book(3, "1984", "George Orwell"),
            new Book(4, "Pride and Prejudice", "Jane Austen"),
            new Book(5, "Brave New World", "Aldous Huxley")
        };

        // --- Linear Search ---
        System.out.println("--- Linear Search ---");
        String titleTarget = "1984";
        Book found1 = linearSearchByTitle(books, titleTarget);
        System.out.println("Search by title '" + titleTarget + "': " + (found1 != null ? found1 : "Not found"));

        String authorTarget = "Harper Lee";
        Book found2 = linearSearchByAuthor(books, authorTarget);
        System.out.println("Search by author '" + authorTarget + "': " + (found2 != null ? found2 : "Not found"));

        Book notFound = linearSearchByTitle(books, "Moby Dick");
        System.out.println("Search by title 'Moby Dick': " + (notFound != null ? notFound : "Not found"));

        // --- Binary Search ---
        System.out.println("\n--- Binary Search (sorted by title) ---");

        Book[] sortedBooks = books.clone();
        Arrays.sort(sortedBooks, Comparator.comparing(b -> b.title.toLowerCase()));

        System.out.println("Sorted Book Titles:");
        for (Book b : sortedBooks) System.out.println("  " + b);

        System.out.println();
        String binaryTarget = "Pride and Prejudice";
        Book found3 = binarySearchByTitle(sortedBooks, binaryTarget);
        System.out.println("Binary Search '" + binaryTarget + "': " + (found3 != null ? found3 : "Not found"));

        Book notFound2 = binarySearchByTitle(sortedBooks, "Moby Dick");
        System.out.println("Binary Search 'Moby Dick': " + (notFound2 != null ? notFound2 : "Not found"));

        System.out.println("\n--- Time Complexity Analysis ---");
        System.out.println("Linear Search : O(n) — works on unsorted data");
        System.out.println("Binary Search : O(log n) — requires sorted data");
        System.out.println("For small/unsorted libraries → Linear Search.");
        System.out.println("For large/sorted libraries → Binary Search.");
    }
}
