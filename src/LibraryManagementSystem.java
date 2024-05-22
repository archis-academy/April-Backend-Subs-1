
import java.time.LocalDate;
import java.util.Scanner;


public class LibraryManagementSystem {

    private static final int INDEX = 100;
    private static String[][] books = new String[INDEX][4]; // [BookName,AuthorName,ISBN,pageNumber]
    private static String[][] users = new String[INDEX][4]; // [FullName,ID,Email,Password]
    private static String[][] transactions = new String[INDEX][3]; // [userId,bookId,date]
    private static int bookQuantity = 0;
    private static int userQuantity = 0;
    private static int transactionQuantity = 0;
    private static int bookIDKeeper = 1;
    
    public static void main(String[] args) {
   
    }


    /*
     *This method checks if the book with the given ISBN is available in the library.
     * Returns the index of the book if the recommended book exists; Otherwise, it returns -1 and returns a message to the user.
     */
    public static void requestBook(String tittle, String author) {
        boolean result = isBookExistWithAuthor(tittle, author);
        String response = "Book procurement process is starting";
        if (result) {
            response = "This book is available in the library and a book suggestion was received and we processed it..";
        }
        System.out.println(response);
    }


    // This method takes an ISBN as a parameter and returns the author of that book.
    public static String getAuthorByISBN(String ISBN) {
        int bookIndex = getBookIndexByISBN(ISBN);
        return books[bookIndex][1];
    }

    /*
     * This method checks whether the given author has a book in the library.
     * If they have one or more books, it returns the index of the first book.
     * Otherwise, it returns -1.
     */
    public static int getFirstBookIndexByAuthor(String author) {
        int bookIndex = -1;

        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][1].equals(author)) {
                bookIndex = i;
                break;
            }
        }
        return bookIndex;
    }

    // This method recommends a randomly selected book from the library to the user.
    public static void randomBookRecommendation() {
        int randomIndex = (int) (Math.random() * bookQuantity); // Selects a random number (index) between 0 and bookQuantity
        System.out.println("Recommended book for you:");
        printBooks(books[randomIndex][0], books[randomIndex][1], books[randomIndex][2], books[randomIndex][3]);
    }

    /*
     * This method recommends a book to the user.
     * If there are other books in the library by the authors of the books they have taken but not yet returned, it recommends those to the user.
     * Otherwise, it recommends a random book.
     */
    public static void generateBookRecommendations(String userId) {
        int transactionIndex = getTransactionIndexByUserId(userId);
        String author = null;

        if (transactionIndex == -1) { // If there is no transaction record
            randomBookRecommendation();
        } else { // If there is a transaction record (one or more)
            boolean printed = false; // To check whether any book recommendation is printed or not.
            for (int i = 0; i < transactionQuantity; i++) {
                if (transactions[i][0].equals(userId)) {
                    author = getAuthorByISBN(transactions[i][1]);

                    if (getFirstBookIndexByAuthor(author) == -1) {
                        continue;
                    } else { // If the author has one or more books in the library
                        System.out.println("Other books available in the library by " + author + ", whose book you have previously taken:");
                        for (int j = 0; j < bookQuantity; j++) {
                            if (books[j][1].equals(author)) {
                                printBooks(books[j][0], books[j][1], books[j][2], books[j][3]);
                                System.out.println();
                            }
                        }
                        printed = true;
                    }
                }
            }
            if (!printed) {
                randomBookRecommendation();
            }
        }
    }

static int login(){
    Scanner scanner = new Scanner(System.in);
    System.out.print("Mail address: ");
    String usermail = scanner.nextLine();
    System.out.println("Password : ");
    String password = scanner.nextLine();
    String userId = "hello"; 
    boolean loginSuccessful = checkPassword(usermail, password, userId);
    scanner.close();  
    if(loginSuccessful){
        System.out.println("Logged in");
        return 1;
    } else {
        System.out.println("Login failed.");
        return -1;
    } 
  
}

static void signup(){
    Scanner scanner = new Scanner(System.in);
    System.out.println("Full Name:");
    String fullName = scanner.nextLine();
    System.out.println("Mail Address : ");
    String newmail = scanner.nextLine();
    System.out.println("Password :");
    String newpassword = scanner.nextLine();
    
    users[userQuantity][0] = fullName;
    users[userQuantity][1] = newmail;
    users[userQuantity][2] = newpassword;
    scanner.close();
}



    // This method updates the book's name, author name and page number.
    public static void updateBook(String ISBN, String newBookName, String newAuthorName, String newPageNumber) {
        int bookIndex = getBookIndexByISBN(ISBN);
        String response = "There is no such book in the library.";
        if (bookIndex != -1) {
            books[bookIndex][0] = newBookName;
            books[bookIndex][1] = newAuthorName;
            books[bookIndex][3] = newPageNumber;
            response = "The book information has been successfully updated.";
        }
        System.out.println(response);
    }

    /*
     *This method takes a  value as a parameter (email, password or userIndex)
     *It checks whether there is any invalid entry.
     */
    public static boolean checkPassword(String email, String password, String userId) {
        int userIndex = getUserIndexByUserId(userId);
        return users[userIndex][2].equals(email) && users[userIndex][3].equals(password);
    }

    public static void printCheckPassword(String email, String password, String userId) {
        String response = "Login Successful";
        boolean result = checkPassword(email, password, userId);
        if (!result) {
            response = "Login Failed please check your password or email.";
        }
        System.out.println(response);

    }

    // This method allows users to make book reservations.
    public static void reserveBook(String ISBN) {
        int bookIndex = getBookIndexByISBN(ISBN);
        String response = "Book reservation has been completed successfully." + "\nReserved book: " + books[bookIndex][0];
        if (bookIndex == -1)
            response = "This book is currently not available in the library.";
        System.out.println(response);
    }

    /*
     * This method checks whether the user with the given ID exists in the library system.
     * If the user exists, it returns the index of the user; otherwise, it returns -1.
     */
    public static int getUserIndexByUserId(String userId) {
        int userIndex = -1;
        for (int i = 0; i < userQuantity; i++) {
            if (users[i][1].equals(userId)) {
                userIndex = i;
                break;
            }
        }
        return userIndex;
    }

    // This method updates the user's email and password.
    public static void updatePatronInfo(String userId, String newEmail, String newPassword) {
        int userIndex = getUserIndexByUserId(userId);
        String response = "There is no such user in the library system.";
        if (userIndex != -1) {
            users[userIndex][2] = newEmail;
            users[userIndex][3] = newPassword;
            response = "The user information has been successfully updated.";
        }
        System.out.println(response);
    }

    /*
     * This method checks whether the specified user is eligible to borrow a book from the library.
     * Firstly, it checks if the user has any transaction record in the 'transactions' array. If not, it returns true.
     * If there is a transaction record, it checks the return deadline of the borrowed but not yet returned book.
     * If the return deadline is later than today, it returns true;
     * If it is earlier than today, (the return deadline has passed), it returns false.
     */
    public static boolean checkPatronEligibilityForCheckout(String userId) {
        boolean eligibility = true;
        int transactionIndex = getTransactionIndexByUserId(userId);
        String returnDeadline = checkBookReturnDeadline(userId);

        if (transactionIndex != -1) {
            if (LocalDate.parse(returnDeadline).isBefore(LocalDate.now())) {
                eligibility = false;
            }
        }

        return eligibility;
    }

    /*
     * This method takes the user ID as a parameter and returns the index of the same user in the 'transactions' array.
     * If the user has made no transactions (not in the array), it returns -1.
     */
    public static int getTransactionIndexByUserId(String userId) {
        int transactionIndex = -1;
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userId)) {
                transactionIndex = i;
                break;
            }
        }
        return transactionIndex;
    }

    /*
     * This method returns the return deadline of the book borrowed by the specified user. (15 days)
     */
    public static String checkBookReturnDeadline(String userId) {
        int transactionIndex = getTransactionIndexByUserId(userId);
        String date = transactions[transactionIndex][2];
        return LocalDate.parse(date).plusDays(15).toString();
    }

    /*

     * This method takes a string value as a parameter (title, author or ISBN)
     * and lists the name, author, ISBN and page numbers of the book or books that match this string value.
     */
    public static void searchBooks(String anything) {
        boolean printed = false; // to check whether any message is printed or not.
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][0].equals(anything)) {
                printBooks(books[i][0], books[i][1], books[i][2], books[i][3]);
                printed = true;
                break;
            } else if (books[i][1].equals(anything)) {
                printBooks(books[i][0], books[i][1], books[i][2], books[i][3]);
                printed = true;
            } else if (books[i][2].equals(anything)) {
                printBooks(books[i][0], books[i][1], books[i][2], books[i][3]);
                printed = true;
                break;
            }
        }

        if (!printed)
            System.out.println("No book title, author or ISBN number matching this string value is found.");
    }

    /**
     * This method is responsible for printing the books details
     *
     * @param title
     * @param author
     * @param ISBN
     * @param pageNumber
     * @return void
     */
    private static void printBooks(String title, String author, String ISBN, String pageNumber) {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + ISBN);
        System.out.println("Page Number: " + pageNumber);
    }

    /*
     * This method checks whether the book with the given ISBN exists in the library.
     * If the book exists, it returns the index of the book; otherwise, it returns -1.
     */
    public static int getBookIndexByISBN(String ISBN) {
        int indexOfExistBook = -1;
        for (int i = 0; i < bookQuantity; i++) {
            if (ISBN.equals(books[i][2])) {
                indexOfExistBook = i;
                break;
            }
        }
        return indexOfExistBook;
    }

    // This method generates an ISBN for a book according to the current order.
    public static String generateISBN() {
        String bookID = String.valueOf(bookIDKeeper);
        String ISBN = "";

        if (bookID.length() == 1) {
            ISBN += "ISBN00" + bookID;
        } else if (bookID.length() == 2) {
            ISBN += "ISBN0" + bookID;
        } else {
            ISBN += "ISBN" + bookID;
        }
        bookIDKeeper++;
        return ISBN;
    }

    // This method checks whether there is a book in the library with the given title and author.
    public static boolean isBookExistWithAuthor(String tittle, String author) {
        boolean result = false;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][0].equals(tittle) && books[i][1].equals(author)) {
                result = true;
                break;
            }
        }
        return result;
    }

    /*
     * This method adds the given book to the library.
     * If the book exists, it prints a message indicating this.
     * Otherwise, it prints a message that the book was successfully added.
     */
    public static void addBook(String tittle, String author, String pageNumber) {
        String ISBN = generateISBN();
        String response = "This book is already available in the library.";
        if (getBookIndexByISBN(ISBN) == -1 && !isBookExistWithAuthor(tittle, author)) {
            books[bookQuantity][0] = tittle;
            books[bookQuantity][1] = author;
            books[bookQuantity][2] = ISBN;
            books[bookQuantity][3] = pageNumber;
            bookQuantity++;
            response = "Adding book has been completed successfully.";
        }
        System.out.println(response);
    }

    // Book Check
    public static boolean checkBook(String ISBN) {
        boolean response = false;
        for (int i = 0; i < bookQuantity; i++) {
            if (books[i][2].equals(ISBN)) {
                response = true;
                break;
            }
        }
        return response;
    }


    /*
     *"If a book is unavailable, it is checked whether it exists or not."
     * "If the book exists, it runs the code to display the existing book."
     */
    public static void viewAvailableBooks() {
        boolean isEmpty = bookQuantity != 0;
        if (!isEmpty) {
            System.out.println("Not Available Books");
        } else {
            System.out.println("Available books");
            for (int i = 0; i < bookQuantity; i++) {
                printBooks(books[i][0], books[i][1], books[i][2], books[i][3]);
            }
        }
    }

    // CountTotalBook
    public static void countTotalBooks() {
        System.out.println("Total number of the books: " + bookQuantity);
    }


    /*
     * This method allows returning the given book.
     * If the book has been returned, it prints a message stating this.
     */
    public static void returnBook(String userID, String tittle, String author, String ISBN, String pageNumber) {
        int transactionIndex = getTransactionIndexByUserId(userID);
        String returnDeadline = checkBookReturnDeadline(userID);

        String[][] transactionClone = new String[INDEX][3];
        for (int i = 0; i < transactionQuantity; i++) {
            if (transactions[i][0].equals(userID) && transactions[i][1].equals(ISBN)) {
                continue;
            }
            transactionClone[i][0] = transactions[i][0];
            transactionClone[i][1] = transactions[i][1];
            transactionClone[i][2] = transactions[i][2];
        }
        transactions = transactionClone;
        books[bookQuantity][0] = tittle;
        books[bookQuantity][1] = author;
        books[bookQuantity][2] = ISBN;
        books[bookQuantity][3] = pageNumber;

        if (transactionIndex != -1 && LocalDate.parse(returnDeadline).isBefore(LocalDate.now())) {
            System.out.println("The book was returned late");
            System.out.println("Book returned successfully.");
        } else if (transactionIndex != -1) {
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("This book has already been returned");
        }
        transactionQuantity--;
        bookQuantity++;
    }

    public static void checkOutBook(String userId, String ISBN) {
        int userIndex = getUserIndexByUserId(userId);
        int bookIndex = getBookIndexByISBN(ISBN);
        String response;
    
        if (userIndex == -1) {
            response = "There is no such user in the library system.";
        } else if (bookIndex == -1) {
            response = "This book is currently not available in the library.";
        } else if (!checkPatronEligibilityForCheckout(userId)) {
            response = "User is not eligible to borrow a book. Please return any overdue books.";
        } else {
            // Update the transaction record
            transactions[transactionQuantity][0] = userId;
            transactions[transactionQuantity][1] = ISBN;
            transactions[transactionQuantity][2] = LocalDate.now().toString();
            transactionQuantity++;
    
            for (int i = bookIndex; i < bookQuantity - 1; i++) {
                books[i] = books[i + 1];
            }
            books[bookQuantity - 1] = null; // Listenin son elemanını null olarak ayarla
    
            // Reduce the book quantity
            bookQuantity--;
        }}    



}


