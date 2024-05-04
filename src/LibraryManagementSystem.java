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
        boolean b=true;
        if(bookQuantity==0){
            b=false;
        }
        if(b==false){
            System.out.println("Not Available Books");
        }
        else{
            System.out.println("Available books");
            for(int i=0;i<bookQuantity;i++){
                System.out.println("Title: " +books[i][0]);
                System.out.println("Author: " +books[i][1]);
                System.out.println("ISBN: " +books[i][2]);
                System.out.println("Page Number: " +books[i][3]);
            }
         }
    }


}

