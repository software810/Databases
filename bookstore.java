import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class bookstore {
    public static void main(String[] args) {
        // Create new input to store user's chosen number
        Scanner input = new Scanner(System.in);
        
        // Continue displaying the menu until user enters 0 to exit 
        while(true) {
            System.out.println("Welcome to the Book Store:");
            System.out.println("1. Enter book");
            System.out.println("2. Update book");
            System.out.println("3. Delete book");
            System.out.println("4. Search books");
            System.out.println("0. Exit");
            System.out.print("Please enter a number: ");
            
            // Capture user input and run function based on the number chosen by user
            int numberInput = input.nextInt();
            
            if (numberInput == 1) {
                // Get all details from user to store in the variables in enterBook
                System.out.print("Please enter the ID of book: ");
                int option1ID = input.nextInt();
                System.out.print("Please enter the Title of the book: ");
                String option1Title = input.next();
                System.out.print("Please enter the Author of the book: ");
                String option1Author = input.next();
                System.out.print("Please enter the quantity of books: ");
                int option1Qty = input.nextInt();
                enterBook(option1ID, option1Title, option1Author, option1Qty);
                }
            
            else if (numberInput == 2) {
                // Get the id of the book the user wishes to update
                System.out.println("Please enter the ID of the book you want to update: ");
                int option2Input = input.nextInt();
                updateBook(option2Input);
                }
            
            else if (numberInput == 3) {
                // Get the id of the book the user wishes to delete
                System.out.println("Please enter the ID of the book you want to delete: ");
                int option3Input = input.nextInt();
                deleteBook(option3Input);
                }
            
            else if (numberInput == 4) {
                // Get the id of the book the user wishes to search
                System.out.println("Please enter the ID of the book you are looking for: ");
                int option4Input = input.nextInt();
                searchBooks(option4Input);
                }
            
            else if (numberInput == 0) {
                // Exit the program should the user enter 0
                System.out.println("You have exited the Book Store");
                System.exit(0);
                }
            
            else {
                System.out.println("We do not recognize the input and therefore program will quit.");
                System.exit(0);
                }
            }
        }
    
    // Enter Book - Option 1
    public static void enterBook(int id, String title, String author, int qty) {
        try (
                // Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection (
                "jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "task" );
                
                // Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
                
                ) {
            
            // Enter a new book using the variables from enterBook
            String sqlInsert = "insert into books " 
            + "values (" + id + "," + '"' + title + '"' + "," + '"' + author + '"' +  "," + qty + ")";
            
            // Run the sql code to enter the book
            stmt.executeUpdate (sqlInsert);
            System.out.println("The book has been added to the bookstore.");
            System.out.println("\n");
            
            } catch (SQLException ex) {
                ex.printStackTrace();
                }
        
        // Close the resources - Done automatically using try-with-resources
        }
    
    // Update Book - Option No 2
    public static void updateBook(int id) {
    try (
            // Allocate a database 'Connection' object
            Connection conn = DriverManager.getConnection (
            "jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "task");
            
            // Allocate a 'Statement' object in the Connection
            Statement stmt = conn.createStatement();
            ) {
        
        // Ask user what about the book they want to update
        System.out.print("What do you want to update? Please enter only one of these: id, title, author, qty: ");
        
        // Create the scanner objects to be used
        Scanner Option2 = new Scanner(System.in);
        Scanner newValue = new Scanner(System.in);
        
        // Variable to store the user's choice
        String confirmationOption2 = Option2.nextLine();
        
        // Update only the ID
        if (confirmationOption2.equalsIgnoreCase("id")){
            // Ask the user what the new id must be
            System.out.print("What would you like the new id to be? ");
            // Store the new id
            int option2ID = newValue.nextInt();
            // Declare this new id in the sql string
            String idUpdate = "update books set id = "+ option2ID + " where id =" + id;
            // Update the id using the sql string
            stmt.executeUpdate(idUpdate);
            // Show the updated book details
            String strSelect = "select * from books where id = '"+ option2ID +"'";
            ResultSet rset = stmt . executeQuery ( strSelect );
            while ( rset . next ()) {
                int bookId = rset.getInt("id");
                String title = rset . getString ( "title" );
                String author = rset . getString ("author");
                int qty = rset.getInt("qty");
                System . out . println ("The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
                System.out.println("\n");
                }
            
            } 
        
        // Update only the title
        else if (confirmationOption2.equalsIgnoreCase("title")) {
            // Ask user for the new title   
            System.out.print("What would you like the new title to be? ");
            String option2Title = newValue.nextLine();
            // Declare the new title in the sql string
            String idUpdate = "update books set title = '"+ option2Title + "' where id =" + id;
            // Update the title using the sql string
            stmt.executeUpdate(idUpdate);
            // Show the updated book details
            String strSelect = "select * from books where id = '"+ id +"'";
            ResultSet rset = stmt . executeQuery ( strSelect );
            while ( rset . next ()) {
                int bookId = rset.getInt("id");
                String title = rset . getString ( "title" );
                String author = rset . getString ("author");
                int qty = rset.getInt("qty");
                System . out . println ("The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
                System.out.println("\n");
                }
            
            }
        
        // Update only the author
        else if (confirmationOption2.equalsIgnoreCase("author")) {
            // Ask who the author is
            System.out.print("Who is the Author? ");
            // Save the new author
            String option2Author = newValue.nextLine();
            // Declare the new author in the sql string
            String idUpdate = "update books set author = '"+ option2Author + "' where id =" + id;
            // Update the author using the sql string
            stmt.executeUpdate(idUpdate);
            // Show the updated book details
            String strSelect = "select * from books where id = '"+ id +"'";
            ResultSet rset = stmt . executeQuery ( strSelect );
            while ( rset . next ()) {
                int bookId = rset.getInt("id");
                String title = rset . getString ( "title" );
                String author = rset . getString ("author");
                int qty = rset.getInt("qty");
                System . out . println ("The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
                System.out.println("\n");
                }
            
            }
        
        // Update only the quantity
        else if (confirmationOption2.equalsIgnoreCase("qty")) {
            // Ask what the new quantity is
            System.out.print("What would you like the new quantity to be? ");
            // Save the new quantity
            int option2Qty = newValue.nextInt();
            // Declare the new quantity in the sql string
            String idUpdate = "update books set id = "+ option2Qty + " where id =" + id;
            // Update the new quantity using the sql string
            stmt.executeUpdate(idUpdate);
            // Show the updated book details
            String strSelect = "select * from books where id = '"+ option2Qty +"'";
            ResultSet rset = stmt . executeQuery ( strSelect );
            while ( rset . next ()) {
                int bookId = rset.getInt("id");
                String title = rset . getString ( "title" );
                String author = rset . getString ("author");
                int qty = rset.getInt("qty");
                System . out . println ("The updated record is now: "+ bookId + ": " + title + " - " + author + " - " + qty);
                System.out.println("\n");
                }
            
            }
        
        else {
            System.out.println("Sorry, we did not understand that input.");
            }
        
        } catch ( SQLException ex ) {
            ex . printStackTrace ();
            }
    
    // Close the resources - Done automatically by try-with-resources
    }
    
    // Delete book - Option 3
    public static void deleteBook(int id) {
    try (
            // Allocate a database 'Connection' object
            Connection conn = DriverManager.getConnection (
            "jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "task" );
            
            // Allocate a 'Statement' object in the Connection
            Statement stmt = conn.createStatement();
            ) {
        
        // Find the book to be deleted      
        String sqlRecord = "select * from books where id = '"+ id +"'";
        ResultSet confirmDeletion = stmt . executeQuery ( sqlRecord );
        // Move the cursor to the next row, return false if no more row
        while (confirmDeletion.next ()) {
            String title = confirmDeletion.getString ("title");
            String author = confirmDeletion.getString ("author");
            //Show the book to be deleted
            System . out . println ("The record to be deleted is: " + title + " - " + author );
            }
        
        // Confirm with user if they really want to delete the book
        System.out.println("Are you sure you want to delete the above record? - yes or no: ");
        
        // Create scanner object to store the user's choice of yes or no to delete the book
        Scanner Option3 = new Scanner(System.in);
        String confirmationOption3 =Option3.nextLine();
        if (confirmationOption3.equalsIgnoreCase("yes")){
            // Store the id of the book to be deleted in the sql string
            String sqlDelete = "delete from books where id = '"+ id +"'";
            // Delete the book
            stmt.executeUpdate(sqlDelete);
            // Confirm that the book has been deleted
            System . out . println ("Record has been deleted.");        
            }
        
        else if (confirmationOption3.equalsIgnoreCase("no")) {
            System.out.println("Record will not be deleted");
            }
        
        } catch ( SQLException ex ) {
            ex . printStackTrace ();
            }
    
    // Close the resources - Done automatically by try-with-resources
    }
    
    // Search Books function - Option No 4
    public static void searchBooks(int id) {
    try (
            // Allocate a database 'Connection' object
            Connection conn = DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "task" );
            // Allocate a 'Statement' object in the Connection
            Statement stmt = conn.createStatement();
            
            ) {
        
        // Declare the book to be searched in the sql string
        String strSelect = "select * from books where id = '"+ id +"'";
        ResultSet rset = stmt . executeQuery ( strSelect );
        // Show the details of the book
        while ( rset . next ()) {
            String title = rset . getString ("title");
            String author = rset . getString ("author");
            System.out.println ("A book is found!");
            System.out.println(id + ": " + title + " - " + author );
            System.out.println("");
            }
        
        } catch ( SQLException ex ) {
            ex . printStackTrace ();
            }
    
    // Close the resources - Done automatically by try-with-resources
    }
}