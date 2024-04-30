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
     * This method takes a string value as a parameter (title, author or ISBN)
     * and lists the name, author, ISBN and page numbers of the book or books that match this string value.
     */
    public static void searchBooks (String string) {
    	for (int i = 0; i < books.length; i++) {
			if (books[i][0] == string) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
			}
			else if (books[i][1] == string) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
			}
			else if (books[i][2] == string) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
			}
			else if (books[i][3] == string) {
				System.out.println("Tittle: " + books[i][0]);
				System.out.println("Author: " + books[i][1]);
				System.out.println("ISBN: " + books[i][2]);
				System.out.println("Page Number: " + books[i][3]);
			}
		}
    }
}
