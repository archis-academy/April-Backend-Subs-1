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
     * This method takes the user ID as a parameter and returns the index of the same user in the 'transactions' array.
     */
    public static int getTransactionIndexByUserId(String userId) {
    	int transactionIndex = 0;
    	for (int i = 0; i < transactionQuantity; i++) {
			if (transactions[i][0].equals(userId)) {
				transactionIndex = i;
				break;
			}
		}
    	return transactionIndex;
    }
    
    /*
     * This method takes the user ID and the duration for how long the book will stay with the user (in days) as parameters.
     * It returns the deadline of the book.
     */
    public static String checkBookReturnDeadline(String userId, int returnPeriod) {
    	int transactionIndex = getTransactionIndexByUserId(userId);
    	String date = transactions[transactionIndex][2];
    	return LocalDate.parse(date).plusDays(returnPeriod).toString();
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
}

