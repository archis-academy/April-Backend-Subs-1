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
     * This method takes a string value as a parameter (title, author or ISBN)
     * and lists the name, author, ISBN and page numbers of the book or books that match this string value.
     */
    public static void searchBooks(String anything) {
    	boolean printed = false; // to check whether any message is printed or not.
    	
    	for (int i = 0; i < bookQuantity; i++) {
			if (books[i][0].equals(anything)) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
				printed = true;
				break;
			}
			else if (books[i][1].equals(anything)) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
				printed = true;
			}
			else if (books[i][2].equals(anything)) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
				printed = true;
				break;
			}
		}
    	
    	if (printed == false) 
			System.out.println("No book title, author or ISBN number matching this string value is found.");
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
