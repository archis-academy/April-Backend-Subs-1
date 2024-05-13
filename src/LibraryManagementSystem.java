import java.time.LocalDate;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static final int INDEX = 100;
    private static  String[][] books = new String[INDEX][4]; // [BookName,AuthorName,ISBN,pageNumber]
    private static  String[][] users = new String[INDEX][4]; // [FullName,ID,Email,Password]
    private static  String[][] transactions = new String[INDEX][3]; // [userId,bookId,date]
    private static int bookQuantity = 0;
    private static int userQuantity = 0;
    private static int transactionQuantity = 0;

    public static void main(String[] args) {
    	
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
		String response = "Adding book has been completed successfully.";
    	if (getBookIndexByISBN(ISBN) == -1) {
			response = "This book is already available in the library.";
		}
    	else {
        	books[bookQuantity][0] = tittle;
        	books[bookQuantity][1] = author;
        	books[bookQuantity][2] = ISBN;
        	books[bookQuantity][3] = pageNumber;
        	bookQuantity++;
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

   // CountTotalBook
    public static void countTotalBooks(){
        System.out.println("Total number of the books: " + bookQuantity);
    }


	/*
	   * This method allows returning the given book.
       * If the book has been returned, it prints a message stating this.
	*/
	public  static  void returnBook(String userID,String tittle,String author,String ISBN,String pageNumber){
		int transactionIndex=getTransactionIndexByUserId(userID);
		String returnDeadline=checkBookReturnDeadLine(userID);


		String [][] transactionClone=new String[INDEX][3];
		for(int i = 0; i<transactionQuantity;i++){
			if(transactions[i][0].equals(userID)&& transactions[i][1].equals(ISBN)){
				continue;
			}
			transactionClone[i][0]=transactions[i][0];
			transactionClone[i][1]=transactions[i][1];
			transactionClone[i][2]=transactions[i][2];
		}
		transactions=transactionClone;
		addBook(tittle,author,ISBN,pageNumber);
		
		if(transactionIndex != -1 && LocalDate.parse(returnDeadline).isBefore(LocalDate.now())){
			System.out.println("The book was returned late");
			System.out.println("Book returned successfully.");
		}
		else if (transactionIndex != -1) {
			System.out.println("Book returned successfully.");
		}
		else {
			System.out.println("This book has already been returned");
		}
		transactionQuantity--;
		bookQuantity ++;
	}
}


