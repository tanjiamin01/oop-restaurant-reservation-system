/*import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
*/
import java.util.Scanner;
import java.util.InputMismatchException;

public class MainUI {
	
    //private static Order[] orders;
    public static void main(String[] args) throws Exception{
        TableManager TableManager = new TableManager();
        ReservationManager ReservationManager = new ReservationManager(TableManager);
        MenuManager MenuManager = new MenuManager();
        StaffManager StaffManager = new StaffManager();
        OrderManager OrderManager = new OrderManager(MenuManager,TableManager,StaffManager, ReservationManager);
        ReportManager ReportManager = new ReportManager(OrderManager, MenuManager);


        // DEFAULT MENU
        MenuItem menuItem1 = new MenuItem(1, "California Maki", 3.00, "3pc");
        menuItem1.setFoodType(1);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(2, "Nigiri Tuna", 2.50, "2pc");
        menuItem1.setFoodType(1);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(3, "Salmon Sashimi", 8.00, "High quality salmon");
        menuItem1.setFoodType(1);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(4, "Matcha Ice Cream", 4.00, "2 scoops of ice cream on a cone");
        menuItem1.setFoodType(2);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(5, "Cake", 3.00, "A slice of dark chocolate cake");
        menuItem1.setFoodType(2);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(6, "Water", 1.00, "Bottled");
        menuItem1.setFoodType(3);
        MenuManager.menuItems.add(menuItem1);
        
        menuItem1 = new MenuItem(7, "Hot Green Tea", 1.50, "Refillable");
        menuItem1.setFoodType(3);
        MenuManager.menuItems.add(menuItem1);
        
        //DEFAULT SETS
        PromotionalSetPackage setA = new PromotionalSetPackage(0,"Set A", "Suitable for 1 person. $3 off original price.");
        setA.promotionMenuItem.add(MenuManager.menuItems.get(0));
        setA.promotionMenuItem.add(MenuManager.menuItems.get(1));
        setA.promotionMenuItem.add(MenuManager.menuItems.get(2));
        setA.promotionMenuItem.add(MenuManager.menuItems.get(3));
        setA.promotionMenuItem.add(MenuManager.menuItems.get(6));
        setA.setpackagePrice(16);
        MenuManager.promos.add(setA);
        
        PromotionalSetPackage setB = new PromotionalSetPackage(1,"Set B", "Dessert set. $1.50 off original price.");
        setB.promotionMenuItem.add(MenuManager.menuItems.get(3));
        setB.promotionMenuItem.add(MenuManager.menuItems.get(4));
        setB.setpackagePrice(5.50);
        MenuManager.promos.add(setB);

        // DEFAULT STAFF
        StaffManager.defaultStaff("Jane", "F", "Chef");
        StaffManager.defaultStaff("John", "M", "Chef");
        StaffManager.defaultStaff("Lydia", "F", "Manager");
        StaffManager.defaultStaff("Peter", "M", "Manager");
        StaffManager.defaultStaff("Lucas", "M", "Waiter");
        StaffManager.defaultStaff("Antonio", "M", "Waiter");
        StaffManager.defaultStaff("Sarah", "F", "Waiter");
        StaffManager.defaultStaff("Isabelle", "F", "Waiter");

        Scanner sc = new Scanner(System.in);

        displayMainPage();
        int mainChoice = sc.nextInt();

        while (mainChoice != 6){
            int choice;
            switch(mainChoice){
                // Orders
                case 1: 
                	ReservationManager.cancelExpiredBooking();
                    displayOrderManagerPage();
                    choice = sc.nextInt();
                    int orderID;
                    
                    while (choice != 6){
                        
						switch(choice){
                            case 1:
                            	// create order
                            	OrderManager.create();
                            	sc.nextLine();
                            	break;
                            	

                            case 2:
                            	// add order item
                            	System.out.println("Add order");
                            	System.out.print("Enter orderID : ");
                					orderID = sc.nextInt();
                					OrderManager.add(orderID);
                                	sc.nextLine();
                					break;


                            case 3:
                                // remove order item
                            	System.out.println("Add order");
                            	System.out.print("Enter orderID : ");
                					orderID = sc.nextInt();
                					OrderManager.remove(orderID);
                                	sc.nextLine();
                					break;

                					
                            case 4:
                            	// print invoice
                            	System.out.print("Enter orderID : ");
                        		orderID = sc.nextInt();
                        		OrderManager.createInvoice(orderID);
                        		sc.nextLine();
                        		break;

                            case 5:
                            	// print report
                            	ReportManager.printReport();
                                break;
                                
                            default:
                                System.out.println("Invalid choice");
                                break;
                        }
                        displayOrderManagerPage();
                        choice = sc.nextInt();
                    }
                    break;

                // Tables
                case 2:
                	ReservationManager.cancelExpiredBooking();
                    displayTableManagerPage();
                    choice = sc.nextInt();
                    while (choice != 6){
                        int printedOccupied = 0;
                        int printedReserved = 0;
                        int printedAvailable = 0;
                        switch(choice){
                            case 1:
                                System.out.print("Number of People: ");
                                int tableId = -1;
                                int numOfPax = sc.nextInt();
                                if ( numOfPax == 0 )
                                    break;
                                tableId = TableManager.findEmptyTable(numOfPax);
                                if ( tableId == -1 ) {
                                    System.out.println("No tables available. ");
                                    break;
                                }
                                System.out.printf("Table %d is available.%n", tableId);
                                // This function is for checking only, will not change table status.
                                // To set status to occupied, see managingOrders().
                                //tableManager.setTableStatus(tableId, Table.Status.OCCUPIED);
                                break;
                            
                            case 5:
                            case 2:
                                for ( Table t : TableManager.getOccupiedTables() ) {
                                    System.out.println(t.toString());
                                    printedOccupied++;
                                }
                                System.out.printf("%d %s table%s in total.\n\n", printedOccupied, "occupied",( printedOccupied == 1 ) ? "" : "s");
                                if ( choice != 5 ){
                                    break;
                                }

                            case 3:
                                for ( Table t : TableManager.getReservedTables() ){
                                    System.out.println(t.toString());
                                    printedReserved++;
                                }
                                System.out.printf("\n%d %s table%s in total.\n", 
                                        printedReserved,
                                        "reserved",
                                        ( printedReserved == 1 ) ? "" : "s");
                                if ( choice != 5 )
                                    break;

                            case 4:
                                    for ( Table t :TableManager.getEmptyTables() ){
                                        System.out.println(t.toString());
                                        printedAvailable++;
                                    }
                                    System.out.printf("\n%d %s table%s in total.\n", 
                                            printedAvailable,
                                            "available",
                                            ( printedAvailable == 1 ) ? "" : "s");
                                    break;

                            default:
                                System.out.println("Invalid choice. Try again...");
                                break;
                        }
                        displayTableManagerPage();
                        choice = sc.nextInt();
                    }
                    break;

                // Reservations
                case 3:
                    displayReservationManagerPage();
                    choice = sc.nextInt();
                    while (choice != 4){
                        switch(choice){
                            case 1:
                            	ReservationManager.cancelExpiredBooking();
                                System.out.print("Enter name: \n");
                                String name = sc.next();
                                System.out.print("Enter contact: \n");
                                String contact = sc.next();
                                System.out.print("Enter date and time (yyyy-mm-ddThh:mm:ss): \n");
                                String date = sc.next();
                                System.out.print("Enter number of dining guests: \n");
                                int numpax = sc.nextInt();
                                ReservationManager.createReservationBooking(name, contact, date, numpax);
                                break;

                            case 2:
                            	ReservationManager.cancelExpiredBooking();
                                System.out.println("Reservation ID: \n");
                                int reservationid = sc.nextInt();
                                ReservationManager.checkReservationBooking(reservationid);
                                break;

                            case 3:
                            	ReservationManager.cancelExpiredBooking();
                                System.out.println("Reservation ID: \n");
                                reservationid = sc.nextInt();
                                ReservationManager.removeReservationBooking(reservationid);
                                break;

                            default:
                                System.out.println("Invalid choice. Try again...");
                                break;
                        }
                        displayReservationManagerPage();
                        choice = sc.nextInt();
                    }
                    break;

                // Menu
                case 4:
                choice = -1; 
                int isNumber = 0;
                while (isNumber == 0) {
                    try {
                        displayMenuManagerPage();
                        choice = sc.nextInt(); 
                        if (choice<1 || choice>9) {
                            System.out.println("Invalid input. Please enter a valid integer 1-9.");
                        }
                        else{
                            isNumber = 1; 
                            break;
                        }
                        
                    }
                    catch(InputMismatchException ex){
                        System.out.println("Invalid input. Please enter a valid integer 1-9.");
                        sc.nextLine();
                    }
                }
                String name;
                Double price =0.00;
                isNumber=0;
                while (choice != 9){
                    switch(choice){
                        case 1:
                            System.out.print("Enter name of new menu item: "); 
                            sc.nextLine();
                            name = sc.nextLine();
                            isNumber=0; 
                            while(isNumber == 0){
                                try {
                                    System.out.print("Enter price of new menu item: ");
                                    price = sc.nextDouble();
                                    if (price<0) {
                                        System.out.println("Price cannot be negative.");
                                    }
                                    else {
                                        isNumber = 1;
                                        break;
                                    }
                                    
                                }
                                catch (InputMismatchException ex) {
                                    System.out.println("Invalid input. Price should be a double.");
                                    sc.nextLine();
                                    
                                }
                                
                            }
                            isNumber =0;
                                
                            
                            System.out.print("Enter description of new menu item: ");
                            sc.nextLine();
                            String description = sc.nextLine();
                            int foodtypeint =0; 
                            while (!(foodtypeint == 1 || foodtypeint == 2 || foodtypeint == 3)) {
                            try {
                                System.out.println("What food type is this item? (1) Main, (2) Dessert, (3) Drink"); 
                                foodtypeint = sc.nextInt(); 
                                if (foodtypeint<1 ||foodtypeint>3) {
                                    System.out.println("Invalid input. Please input an integer from 1-3.");
                                }
                            }
                            catch (InputMismatchException ex) {
                                System.out.println("Invalid input. Please input an integer from 1-3.");
                                sc.nextLine();

                            }
                            }
                            
                            // DOUBLE CHECK THIS
                            MenuManager.createMenuItem(MenuManager.menuItems.size(), name, price, description, foodtypeint);
                            break;

                        case 2:
                            System.out.println("What menu item do you want to remove?");
                            sc.nextLine();
                            name = sc.nextLine();
                            MenuManager.removeMenuItem(name);
                            break;

                        case 3:
                            System.out.print("Name of menu item you want to update: ");
                            sc.nextLine();
                            name = sc.nextLine();
                            MenuManager.updateMenuItemName(name);
                            
                            break;

                        case 4:
                            System.out.print("Name of promotional set package: ");
                            sc.nextLine();
                            name = sc.nextLine();
                            System.out.print("Description of promotional set package: ");
                            description = sc.nextLine();
                            // CHECK THIS
                            MenuManager.createPromotion(MenuManager.promos.size(), name, description);
                            break;
                            

                        case 5:
                            System.out.print("Name of promotional set package to remove: ");
                            sc.nextLine();
                            name = sc.nextLine();
                            MenuManager.removePromotion(name);
                            break;

                        case 6:
                            System.out.print("Name of promotional set package to update: ");
                            sc.nextLine();
                            name = sc.nextLine();
                            MenuManager.updatePromotion(name);
                            break;
                        case 7: 
                            int j=0;
                            System.out.println("--------------------------------------------");
                            System.out.println("MAINS: ");
                            System.out.println("--------------------------------------------");
                            for (int i =0; i<MenuManager.menuItems.size(); i++) {
                                if (MenuManager.menuItems.get(i).getFoodTypeInt() == 1) {
                                    System.out.println("\nITEM "+ (j+1) +":"
                                            + "\nName: " + MenuManager.menuItems.get(i).getName() + 
                                            " \nPrice: $ " + MenuManager.menuItems.get(i).getPrice() + 
                                            " \nDescription: " + MenuManager.menuItems.get(i).getDescription());//  + 
                                            // " \nFoodType: " + MenuManager.menuItems.get(i).getFoodType() +"\n" );
                                    j++;
                                }
                                
                                
                                
                            }
                            
                            
                            System.out.println("\n");
                            j=0;
                            System.out.println("--------------------------------------------");
                            System.out.println("DESSERTS: ");
                            System.out.println("--------------------------------------------");
                            for (int i =0; i<MenuManager.menuItems.size(); i++) {
                                if (MenuManager.menuItems.get(i).getFoodTypeInt() == 2) {
                                    System.out.println("\nITEM "+ (j+1) +":"
                                            + "\nName: " + MenuManager.menuItems.get(i).getName() + 
                                            " \nPrice: $ " + MenuManager.menuItems.get(i).getPrice() + 
                                            " \nDescription: " + MenuManager.menuItems.get(i).getDescription()); // + 
                                            // " \nFoodType: " + MenuManager.menuItems.get(i).getFoodType() +"\n" );
                                    j++;
                                }
                                
                                
                            }
                            
                            System.out.println("\n");
                            j=0;
                            
                            System.out.println("--------------------------------------------");
                            System.out.println("DRINKS: ");
                            System.out.println("--------------------------------------------");
                            for (int i =0; i<MenuManager.menuItems.size(); i++) {
                                if (MenuManager.menuItems.get(i).getFoodTypeInt() == 3) {
                                    System.out.println("\nITEM "+ (i+1) +":"
                                            + "\nName: " + MenuManager.menuItems.get(i).getName() + 
                                            " \nPrice: $ " + MenuManager.menuItems.get(i).getPrice() + 
                                            " \nDescription: " + MenuManager.menuItems.get(i).getDescription()); // + 
                                            //" \nFoodType: " + MenuManager.menuItems.get(i).getFoodType() +"\n" );
                                    j++;
                                }
                                
                                
                            }
                            
                            System.out.println("\n");
                            
                            break;
                        case 8: 
                            for (int i =0; i<MenuManager.promos.size(); i++) {
                                System.out.println("\n");
                                System.out.println("--------------------------------------------");
                                System.out.println("PROMOTIONAL PACKAGE " + (i+1));
                                System.out.println("--------------------------------------------");
                                System.out.println( "Name:" + MenuManager.promos.get(i).getpackageName());
                                System.out.println("Description:" + MenuManager.promos.get(i).getpackageDescription());
                                System.out.println("Price: $ " + MenuManager.promos.get(i).getpackagePrice());
                                for (j =0; j< MenuManager.promos.get(i).promotionMenuItem.size();j++) {
                                    
                                    System.out.println("\nItem "+ (j+1) +":"
                                            + "\n	Name: " + MenuManager.promos.get(i).promotionMenuItem.get(j).getName() + 
                                            "\n 	Price: $ " + MenuManager.promos.get(i).promotionMenuItem.get(j).getPrice() + 
                                            "\n 	Description: " + MenuManager.promos.get(i).promotionMenuItem.get(j).getDescription() +  
                                             "\n 	FoodType: " + MenuManager.promos.get(i).promotionMenuItem.get(j).getFoodType() +"\n");
                                    
                                    
                                    
                                    
                                }
                                
                                
                            }
                            break;
                        case 9: break;

                        default:
                            System.out.println("Invalid choice. Try again...");
                            break;
                    }
                    isNumber =0; 
                    while (isNumber == 0) {
                        try {
                            displayMenuManagerPage();
                            choice = sc.nextInt(); 
                            if (choice<1 || choice>9) {
                                System.out.println("Invalid input. Please enter a valid integer 1-9.");
                            }
                            else{
                                isNumber = 1; 
                                break;
                            }
                            
                        }
                        catch(InputMismatchException ex){
                            System.out.println("Invalid input. Please enter a valid integer 1-9.");
                            sc.nextLine();
                        }
                    }
                }
                break;

                // Staff
                case 5:
                    displayStaffManagerPage();
                    choice = sc.nextInt();
                    while (choice != 5){
						switch(choice){
                            case 1:
                                System.out.print("Enter name: ");
                                sc.nextLine();
                                name = sc.next();
                                System.out.print("Enter gender(F/M): ");
                                String gender = sc.next();
                                System.out.print("Enter job title(Chef/Manager/Waiter): ");
                                String jobTitle = sc.next();
                                StaffManager.createStaff(name,gender,jobTitle);
                                break;

                            case 2:
                                StaffManager.viewAllStaff();
                                break;

                            case 3:
                                int employeeId = -1;
                                isNumber = 0;
                                while (isNumber == 0){
                                    try{
                                        System.out.print("Enter employee ID: ");
                                        sc.nextLine();
                                        employeeId = sc.nextInt();
                                        if (employeeId < 0){
                                            System.out.println("Employee ID cannot be negative.");
                                        }
                                        else{
                                            isNumber = 1;
                                            break;
                                        }
                                    }
                                        catch (InputMismatchException ex) {
                                            System.out.println("Invalid input. Employee ID should be a integer.");
                                            sc.nextLine();
                                            
                                        }
                                }

                                StaffManager.removeStaff(employeeId);
                                break;

                            case 4:
                            employeeId = -1;
                            isNumber = 0;
                            while (isNumber == 0){
                                try{
                                    System.out.print("Enter employee ID: ");
                                    sc.nextLine();
                                    employeeId = sc.nextInt();
                                    if (employeeId < 0){
                                        System.out.println("Employee ID cannot be negative.");
                                    }
                                    else{
                                        isNumber = 1;
                                        break;
                                    }
                                }
                                    catch (InputMismatchException ex) {
                                        System.out.println("Invalid input. Employee ID should be a integer.");
                                        sc.nextLine();
                                        
                                    }
                            }

                                StaffManager.updateStaff(employeeId);
                                break;

                            default:
                                System.out.println("Invalid choice. Try again...");
                                break;
                        }
                        displayStaffManagerPage();
                        choice = sc.nextInt();
                    }
                
                default:
                    break;
            }
            displayMainPage();
            mainChoice = sc.nextInt();
        }
        System.out.println("Thank you and goodbye!");
        System.out.println("Exiting system......");
        System.exit(0);
    }

    public static void displayMainPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "              ☆ Sushi Palace ☆              \n"+
        "    Reservation and Point of Sale System    \n"+
        "--------------------------------------------\n"+
        "Welcome! What would you like to do today?   \n"+
        "(1) Manage Orders                           \n"+
        "(2) Manage Tables                           \n"+
        "(3) Manage Reservations                     \n"+
        "(4) Manage Menu                             \n"+
        "(5) Manage Staff                            \n"+
        "(6) Exit                                    \n"+
        "                                            \n"+
        "Enter choice (1-6): "
        );
    }

    public static void displayOrderManagerPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "                Manage Orders               \n"+
        "--------------------------------------------\n"+
        "Select task:                                \n"+
        "(1) Create Order                            \n"+
        "(2) Add Item to Order                       \n"+
        "(3) Remove Item from Order                  \n"+
        "(4) Print Order Invoice                     \n"+
        "(5) Print Sale Revenue Report               \n"+
        "(6) Back to Main                            \n"+
        "                                            \n"+
        "Enter choice (1-6): "
        );
    }

    public static void displayTableManagerPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "                Manage Tables               \n"+
        "--------------------------------------------\n"+
        "Select task:                                \n"+
        "(1) Check Table Availability                \n"+
        "(2) List Occupied Tables                    \n"+
        "(3) List Reserved Tables                    \n"+
        "(4) List Available Tables                   \n"+
        "(5) List All Tables                         \n"+
        "(6) Back to Main                            \n"+
        "                                            \n"+
        "Enter choice (1-6): "
        );
    }

    public static void displayReservationManagerPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "             Manage Reservations            \n"+
        "--------------------------------------------\n"+
        "Select task:                                \n"+
        "(1) Create Reservation Booking              \n"+
        "(2) Check Reservation Booking               \n"+
        "(3) Remove Reservation Booking              \n"+
        "(4) Back to Main                            \n"+
        "                                            \n"+
        "Enter choice (1-4): "
        );
    }

    /*
                (1) Create Menu Item\n"
				+ "(2) Remove Menu Item\n"
				+ "(3) Update Menu Item\n"
				+"(4) Create Promotional Package\n"
				+ "(5) Remove Promotional Package\n"
				+ "(6) Update Promotional Package\n"
				+ "(7) Print Menu\n"
				+ "(8) Print Promotional Set Packages\n"
				+ "(9) Quit\n");
                */
    
    public static void displayMenuManagerPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "                 Manage Menu                \n"+
        "--------------------------------------------\n"+
        "Select task:                                \n"+
        "(1) Create Menu Item                        \n"+
        "(2) Remove Menu Item                        \n"+
        "(3) Update Menu Item                        \n"+
        "(4) Create Promotional Package              \n"+
        "(5) Remove Promotional Package              \n"+
        "(6) Update Promotional Package              \n"+
        "(7) Print Menu                              \n"+
        "(8) Print Promotional Package               \n"+
        "(9) Back to Main                            \n"+
        "                                            \n"+
        "Enter choice (1-9): "
        );
    }

    public static void displayStaffManagerPage(){
        System.out.print(
        "--------------------------------------------\n"+
        "                Manage Staff                \n"+
        "--------------------------------------------\n"+
        "Select task:                                \n"+
        "(1) Create Staff                            \n"+
        "(2) View All Staff                          \n"+
        "(3) Remove Staff                            \n"+
        "(4) Update Staff Details                    \n"+
        "(5) Back to Main                            \n"+
        "                                            \n"+
        "Enter choice (1-5): "
        );
    }
}

