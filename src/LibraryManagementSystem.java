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
     * This method checks whether the specified user is eligible to borrow a book from the library.
     * Firstly, it checks if the user has any transaction record in the transactions array.
     * If not, it returns true, (the user is eligible to borrow a book).
     * If there is a transaction record, it also checks if the book they borrowed is available in the library.
     * If the book is found (the user has returned the book), it returns true.
     * If the book is not found, (the user has not returned the book yet), it returns false.
     */
    public static boolean checkPatronEligibilityForCheckout(String userId) {
    	boolean eligibility = true;
    	int transactionIndex = getTransactionIndexByUserId(userId);
    	
    	if (transactionIndex == -1) {
    		eligibility = true;
		}
    	else {
			String ISBN = transactions[transactionIndex][1];
			if (getBookIndexByISBN(ISBN) == -1) {
				eligibility = false;
			}
		}
    	return eligibility;
    }
    
    /*
     * This method takes the user ID as a parameter and returns the index of the same user in the 'transactions' array.
     * If the user has made no transactions and is not in the array, it returns -1.
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
        int totalBooks=bookQuantity;
        System.out.println("Total number of the books: " + totalBooks);
    }
}
