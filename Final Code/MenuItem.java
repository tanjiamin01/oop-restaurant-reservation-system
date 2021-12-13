enum FOODTYPE{ 
	/**
	 * Enum providing possible {@link MenuItem} food types
	 * <li>{@link #MAIN}</li>
	 * <li>{@link #DESSERT}</li>
	 * <li>{@link #DRINK}</li>
	 */	
	MAIN, DESSERT,DRINK,
}

/**
 * MenuItem is a main, dessert or drink served at Sushi Palace. Each menu item has a unique name, a description, a price and a food type.
*/
public class MenuItem {
	private int itemID;
	private String name;
	private double price;
	private String description;
	FOODTYPE foodtype;
	
	/**
	 * Constructor.
	 * Construct MenuItem
	 * @param itemID Unique ID of the menu item
	 * @param name Unique name of the menu item
	 * @param price Non-negative Price of menu item
	 * @param description Description of menu item
	 * 
	 */
	public MenuItem(int itemID, String name, double price, String description) { //FOODTYPE foodtype){
		this.itemID = itemID;
		this.name = name;
		this.price = price;
		this.description = description;
	}
	
	/**
	 * Gets ItemID of menu item
	 * @returns itemID menu item must exist
	 */
	public int getItemID(){
		return itemID;
	}

	/**
	 * Constructs an empty default {@link MenuItem}
	 */
	public MenuItem() { 
		this.name = "Empty";
		this.price = 0;
		this.description = "Empty";
		
	}
	
	/**
	 * Gets name of {@link MenuItem}
	 * @return name name of menu item is unique
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Gets price of {@link MenuItem}
	 * @return price price is a non-negative double
	 */
	public double getPrice() {
		return this.price;
	}
	
	/**
	 * Gets description of {@link MenuItem}
	 * @return description description is a string that need not be unique
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Gets food type of {@link MenuItem}
	 * @return foodtype foodtype can a MAIN, DESSERT or DRINK
	 */
	public FOODTYPE getFoodType() {
		return this.foodtype;
	}
	
	/**
	 * Get {@link FOODTYPE} using an integer input
	 * @return 1 if menu item is a main, 2 if menu item is a dessert, 3 if menu item is a drink
	 */
	public int getFoodTypeInt() {
		if (this.foodtype == foodtype.MAIN) {
			return 1;
		}
		if (this.foodtype == foodtype.DESSERT) {
			return 2;
		}
		if (this.foodtype == foodtype.DRINK) {
			return 3;
		}
		return 100;
		
	}
	
	/**
	 * Set name of {@link MenuItem}
	 * @return name name is a string and must be unique
	 */
	public void setName(String name) {
		this.name = name;
		return;
	}
	
	/**
	 * Set pame of {@link MenuItem}
	 * @return price price is a non-negative double
	 */
	public void setPrice(double price) {
		this.price = price;
		return;
	}
	
	/**
	 * Set description of {@link MenuItem}
	 * @return description description is a string and need not be unique
	 */
	public void setDescription(String description) {
		this.description = description;
		return;
	}
	
	/**
	 * Set {@link FOODTYPE} using an integer input
	 * @param a Integer corresponding to food type. 1 represents main, 2 represents dessert, 3 represents drink
	 */
	public void setFoodType(int a) {
		if (a==1) {
			this.foodtype =  foodtype.MAIN;
		}
		if (a==2) {
			this.foodtype =  foodtype.DESSERT;
		}
		if (a==3) {
			this.foodtype =  foodtype.DRINK;
		}
	
		return;
	}
	
	
	
	
}
