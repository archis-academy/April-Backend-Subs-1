public class LibraryManagementSystem {

    private static final int INDEX = 100;
    private static final String[][] books = new String[INDEX][4];
    private static final String[][] users = new String[INDEX][4];
    private static final String[][] transactions = new String[INDEX][4];
    private static int bookQuantity = 0;
    private static int userQuantity = 0;
    private static int transactionQuantity = 0;

    public static void main(String[] args) {

    }
    
    public static void addBook (String tittle, String author, String ISBN, String quantity) {
    	boolean isExist = false;
    	int indexOfExistBook = -1;
    	for (int i = 0; i < books.length; i++) {
			if (tittle.equals(books[i][0]) && author.equals(books[i][1]) && ISBN.equals(books[i][2])) {
				isExist = true;
				indexOfExistBook = i;
				break;
			}
		}
    	
    	if (isExist) {
			books[indexOfExistBook][3] = String.valueOf(Integer.valueOf(books[indexOfExistBook][3]) + Integer.valueOf(quantity));
			bookQuantity += Integer.valueOf(quantity);
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
        	books[emptyIndex][3] = quantity;
        	bookQuantity += Integer.valueOf(quantity);
		}
    	
    	transactionQuantity++;
    	System.out.println("Kitap ekleme işlemi başarıyla tamamlandı.");
    }
}