import java.time.LocalDate;

public class LibraryManagementSystem {

    private static final int INDEX = 100;
    private static final String[][] books = new String[INDEX][4]; // [BookName,AuthorName,ISBN,pageNumber]
    private static final String[][] users = new String[INDEX][4]; // [FullName,ID,Email,Password]
    private static final String[][] transactions = new String[INDEX][3]; // [userId,bookId,date]
    private static int bookQuantity = 0;
    private static int userQuantity = 0;
    private static int transactionQuantity = 0;

    public static void main(String[] args) {
    	
    }


	/*
      *This method checks if the book with the given ISBN is available in the library.
	  * Returns the index of the book if the recommended book exists; Otherwise, it returns -1 and returns a message to the user.
	 */
	public static void requestBook(String ISBN){
		int bookIndex=getBookIndexByISBN(ISBN);
		String response="Book procurement process is starting";
		if(bookIndex!=-1){
			response="This book is available in the library and a book suggestion was received and we processed it..";
		}
		System.out.println(response);
	 }
	/*

    
    // This method allows users to make book reservations.
    public static void reserveBook(String ISBN) {
		String response = "Book reservation has been completed successfully.";
    	if (getBookIndexByISBN(ISBN) == -1)
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
				printBooks(books[i][0],books[i][1],books[i][2],books[i][3]);
				printed = true;
				break;
			}
			else if (books[i][1].equals(anything)) {
				printBooks(books[i][0],books[i][1],books[i][2],books[i][3]);
				printed = true;
			}
			else if (books[i][2].equals(anything)) {
				printBooks(books[i][0],books[i][1],books[i][2],books[i][3]);
				printed = true;
				break;
			}
		}
    	
    	if (!printed)
			System.out.println("No book title, author or ISBN number matching this string value is found.");
    }

	/**
	 * This method is responsible for printing the books details
	 * @param title
	 * @param author
	 * @param ISBN
	 * @param pageNumber
	 * @return void
	 */
	private static void printBooks(String title,String author,String ISBN,String pageNumber){
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
    
    /*
     * This method adds the given book to the library.
     * If the book exists, it prints a message indicating this.
     * Otherwise, it prints a message that the book was successfully added.
     */
    public static void addBook(String tittle, String author, String ISBN, String pageNumber) {
		String response = "This book is already available in the library.";
    	if (getBookIndexByISBN(ISBN) == -1) {
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
    public  static  boolean  checkBook(String ISBN){
        boolean response = false;
        for (int i=0; i<bookQuantity;i++){
            if(books[i][2].equals(ISBN)){
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
    public static void viewAvailableBooks(){
        boolean isEmpty=true;
        if(bookQuantity==0){
            isEmpty=false;
        }
        if(isEmpty==false){
            System.out.println("Not Available Books");
        }
        else{
            System.out.println("Available books");
            for(int i=0;i<bookQuantity;i++){
				printBooks(books[i][0],books[i][1],books[i][2],books[i][3]);
            }
         }
    }
	
   // CountTotalBook
    public static void countTotalBooks(){
        System.out.println("Total number of the books: " + bookQuantity);
    }
}
