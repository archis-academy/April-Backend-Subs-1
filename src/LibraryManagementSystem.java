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

    // Book Check
    public  static  boolean  isCheckBooks(String ISBN){
        for (int i=0; i<books.length;i++){
            if(books[i][2] == ISBN){
                return  true;
            }
        }
        return false;
    }
}

