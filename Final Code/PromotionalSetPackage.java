import java.util.ArrayList;

/**
 * PromotionalSetPackage contains a selection of existing {@link MenuItem} and has an ID, name, price, description.
 */

public class PromotionalSetPackage {
	private int promoID;
	private String packageName;
	private double packagePrice =0;
	private String packageDescription;
	ArrayList<MenuItem> promotionMenuItem;

	/**
	 * Constructs and empty default promotionalSetPackage
	 */
	public PromotionalSetPackage() {
		this.packageName = "Empty";
		this.packageDescription = "Empty";
	}

	/**
	 * Constructor.
	 * Construct PromotionalSetPackage
	 * @param promoID Unique ID of the promotional set package
	 * @param packageName Unique name of promotional set package 
	 * @param packageDescription Description of promotional set package
	 * 
	 */
	public PromotionalSetPackage(int promoID, String packageName, String packageDescription) {
		this.promoID = promoID;
		this.packageName = packageName;
		this.packageDescription = packageDescription;
		
		promotionMenuItem = new ArrayList<MenuItem>();
		
	}
	
	/**
	 * Gets PromoID of {@link PromotionalSetPackage}
	 * @return promoID Unique non-negative promoID of an existing promotional Set Package
	 */
	public int getPromoID(){
		return promoID;
	}
	
	/**
	 * Gets name of {@link PromotionalSetPackage}
	 * @return packageName a unique string
	 */
	public String getpackageName() {
		return this.packageName;
	}
	
	/**
	 * Gets description of {@link PromotionalSetPackage}
	 * @return packageDescription package description is a string that needs not be unique
	 */
	public String getpackageDescription() {
		return this.packageDescription;
		
	}

	/**
	 * Gets price of {@link PromotionalSetPackage}
	 * @return packagePrice package price is a non-negative double that is typically less than the prices of the individual menu items combined
	 */
	public double getpackagePrice() {
		return this.packagePrice;
	}
	
	/**
	 * Sets name of {@link PromotionalSetPackage}
	 * @param packageName package name has to be unique
	 */
	public void setpackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * Sets price of {@link PromotionalSetPackage}
	 * @param packagePrice package price has to be a non-negative double
	 */
	public void setpackagePrice(double packagePrice) {
		this.packagePrice = packagePrice;
	}
	
	/**
	 * Sets price of {@link PromotionalSetPackage}
	 * @param packageDescription package description is a string that needs not be unique
	 */
	public void setpackageDescription(String packageDescription) {
		this.packageDescription = packageDescription;
	}
	
	/**
	 * Method for adding a {@link MenuItem} to a promotional Set Package
	 * @param menuItem Menu Item must exist
	 */
	public void addPromoItem(MenuItem menuItem) {
		
		for (int i = 0;i<promotionMenuItem.size(); i++) {
			if (promotionMenuItem.get(i) == menuItem) {
				System.out.println("Menu item already exists in this promotional menu. Not added.");
				return;
				
			}
		}
		promotionMenuItem.add(menuItem);
		packagePrice+=menuItem.getPrice();
		System.out.println("Menu item successfully added to promotional menu.");
	}
	
	/**
	 * Method for removing a {@link MenuItem} from a promotional Set Package
	 * @param menuItem Menu Item must exist in the promotional set package
	 */
	public void removePromoItem(MenuItem menuItem) {
		
		for (int i=0; i<promotionMenuItem.size(); i++) {
			if (promotionMenuItem.get(i) == menuItem) {
				promotionMenuItem.remove(i);
			}
		}
	
		packagePrice-= menuItem.getPrice();
		System.out.println("Menu item successfully removed from promotional package");
	}
	
	
	
	
}
