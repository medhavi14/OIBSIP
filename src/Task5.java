import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Task5 {

    public static class Book {
        private int id;
        private String title;
        private String author;
        private boolean isBorrowed;

        public Book(int id, String title, String author) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.isBorrowed = false;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isBorrowed() {
            return isBorrowed;
        }

        public void borrowBook() {
            this.isBorrowed = true;
        }

        public void returnBook() {
            this.isBorrowed = false;
        }

        @Override
        public String toString() {
            return "Book [ID=" + id + ", Title=" + title + ", Author=" + author + ", Borrowed=" + isBorrowed + "]";
        }
    }

    public static class User {
        private int userId;
        private String username;
        private String password;

        public User(int userId, String username, String password) {
            this.userId = userId;
            this.username = username;
            this.password = password;
        }

        public int getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }

        public String getPassword() {
            return password;
        }

        @Override
        public String toString() {
            return "User [ID=" + userId + ", Username=" + username + "]";
        }
    }

    public static class Library {
        private ArrayList<Book> books;
        private ArrayList<User> users;

        public Library() {
            this.books = new ArrayList<>();
            this.users = new ArrayList<>();
        }

        public void addBook(Book book) {
            books.add(book);
        }

        public void addUser(User user) {
            users.add(user);
        }

        public Book findBookById(int id) {
            for (Book book : books) {
                if (book.getId() == id) {
                    return book;
                }
            }
            return null;
        }

        public User findUserById(int userId) {
            for (User user : users) {
                if (user.getUserId() == userId) {
                    return user;
                }
            }
            return null;
        }

        public void borrowBook(int userId, int bookId) {
            User user = findUserById(userId);
            Book book = findBookById(bookId);

            if (user == null || book == null) {
                System.out.println("Invalid user ID or book ID.");
                return;
            }

            if (book.isBorrowed()) {
                System.out.println("The book is already borrowed.");
            } else {
                book.borrowBook();
                System.out.println("Book borrowed successfully by " + user.getUsername());
            }
        }

        public void returnBook(int userId, int bookId) {
            User user = findUserById(userId);
            Book book = findBookById(bookId);

            if (user == null || book == null) {
                System.out.println("Invalid user ID or book ID.");
                return;
            }

            if (!book.isBorrowed()) {
                System.out.println("The book is not borrowed.");
            } else {
                book.returnBook();
                System.out.println("Book returned successfully by " + user.getUsername());
            }
        }

        public void listBooks() {
            for (Book book : books) {
                System.out.println(book);
            }
        }

        public void listUsers() {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Library library = new Library();

        // Sample data
        library.addUser(new User(1, "user1", "password1"));
        library.addUser(new User(2, "user2", "password2"));
        library.addBook(new Book(1, "Java Programming", "Author A"));
        library.addBook(new Book(2, "Data Structures", "Author B"));

        while (true) {
            System.out.println("\nDigital Library Management System");
            System.out.println("1. List all books");
            System.out.println("2. List all users");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    library.listBooks();
                    break;
                case 2:
                    library.listUsers();
                    break;
                case 3:
                    System.out.print("Enter your user ID: ");
                    int userIdBorrow = scanner.nextInt();
                    System.out.print("Enter the book ID: ");
                    int bookIdBorrow = scanner.nextInt();
                    library.borrowBook(userIdBorrow, bookIdBorrow);
                    break;
                case 4:
                    System.out.print("Enter your user ID: ");
                    int userIdReturn = scanner.nextInt();
                    System.out.print("Enter the book ID: ");
                    int bookIdReturn = scanner.nextInt();
                    library.returnBook(userIdReturn, bookIdReturn);
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
