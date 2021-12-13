import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * OrderManager class that manages orders in the restaurant such as creating and editing orders
 */
public class OrderManager {
	
	private MenuManager MenuManager;
	private TableManager TableManager;
	private StaffManager StaffManager;
	private ReservationManager ReservationManager;
	private ArrayList <Order> orders;
	private int activeOrders;
	
	
	BufferedWriter out;
	BufferedReader in;
	
	/**
	 * Constructor for OrderManager
	 * @param MenuManager			associated MenuManager in the restaurant
	 * @param TableManager			associated TableManager in the restaurant
	 * @param StaffManager			associated StaffManager in the restaurant
	 * @param ReservationManager	associated ReservationManager in the restaurant
	 */
	//reads orders from order.txt
	public OrderManager(MenuManager MenuManager, TableManager TableManager, StaffManager StaffManager, ReservationManager ReservationManager){
		
		this.MenuManager = MenuManager;
		this.TableManager = TableManager;
		this.StaffManager  = StaffManager;
		this.ReservationManager = ReservationManager;
		orders = new ArrayList <Order>();
		activeOrders = 0;
		
		//initializing the file at the beginning of the program
		try {
			File file = new File("order.txt");
	        BufferedReader br = new BufferedReader(new FileReader(file));
	        List<String> lines = new ArrayList<String>();
	        String line = br.readLine();
	        while(line != null) {
	            lines.add(line.replace(">", ""));
	            line = br.readLine();
	        }
	        
	        int orderID, staffID, tableID, numPax, itemSize, promoSize, tempItemID, tempSetID;
	        boolean active;
	        double price;
	        Order order;
	        Calendar date = Calendar.getInstance();
	        while(line != null) {
	            lines.add(line.replace("<", ""));
	            line = br.readLine();
	        }
	        int i=0;
	        //assigning variables
	        while (i<lines.size()){
				 orderID = Integer.parseInt(lines.get(i));
				 
				 staffID = Integer.parseInt(lines.get(i+1));
				 
				 tableID = Integer.parseInt(lines.get(i+2));
				 
				 numPax = 	   Integer.parseInt(lines.get(i+3));
				 
				 itemSize= Integer.parseInt(lines.get(i+4));
				 
				 String[] itemID = lines.get(i+5).split("\\|");
				 
				 promoSize = Integer.parseInt(lines.get(i+6));
				 
				 String[] setID = lines.get(i+7).split("\\|");
				 
				 active =  Boolean.parseBoolean(lines.get(i+9));
				 
				 price =   Double.parseDouble(lines.get(i+10));
				 
				 String[] stringDate = lines.get(i+11).split("\\|");
				 				 
				 date.set(Integer.parseInt(stringDate[0]), 
						 
				 Integer.parseInt(stringDate[1]),Integer.parseInt(stringDate[2]), Integer.parseInt(stringDate[3]), Integer.parseInt(stringDate[4]));
				 
				 order =   new Order(orderID,0,0,staffID,tableID,numPax,date.getTime(),active,price);
				 
				 //for each menu item
				 for(int k =0;k<itemSize;k++){
					 tempItemID=Integer.parseInt(itemID[k]);
					 // add to order
					 order.addMenuItem(tempItemID);
				 	}
				 
				 //for each promo item
				 for(int l =0;l<promoSize;l++){
					 tempSetID=Integer.parseInt(setID[l]);
					 // add to order
					 order.addMenuSet(tempSetID);
				 	}
				 
				 //if order & table is occupied
				 orders.add(order);
				 if(order.isActive()) {
					 
				 	TableManager.setTableOccupied(tableID);
				 }
				 
				 
				 i+=13;
			}
				br.close();
		
		}
	 		catch(IOException e){
	 			  
	 			  System.out.println("There was a problem:" + e);
	 			  
	 		}
			catch (NumberFormatException e) {
				
				System.out.println("This is not a number");
				
			}
		
		for(int i=0;i<orders.size();i++)
			// if order is active
			if(orders.get(i).isActive())
				// add to count
				activeOrders++;
	}
	
	/**
	 * Write orders to the order.txt
	 */
	public void orderOverWrite(){
        try{
         out = new BufferedWriter(new FileWriter("order.txt",false)); 
         
         int tempItemID, tempSetID;
         
         Calendar date = Calendar.getInstance();
         
         for(int count=0;count<orders.size();count++){
        	 
        	 date.setTime(orders.get(count).getDate());
	 out.write(orders.get(count).getOrderID()+"\n"+
    		   orders.get(count).getStaffID()+"\n"+
    		   orders.get(count).getTableID()+"\n"+
    		   orders.get(count).getNumPax()+"\n"+
    		   orders.get(count).getItemSize()+"\n");
	 
	 		for(int i=0;i<orders.get(count).getItemSize();i++){
	 			
	 			tempItemID = orders.get(count).getItemIdByIndex(i);
	 			out.write(String.valueOf(tempItemID)+"|");
	 			
	 		}
	 			out.write("\n"+orders.get(count).getSetSize()+"\n");
	 			
	 		for(int j=0;j<orders.get(count).getItemSize();j++){
	 			
		 			tempSetID = orders.get(count).getSetIdByIndex(j);
		 			out.write(String.valueOf(tempSetID)+"|");
		 			
		 		}	
	 		
    		   out.write("\n"+
		 		orders.get(count).isActive()+"\n"+
    		   String.valueOf(orders.get(count).getTotalPrice())+"\n"+
		 		date.get(Calendar.YEAR)+"|"+date.get(Calendar.MONTH)+"|"+date.get(Calendar.DAY_OF_MONTH)+"|"+date.get(Calendar.HOUR_OF_DAY)+"|"+date.get(Calendar.MINUTE)+"|");
				out.newLine();
				out.newLine();
        }
         out.close();
         }
         catch(IOException e){
         System.out.println("There was a problem:" + e);
         }

	}

	/**
	 * gets the number of active orders
	 * @return number of active orders
	 */
	public int getActiveOrders(){
		
		// get number of active orders
		return activeOrders;
		
	}
	
	/**
	 * gets the number of orders in the restaurant
	 * @return number of orders
	 */
	public int getSize(){
		
		// get number of orders
		return orders.size();
		
	}
	  
	/**
	 * checks if an order with certain orderID exists
	 * @param id order ID to check
	 * @return true if exists, false if it doesnt
	 */
	public boolean checkOrderById(int id){
		  for(int i=0;i<orders.size();i++)
			  // if orderID exists
			  if(orders.get(i).getOrderID() == id)
				  
				  return true;
		  // if orderID doesn't exist
		  return false;
	  }
	  
	/**
	 * generates an orderID
	 * @return new orderID
	 */
	public int generateID(){
		  // previous id generated
		  int lastID;
		  
		  for(int i=0;i<orders.size();i++) 
			  
			  if(!checkOrderById(i))	
				  
				  return i;
		  // if there are no orders
		  if (orders.size()==0)
			  //orderID is 0
			  lastID=0;
		  
		  //if there are orders
		  else{
			  
			  lastID = orders.get(orders.size()-1).getOrderID();
			  
		  //get an unused ID
		  while(checkOrderById(lastID))
			  // orderID is previous orderID add 1
			  lastID++;
		  
			  }
		  return lastID; 
				  
	  }
	  
	/**
	 * returns index of order in order array by order ID
	 * @param id order ID
	 * @return corresponding index of order in array
	 */
	public int getIndexByID(int id){
		
		  for(int i=0;i<orders.size();i++)
			  
			  if(orders.get(i).getOrderID() == id)
				  
				  return i;
		  
		  return -1; //if not found
	  }
	
	/**
	 * returns the entire order for a given order ID
	 * @param id order ID 
	 * @return order corresponding to the order ID
	 */ 
	public Order getOrderByID(int id){
		
		  for(int i=0;i<orders.size();i++)
			  
			  if(orders.get(i).getOrderID() == id)
				  
				  return orders.get(i);
		  
		  return null; //if not found
	  }
	
	/**
	 * returns the entire order for a given index in order array
	 * @param index order index in order array
	 * @return corresponding order at that index
	 */
	public Order getOrderByIndex(int index){
		
		  return orders.get(index);
		  
	}
	  
	/**
	 * creates a new order taking in inputs from user
	 */
	public void create(){
		//create order
		Scanner sc = new Scanner(System.in);
		int tableID = 0, staffID, numPax = 0;
		boolean testBool;
		
		do{
		System.out.print("Input your staffID : ");
		staffID = sc.nextInt();
		testBool = StaffManager.checkStaff(staffID);
			// if staff doesn't exist
			if(!testBool)
				System.out.println("Error : Input valid staffID.\n Enter -1 to cancel order creation.");
				if (staffID == -1){
					return;
				}
			// keep prompting until a valid staffID is entered
			}while(testBool == false);

		
		//condition for do-while loop
		int x = 0;
		
		//check if customer has a reservation previously
		do {
			System.out.print("Input reservation ID (enter -1 if there is no reservation):");
			int reservationId = sc.nextInt();
			if (reservationId != -1) {
				tableID  = ReservationManager.getTableIdByReservationId(reservationId);
				numPax = ReservationManager.getNumPaxByReservationId(reservationId);
				System.out.println("Table " + tableID + " and NumPax: " + numPax + " assigned from reservation");
				x = 0;
				//tableID return -1 means no table found
				if (tableID == -1) {
					System.out.println("No such reservation!");
					x = 1;
				}	
			}
			else if (reservationId == -1) {
				//ensure numPax is within 0 and 10
				do{
					System.out.print("Input no of people : ");
					numPax = sc.nextInt();
					if(numPax>10)
						System.out.println("Maximum 10 per table only");
					else if(numPax<=0)
						System.out.println("Please input a positive number");
				}while(numPax>10 || numPax<=0);
				
				tableID  = TableManager.findEmptyTable(numPax);
				if(tableID==-1)
					System.out.println("No tables available.");
				else{
				System.out.print("Table " + tableID + " assigned");
				x = 0;
				}
			}
		}while(x == 1);
		// user choice
		int choice; 
		// menu item ID choice
		int menuSelection; 
		// give an unused orderID
		int orderID = generateID();
		
		int itemSize = 0;
		int promoSize = 0;
		
		Order order = new Order(orderID,itemSize,promoSize,staffID,tableID,numPax);
		
		do{	
		System.out.println("\n1. Add Menu Item\n2. Add Promo Item \n3. Finish");
		choice = sc.nextInt();
		
		switch(choice){
			case 1: 
					System.out.print("Input Menu Item ID to add : ");
					menuSelection = sc.nextInt();
					
					// if menuitem is valid
					if(MenuManager.checkMenuItem(menuSelection)){
						// add to order
						order.addMenuItem(menuSelection);
						itemSize++;
						
					System.out.println(MenuManager.getMenuItemById(menuSelection).getName() +" added");
					
					}
					else 
						//if item doesn't exist
						System.out.println("Error : Input valid item ID");
					
					break;
					
			case 2: 
					System.out.print("Input Promo Set ID to add : ");
					menuSelection = sc.nextInt();
					
					// if promoitem is valid
					if(MenuManager.getSetIndexByID(menuSelection)!=-1){
						// add to order
						order.addMenuSet(menuSelection);
						promoSize++;
						
					System.out.println(MenuManager.getSetByID(menuSelection).getpackageName() +" added");
					}
					else 
						//if set doesn't exist
						System.out.println("Error : Input valid set ID");
					
					break;
					
			case 3: if(promoSize==0 && itemSize==0){ 
						// must add items
						System.out.println("Error : Order is empty. Please add items before finishing");
						// prompt to add items
						choice = 1; 
					}
			
					// if user added menuitem/promoitem
					else{ 
						order.setTotalPrice(calculateSum(order));
						// successfully created order
						orders.add(order);
						//table occupied only when order at least one item
						TableManager.setTableOccupied(tableID);
						System.out.println("Order creation successful");
						this.viewOrder(order);
						orderOverWrite();
						// add to count
						activeOrders++;
					}
					break;
					// if any other invalid choice
					default:System.out.println("Check input value");
					break;
					
			}
		} while(choice!=3);
		
	}

	/**
	 * adds items to an order
	 * @param orderID order ID of order to add items to
	 */
	public void add(int orderID){

		// find index of orderID
		int index = this.getIndexByID(orderID);
		if(index==-1){
			System.out.println("Error : Invalid order ID");
			return;
		}
		Order order = orders.get(index);
		
		int menuSelection;
		int choice;
		
		Scanner sc = new Scanner(System.in);
		
		// if printed invoice already
		if(!order.isActive())
			System.out.println("Error : Order has already completed");
		
		// if orderId does not exist
		else if(index==-1)
			System.out.println("Error : Invalid order ID");
		
		// if order is active and exists
		else{ 
			
			viewOrder(order);
			
			do{
			System.out.println("\n1. Add Menu Item\n2. Add Promo Set\n3. Finish");
			choice = sc.nextInt();
			
			switch(choice){
				case 1: 
					
						System.out.print("Input item ID to add : ");
						menuSelection  = sc.nextInt();
						
						// if menuitem exists
						if(MenuManager.checkMenuItem(menuSelection)){ 
							// add to order
							order.addMenuItem(menuSelection);
							System.out.println(MenuManager.getMenuItemById(menuSelection).getName() +" added");
						}
						
						// if menuitem ID invalid
						else
							System.out.println("Error : Input valid item ID");
							sc.nextLine();
							System.out.println("\nPress enter to continue");
							sc.nextLine();
							
						break;
						
				case 2: 
					
						System.out.print("Input promo ID to add : ");
						menuSelection  = sc.nextInt();
						
						// if promoitem exists
						if(MenuManager.getSetIndexByID(menuSelection)!=-1){ 
							// add to order
							order.addMenuSet(menuSelection);
							System.out.println(MenuManager.getSetByID(menuSelection).getpackageName() +" added");
						}
						
						// if promoitem ID invalid
						else 
							System.out.println("Error : Input valid set ID");
							sc.nextLine();
							System.out.println("\nPress enter to continue");
							sc.nextLine();
							
						break;
						
				case 3: 
					
					System.out.println("Order update successful");
					this.viewOrder(order);
					order.setTotalPrice(calculateSum(order));
					// remove old order
					orders.remove(index);
					// add new order
					orders.add(order);
					orderOverWrite();
					break;
						
						
				default:
					// invalid input
					System.out.println("Check input value");
					System.out.println("\nPress enter to continue");
					sc.nextLine();
					break;
			}
		}while(choice!=3);
			
		}
	}
	
	/**
	 * remove items from order
	 * @param orderID order ID of order to remove items from
	 */
	public void remove(int orderID){

		// find index of orderID
		int index = this.getIndexByID(orderID);
		if(index==-1){
			System.out.println("Error : Invalid order ID");
			return;
		}
		Order order = orders.get(index);
		
		int menuSelection;
		int choice;
		
		Scanner sc = new Scanner(System.in);
		
		// if printed invoice already
		if(!order.isActive())
			System.out.println("Error : Order has already completed");
		
		// if order exists and is active
		else{
			viewOrder(order);
			
			do{
				
			System.out.println("\n 1. Remove Menu Item \n 2. Remove Promo Set \n 3. Finish");
			choice = sc.nextInt();
			switch(choice){
			
				case 1: 
						System.out.print("Input item ID to remove : ");
						menuSelection  = sc.nextInt();
						
						// if menuitem exists
						if(order.checkItemByID(menuSelection)){ 
							// remove from order
							order.removeMenuItem(menuSelection);
							System.out.println(MenuManager.getMenuItemById(menuSelection).getName() +" removed");
						}
						else //if item doesnt exist
							System.out.println("Error : Input valid item ID");
							sc.nextLine();
							
							System.out.println("\nPress enter to continue");
							sc.nextLine();
							
						break;
						
				case 2: 
						System.out.print("Input promo ID to remove : ");
						menuSelection  = sc.nextInt();
						// if promoitem exists
						if(order.checkSetByID(menuSelection)){
							// remove from order
							order.removeMenuSet(menuSelection);
							System.out.println(MenuManager.getSetByID(menuSelection).getpackageName() +" removed");
						}
						
						// if promoitem ID invalid
						else 
							System.out.println("Error : Input valid set ID");
							sc.nextLine();
							
							System.out.println("\nPress enter to continue");
							sc.nextLine();
							
						break;
		
				case 3: 
						System.out.println("Order update successful");
						this.viewOrder(order);
						order.setTotalPrice(calculateSum(order));
						//remove old order
						orders.remove(index); 
						//add new order
						orders.add(order); 
						orderOverWrite();
						
						break;
						
						
				default:
					// input choice invalid
					System.out.println("Check input value");
					System.out.println("\nPress enter to continue");
					sc.nextLine();
					
					break;
			}
		}while(choice!=3);
		}
	}

	/**
	 * calculate the total nett price of an order
	 * @param order order to calculate price of
	 * @return total nett price in double
	 */
	public double calculateSum(Order order){
		
		double price = 0;
		
		//total price of menuitems
		for(int i=0;i<order.getItemSize();i++)
			price += MenuManager.getMenuItemById(order.getItemIdByIndex(i)).getPrice();
		
		//total price of promoitems
		for(int j=0;j<order.getSetSize();j++)
			price += MenuManager.getSetByID(order.getSetIdByIndex(j)).getpackagePrice();
		
		return price;
	}
	
	/**
	 * view order details
	 * @param order order to view details of
	 */
	public void viewOrder(Order order){
		
		//display order details
		System.out.println(order.getDate().toString());
		System.out.println("Order ID : " + order.getOrderID());
		System.out.println("Table ID : " + order.getTableID());
		System.out.println("Staff ID : " + order.getStaffID());
		
		System.out.println("\nMenu Items");
		System.out.format("%2s %-6s %30s    %9s","#", "ItemID", "Item Name", "Price");
		
		// for menu items
		for(int i=0;i<order.getItemSize();i++){
			
			System.out.format("%n%2d %6d %30s    %4s%5.2f",i+1,
					MenuManager.getMenuItemById(order.getItemIdByIndex(i)).getItemID(),
					MenuManager.getMenuItemById(order.getItemIdByIndex(i)).getName(),
					"SGD ",
					MenuManager.getMenuItemById(order.getItemIdByIndex(i)).getPrice());
			
		}
		
		System.out.println("\nPromo Sets");
		System.out.format("%2s %6s %30s    %9s","#", "SetID", "Set Name", "Price");
		
		// for promo items
		for(int j=0;j<order.getSetSize();j++){
			
			System.out.format("%n%2d %6d %30s    %4s%5.2f",j+1,
					MenuManager.getSetByID(order.getSetIdByIndex(j)).getPromoID(),
					MenuManager.getSetByID(order.getSetIdByIndex(j)).getpackageName(),
					"SGD ",
					MenuManager.getSetByID(order.getSetIdByIndex(j)).getpackagePrice());
			
		}
	}
	
	/**
	 * create invoice for a given order ID
	 * @param orderID order ID to create invoice of
	 */
	public void createInvoice(int orderID){
		//create Invoice
		int index = this.getIndexByID(orderID);
		
		// if orderID does not exist
		if(index==-1){
			System.out.println("Order does not exist.");
			return;
		}
		// if orderID exists
		Order order = orders.get(index);
		
		if(!order.isActive()){
			return;
		}
		
		viewOrder(order);
		
		double price = calculateSum(order);
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println();
		System.out.println("Member? (Enter 1 for yes and 0 for no) : ");
		
		int answer = sc.nextInt();
		// if customer is a member
		if(answer == 1){

			System.out.println("Total nett price : " + price);
			System.out.println("10% Service charge");
			System.out.println("7% GST");
			System.out.println("10% Member's discount");
			// total price
			System.out.println("Price after discount & service charge & GST : " + price*0.9*1.07*1.1);

		}
		// if customer is not a member
		else {
			
			System.out.println("Total nett price : " + price);
			System.out.println("10% Service charge");
			System.out.println("7% GST");
			// total price
			System.out.println("Price after service charge & GST: " + price*1.07*1.1);
			
		}
		
		//make table available after previous customer paid
		TableManager.setTableEmpty(order.getTableID());
		// order status no longer active
		order.closeOrder();
		order.setTotalPrice(price);
		orders.remove(index);
		orders.add(order);
		orderOverWrite();
		activeOrders--;
	}
	

}