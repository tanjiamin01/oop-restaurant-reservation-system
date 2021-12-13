import java.util.ArrayList;
import java.util.Date;

/**
 * Order class for the restaurant
 */
public class Order {
	
	private int orderID;
	private int tableID;
	private Date date;
	private int numPax;
	private int staffID;
	private boolean active; 
	private double totalPrice;
	private int [] menuItems;
	private int [] promoItems;
	//number of menuItems
	private int itemSize; 
	//number of promos
	private int promoSize;
	
	/**
	 * Constructor for Order
	 * @param orderID		Order's ID
	 * @param itemSize		Number of menu items in order
	 * @param promoSize		Number of promo sets in order
	 * @param staffID		Staff's ID	
	 * @param tableID		Customer's table for order
	 * @param numPax		Number of people at table
	 */
	public Order(int orderID, int itemSize, int promoSize, int staffID, int tableID, int numPax){
		this.orderID = orderID;
		this.staffID = staffID;
		this.tableID = tableID;
		this.itemSize = itemSize;
		this.promoSize = promoSize;
		date = new Date();
		this.numPax = numPax;
		active = true;
		this.menuItems = new int[50];
		this.promoItems = new int[15];
	}
	
	
	/**
	 * Constructor for order with additional details
	 * @param orderID		Order ID
	 * @param itemSize		Number of menu items in order
	 * @param promoSize		Number of promo sets in order
	 * @param staffID		Staff's ID
	 * @param tableID		Customer's table for order
	 * @param numPax		Number of people at the table
	 * @param date			Timestamp of order
	 * @param active		Status of order
	 * @param price			Total price of items in order
	 */
	//information to be read from order.txt file
	protected Order(int orderID, int itemSize, int promoSize, int staffID, int tableID, int numPax, Date date, boolean active, double price){
		this.orderID = orderID;
		this.staffID = staffID;
		this.tableID = tableID;
		this.itemSize = itemSize;
		this.promoSize = promoSize;
		this.date = date;
		this.numPax = numPax;
		this.active = active;
		this.totalPrice = price;
		this.menuItems = new int[50];
		this.promoItems = new int[15];
	}
	
	/**
	 * get number of people at table for {@link Order}
	 * @return number of people
	 */
	public int getNumPax(){
		
		return numPax;
		
	}
	
	/**
	 * get the total cost of {@link Order}
	 * @return the total price
	 */
	public double getTotalPrice(){
		
		return totalPrice;
		
	}
	
	/**
	 * set the total price of (@link Order)
	 * @param price	Price of order
	 */
	public void setTotalPrice(double price){
		
		this.totalPrice = price;
		
	}
	
	/**
	 * gets the staff in charge of {@link Order} by their ID
	 * @return staff ID
	 */
	public int getStaffID(){
		
		return staffID;
		
	}
	
	/**
	 * sets the staff in charge of {@link Order}
	 * @param staffID staff ID
	 */
	public void setStaffID(int staffID){
		
		this.staffID = staffID;
		
	}
	
	/**
	 * gets the table ID for {@link Order}
	 * @return table ID
	 */
	public int getTableID(){
		
		return tableID;
		
	}
	
	/**
	 * sets the table for the {@link Order}
	 * @param tableID table ID
	 */
	public void setTableID(int tableID){
		
		this.tableID = tableID;
		
	}
	
	
	/**
	 * gets the timestamp of {@link Order}
	 * @return date
	 */
	public Date getDate(){
		
		return date;
		
	}
	
	
	/**
	 * gets the orderID of {@link Order}
	 * @return orderID
	 */
	public int getOrderID(){
		
		return orderID;
		
	}
	
	/**
	 * gets the number of menu items in {@link Order}
	 * @return menu item size
	 */
	public int getItemSize(){
		
		return itemSize;
		
	}
	
	/**
	 * gets the number of promo sets in {@link Order}
	 * @return promo size
	 */
	public int getSetSize(){
		
		return promoSize;
		
	}
	
	
	/**
	 * get the index of item in menu by item ID
	 * @param itemID
	 * @return menu item index in order's menu item array
	 * return -1 if menu item is not in the order
	 */
	private int getItemIndexByID(int itemID){
		for(int i=0;i<itemSize;i++)
			
			if(menuItems[i] == itemID)
				
				return i;
		
		//if MenuItem not in order
		return -1;
	}
	
	
	/**
	 * checks if the item is in {@link Order} by item ID
	 * @param itemID
	 * @return true if in order, false if not in order
	 */
	public boolean checkItemByID(int itemID){
		
		for(int i=0;i<itemSize;i++)
			
			if(menuItems[i] == itemID)
				
				return true;
		
		//if MenuItem not in order
		return false;
	}
	
	
	/**
	 * gets all the MenuItems in {@link Order} in an array
	 * @return
	 */
	public ArrayList<Integer> getAllMenuItem(){
		
		ArrayList<Integer> menuItemsArray = new ArrayList<Integer>();
		for (int i=0; i<itemSize;i++) {
			menuItemsArray.add(menuItems[i]);
		}
		return menuItemsArray;
			
	}

	
	// get index of setID in array
	/**
	 * gets the index of setID in an array
	 * @param promoID promo set ID
	 * @return index of promo set in order's promo set array
	 * returns -1 if the promo set is not in the order
	 */
	private int getSetIndexByID(int promoID){
		
		for(int i=0;i<promoSize;i++)
			
			if(promoItems[i]==promoID)
				
				return i;
		
		//if promo does not exist in order
		return -1; 
	} 
	
	/**
	 * checks if the promotional set package is in {@link Order}
	 * @param promoID promo set ID
	 * @return true if promoset is in the order
	 * return false if not in the order
	 */
	public boolean checkSetByID(int promoID){
		
		for(int i=0;i<promoSize;i++)
			
			if(promoItems[i]==promoID)
				
				return true;
		// if promo does not exist in order
		return false;
	}
	
	/**
	 * gets all the promo set indexes in {@link Order} in an array
	 * @return promo set index array
	 */
	public ArrayList<Integer> getAllPromoItem(){
		
		ArrayList<Integer> promoItemsArray = new ArrayList<Integer>();
		for(int i=0;i<promoSize;i++){
			promoItemsArray.add(promoItems[i]);
		}
		
		return promoItemsArray;
	}
	
	/**
	 * gets the item ID of menu item in the menu item array of {@link Order}
	 * @param index item ID index in menu item array
	 * @return item ID
	 */
	public int getItemIdByIndex(int index){
		
		return menuItems[index];
		
	}
	 
	
	/**
	 * gets the promo set ID of promo set in the promo set array of {@link Order}
	 * @param index promo set index in menu item array
	 * @return promo set ID
	 */
	public int getSetIdByIndex(int index){
		return promoItems[index];
	}
	
	
	
	/**
	 * returns status of {@link Order}
	 * @return true if active
	 * return false if order is completed
	 */
	public boolean isActive(){
		
		return active;
		
	}
	
	/**
	 * closes the order when invoice is printed
	 * sets order's active status to false since {@link Order} is completed
	 */
	public void closeOrder(){
		
		active = false;
		
	}

	/**
	 * adds menu items to {@link Order} by the menu item ID
	 * @param menuItemID menu item ID in menu
	 */
	public void addMenuItem(int menuItemID){
		// if try to add more than 50 menu items
		if(itemSize>=50)
			System.out.println("Error: Cannot add more menu items to order. Maximum 50 menu items allowed.");
		
		else{
			//add MenuItem to array
			menuItems[itemSize] = menuItemID;
			itemSize++;
		}
	}
	
	/**
	 * removes menu items from {@link Order} by item ID
	 * @param ItemID menu item ID in menu
	 */
	public void removeMenuItem(int ItemID){
		
		int index = getItemIndexByID(ItemID);
		
		if(index==-1)
			//if MenuItem is not ordered
			System.out.println("Item does not exist in order.");
		
		else{
			for(int i = index;i<itemSize-1;i++)
				//let the menuitem in the next index take its place
				menuItems[i] = menuItems[i+1];
			// reduce count by 1
			itemSize--;
		}
	}
	
	/**
	 * adds promo set to {@link Order} by set ID
	 * @param menuSetID menu set's ID in menu
	 */
	public void addMenuSet(int menuSetID){
		if(promoSize>=15)
			
			System.out.println("Error : Cannot add more promotional set packages to order. Maximum 15 promotional set packages allowed.");
		
		else{
			
			promoItems[promoSize] = menuSetID;
			promoSize++;
		}
	}
	
	/**
	 * removes promo set from {@link Order}
	 * @param setID promo set's ID in menu
	 */
	public void removeMenuSet(int setID){
		int index = getSetIndexByID(setID);
		
		if(index==-1)
			
			System.out.println("No such set exists in order");
		
		else{
			for(int i = index ;i<promoSize-1; i++)
				//let the promoitem in the next index take its place
				promoItems[i] = promoItems[i+1];
			//remove count by 1
			promoSize--;
		}
	}
	
}