import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * MenuManager allows users to create,remove and update {@link MenuItem} as well as {@link PromotionalSetPackage}
 */

public class MenuManager {
	
	ArrayList<MenuItem> menuItems;
	ArrayList<PromotionalSetPackage> promos;
	
	
	Scanner in = new Scanner(System.in);

	/**
	 * Constructor for MenuManager
	 */
	public MenuManager(){
		this.menuItems = new ArrayList<MenuItem>();
		this.promos = new ArrayList<PromotionalSetPackage>();
	
	}

	/**
	 * Returns all {@link MenuItem}
	 * @return menuItems an ArrayList of all existing menuItems
	 */
	public ArrayList<MenuItem> getMenuItems() { return menuItems; }
	
	/**
	 * Checks if {@link MenuItem} of a certain ID exists in the menu
	 * @param id ID of menu item to check
	 * @return true if the menu item exists, false if the menu item does not exist
	 * 
	 */
	public boolean checkMenuItem(int id){
		  for(int i=0;i<menuItems.size();i++)
			  if(menuItems.get(i).getItemID() == id)
				  return true;
		  return false;
	  }
	
	/**
	 * Gets index of {@link MenuItem} using its ID
	 * @param id ID of menu item
	 * @return index i if the menu item exists, -1 if the menu item of that ID does not exist
	 * 
	 */
	public int getMenuItemIndexByID(int id){
		for(int i=0;i<menuItems.size();i++)
			if(menuItems.get(i).getItemID() == id)
				return i;
		return -1; 
	  }
	
	/**
	 * Gets {@link MenuItem} using its ID
	 * @param id ID of menu item
	 * @return Menu item of that ID if it exists, an empty MenuItem if it does not exist
	 * 
	 */
	public MenuItem getMenuItemById(int id){
		int index = getMenuItemIndexByID(id);
		if(index!=-1)
			return menuItems.get(index);
		else
			return new MenuItem();
	}
	
	
	/**
	 * Gets index of {@link PromotionalSetPackage} using its ID
	 * @param promoID Unique ID of promotional set menu
	 * @return index if it exists, -1 if it does not exist
	 * 
	 */
	public int getSetIndexByID(int promoID){
		for(int i=0;i<promos.size();i++)
			if(promos.get(i).getPromoID() == promoID)
				return i;
		System.out.println("No such set exists");
		return -1; 
	}
		
	/**
	 * Gets {@link PromotionalSetPackage} using its ID
	 * @param promoID Unique ID of promotional set menu
	 * @return PromotionalSetPackage if it exists, an empty PromotionalSetPackage if it does not exist
	 * 
	 */
	public PromotionalSetPackage getSetByID(int promoID){
		int index = getSetIndexByID(promoID);
		if(index!=-1)
			return promos.get(index);
		else
			return new PromotionalSetPackage();
	}
	
	/**
	 * Creates a {@link MenuItem} using user input
	 * @param itemID Unique ID of new menu item
	 * @param name Unique name of new menu item 
	 * @param price Non-negative price of new menu item
	 * @param description description of new menu item
	 * @param foodtypeint 1 if food type is MAIN, 2 if food type is DESSERT, and 3 if foodtype is DRINK
	 */
	public void createMenuItem(int itemID, String name, double price, String description, int foodtypeint) {
		
		MenuItem menuItem1 = new MenuItem(itemID, name,price,description);
		menuItem1.setFoodType(foodtypeint);
			
		for (int i = 0;i<menuItems.size();i++) {
			if (menuItems.get(i).getName().equals(name)) { // if name of item already on the menu, do not create
				System.out.println("Menu item of that name already exists, not added to menu.");
				return;
			}
		}
		menuItems.add(menuItem1);
		
		System.out.println("Menu item " + name +" successfully created.");
		return;
	}
	
	/**
	 * Removes a {@link MenuItem} using its name
	 * @param name Name of existing menu item to be removed
	 */
	public void removeMenuItem(String name) {
	
		for (int i = 0;i<menuItems.size();i++) {

			
			if (menuItems.get(i).getName().equals(name)) {
				
				menuItems.remove(i);
				System.out.println("Menu item " + name +" successfully removed.");
				return;
			}
		}
		
		System.out.println("Menu item does not exist.");
		return;
		
	}
	
	/**
	 * Updates the name, price, description and food type of a {@link MenuItem} using its name
	 * @param name Name of existing menu item to be removed
	 */
	public void updateMenuItemName(String name) {
		int a=0;
		
		for (int i = 0;i<menuItems.size();i++) {
			if (menuItems.get(i).getName().equals(name)) {
				
				while (a<1 || a>4) {
					try {
						System.out.println("Which would you like to update? (1) Name, (2) Price, (3) Description, (4) Food Type");
						a = in.nextInt();
					}
					catch (InputMismatchException ex) {
						System.out.println("Invalid input. Please input an integer from 1-4.");
						in.nextLine();
					}
				}
				
				
				
				switch(a) {
				case 1: 
					System.out.print("Enter new name: ");
					String newName = in.nextLine();
					newName = in.nextLine();
					for (int j = 0;j<menuItems.size();j++) {
						if (menuItems.get(j).getName().equals(newName)) {
							System.out.println("Name cannot be updated to an existing name");
							return;
						}
					}
					
			
					menuItems.get(i).setName(newName);
					System.out.println("Name successfully updated.");
					return;
						
					
		
				case 2: 
					int isNumber=0; 
					double newPrice = -1;
					while(isNumber == 0){
    					try {
    						System.out.print("Enter new price: ");
    						newPrice = in.nextDouble();
            				if (newPrice<0) {
            					System.out.println("New price cannot be negative.");
            				}
            				else {
            					isNumber = 1;
                				break;
            				}
            				
        				}
        				catch (InputMismatchException ex) {
        					System.out.println("Invalid input. Price should be a double.");
        					in.nextLine();
        					
        				}
    					
    				}
					
					menuItems.get(i).setPrice(newPrice);
					System.out.println("Price successfully updated.");
					return;
				
				case 3: 
					System.out.print("Enter new description: ");
					String newDescription = in.nextLine();
					newDescription = in.nextLine();
					menuItems.get(i).setDescription(newDescription);
					System.out.println("Description successfully updated.");
					return;
					
				case 4: 
					System.out.println("Which food type would you like to update " + name + " to? (1) MAIN (2) DESSERT (3) DRINK");
					int b = in.nextInt();
					menuItems.get(i).setFoodType(b);
					System.out.println("Food type successfully updated.");
					return;
					
				}
			}
		}
		System.out.println("Menu item with this name does not exist");
		return;
	}
	
	/**
	 * Creates a new {@link PromotionalSetPackage}
	 * @param promoID Unique ID of new promotional set package
	 * @param name Unique name of new promotional set package
	 * @param description Description of new promotional set package
	 */
	public void createPromotion(int promoID, String name, String description) {
		
		PromotionalSetPackage promo1 = new PromotionalSetPackage(promoID, name, description);
		for (int i = 0;i<promos.size();i++) {
			if (promos.get(i).getpackageName().equals(name)) { // if name of item already on the menu, do not create
				System.out.println("Promotional set package of that name already exists, not created.");
				return;
			}
		}
		promos.add(promo1);
		
		System.out.println("Promotional set package created successfully");
		return;
	}
	
	/**
	 * Removes an existing {@link PromotionalSetPackage} using its name
	 * @param name Name of existing promotional set package
	 */
   	public void removePromotion(String name) {
		
	   for (int i = 0;i<promos.size();i++) {

			
			if (promos.get(i).getpackageName().equals(name)) {
				
				
					
				promos.remove(i);
				System.out.println("Promotional set package " + name +" successfully removed.");
				return;
			}
		}
		
		System.out.println("Promotional set package does not exist. ");
		return;
	}
   
    /**
	 * Updates the name, description and menu items of a {@link PromotionalSetPackage} using its name
	 * @param name Name of existing promotional set package to be updated
	 */
    public void updatePromotion(String name) {
	   
	   for (int i =0;i<promos.size();i++) {
		   if (promos.get(i).getpackageName().equals(name)) {
			   int isNumber=0;
			   int a =0;
			   while (isNumber == 0) {
               		try {
               			System.out.println("Would you like to:\n(1) add menu item to promotional package \n(2) remove menu item from promotional package\n(3) change name of promotional package\n(4) change description of promotional package");
               			a = in.nextInt();
               			if (a<1 || a>4) {
               				System.out.println("Invalid input. Please enter a valid integer 1-4.");
               			}
               			else {
               				isNumber = 1; 
                   			break;
               			}
                   }
                   catch(InputMismatchException ex){
                   	System.out.println("Invalid input. Please enter a valid integer 1-4.");
                   	in.nextLine();
                   }
               }
			  
			   
			   switch(a) {
			   case 1: 
				   System.out.print("Name of menu item you wish to add: ");
				   String menuitemname = in.nextLine();
				   menuitemname = in.nextLine();
				   for (int k =0;k<menuItems.size();k++) {
					   if (menuitemname.equals(menuItems.get(k).getName())) {
						   
						   promos.get(i).addPromoItem(menuItems.get(k));
						   return;
					   }
				   }
				   System.out.println("Menu item with this name does not exist.");
					return;
			   case 2: 
				   System.out.print("Name of menu item you wish to remove: ");
				   menuitemname = in.nextLine();
				   menuitemname = in.nextLine();
				   for (int k =0;k<menuItems.size();k++) {
					   if (menuitemname.equals(menuItems.get(k).getName())) {
						   
						   promos.get(i).removePromoItem(menuItems.get(k));
						   return;
					   }
				   }
				   System.out.println("Menu item with this name does not exist.");
					return; 
			   case 3: 
				   System.out.print("Enter new name: ");
					String newName = in.nextLine();
					newName = in.nextLine();
					for (int j = 0;j<=promos.size();j++) {
						if (promos.get(j).getpackageName().equals(newName)) {
							System.out.println("Name cannot be updated to an existing promotional set package name.");
							return;
						}
					}
					promos.get(i).setpackageName(newName);
					System.out.println("Name successfully updated.");
					return;
			   case 4: 
				    System.out.println("Enter new description: ");
					String newDescription = in.nextLine();
					newDescription = in.nextLine();
					promos.get(i).setpackageDescription(newDescription);
					System.out.println("Description successfully updated.");
					return;
				   
				   
					
			   }
		   }
	   }
	   System.out.println("Promotional set package with this name does not exist.");
	   return;		
	}
}
