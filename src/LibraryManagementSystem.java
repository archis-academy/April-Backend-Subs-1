public class LibraryManagementSystem {

    private static final int INDEX = 100;
    private static final String[][] books = new String[INDEX][4];
    private static final String[][] users = new String[INDEX][4];
    private static final String[][] transactions = new String[INDEX][3];
    private static int bookQuantity = 0;
    private static int userQuantity = 0;
    private static int transactionQuantity = 0;

    public static void main(String[] args) {

    }
    
    /*
     * This method checks whether the book with the given ISBN exists in the library.
     * If the book exists, it returns the index of the book; otherwise, it returns -1.
     */
    public static int getBookIndexByISBN (String ISBN) {
    	int indexOfExistBook = -1;
    	for (int i = 0; i < books.length; i++) {
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
    public static void addBook (String tittle, String author, String ISBN, String pageNumber) {
    	if (getBookIndexByISBN(ISBN) == -1) {
			System.out.println("This book is already available in the library.");
		}
    	else {
        	int emptyIndex = -1;
        	for (int i = 0; i < books.length; i++) {
    			if (books[i] == null) {
    				emptyIndex = i;
    				break;
    			}
    		}
        	
        	books[emptyIndex][0] = tittle;
        	books[emptyIndex][1] = author;
        	books[emptyIndex][2] = ISBN;
        	books[emptyIndex][3] = pageNumber;
        	bookQuantity++;
        	
        	System.out.println("Adding book has been completed successfully.");
		}
    }
}