import java.text.ParseException;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Report Manager Class that mainly prints the report for the restaurant
 */

public class ReportManager {
	private OrderManager orders;
	private MenuManager MenuManager;
	private double revenue;
	
	/**
	 * Constructor for ReportManager
	 * @param orders {@link Order}
	 * @param MenuManager [@link MenuManager}
	 */
	public ReportManager(OrderManager orders, MenuManager MenuManager) {
		this.orders = orders;
		Date initialDate = new Date();
		Date finalDate = new Date();
		this.MenuManager = MenuManager; 
		}
	
	
	/**
	 * Prints report showing list of total {@link Order}
	 */
	public void printReport(){
		ArrayList<Order> totalOrders = new ArrayList<Order>(); 
		revenue = 0.0;
		double setrevenue = 0.0;
        int count;
        int index = 0;
        int index1 = 0;
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        		
        System.out.println("Enter initial date with dd/mm/yyyy: ");
        String initial_date = sc.next();
        Date initialDate = null;
        
        try {
           initialDate = sdf.parse(initial_date); 
        } catch (ParseException e) { 
			System.out.println("Invalid date entered.");
			return;
        }
        
        
        System.out.println("Enter final date with dd/mm/yyyy: ");
        String final_date = sc.next();
        Date finalDate = null;
        
        try {
           finalDate = sdf.parse(final_date); 
        } catch (ParseException e) { 
			System.out.println("Invalid date entered.");
			return;
        }
        
        
        for (int i=0;i<orders.getSize();i++) {
        	// check if the date that order was created is between the initial and final dates
			if(sdf.format(initialDate).compareTo(sdf.format(orders.getOrderByIndex(i).getDate())) <= 0
				&& sdf.format(finalDate).compareTo(sdf.format(orders.getOrderByIndex(i).getDate())) >= 0) {
		
					// if it is, add them to a new array of orders
					totalOrders.add(orders.getOrderByIndex(i));
            }
        }
        

        if(totalOrders.size() == 0){
            System.out.println("There are no orders on this period");
		}
        else {
		
        	
			// Print headers
			System.out.printf("%-32s %s  %s  %s\n", "Item", "Qty", "Unit price", "Item total");
			System.out.printf("%-32s %s  %s  %s\n", "----", "---", "----------", "----------");
			
			ArrayList<Integer> arr = new ArrayList<Integer>();
			ArrayList<Integer> setarr = new ArrayList<Integer>();
				
			// for each order within the time period
			for(int i=0;i<totalOrders.size();i++){
				arr.clear();// added this
				setarr.clear(); 
					
				// array arr will store each MenuItem ID in every order
				for (int i2=0; i2<totalOrders.get(i).getAllMenuItem().size();i2++) {
					arr.add(totalOrders.get(i).getAllMenuItem().get(i2));
				} 
					
				//same for package
				for (int i2=0; i2<totalOrders.get(i).getAllPromoItem().size();i2++) {
					setarr.add(totalOrders.get(i).getAllPromoItem().get(i2));
				} 
				
				//array freq will store frequency of itemID appearing while array arr will store itemID  
				int [] freq = new int[arr.size()];  
				int [] setfreq = new int [setarr.size()];
				int visited = -1;  
				int setvisited = -1;
				
				for (int i1=0; i1<arr.size(); i1++) { 
					count = 1;  
					for (int j=i1+1; j<arr.size(); j++) {
						if(arr.get(i1) == arr.get(j)) { 
							count = count + 1;  
							// to avoid counting same element again  
							freq[j] = visited;  
						}
					}		
					if(freq[i1] != visited)
						freq[i1] = count;  
				}
			
				int setcount;
				//same for package
				for (int i1=0; i1<setarr.size(); i1++) { 
					setcount = 1;  
					for (int j=i1+1; j<setarr.size(); j++) {
						if(setarr.get(i1) == setarr.get(j)) { 
							setcount = setcount + 1;   
							setfreq[j] = setvisited;  
						}
					}
							
					if(setfreq[i1] != setvisited)
						setfreq[i1] = setcount;  
				}
 
				for (int i1=0; i1<arr.size(); i1++) {
					int itemCount = freq[i1];
					if(( MenuManager.getMenuItemById(arr.get(i1)).getName() != "Empty") && itemCount!=-1 ) {
						double price = MenuManager.getMenuItemById(arr.get(i1)).getPrice();
						revenue += itemCount * price;
						System.out.printf("%-32s '%d'     %.2f        %.2f", MenuManager.getMenuItemById(arr.get(i1)).getName(), itemCount, price, itemCount * price);
						System.out.println();
		
					}
				}
			
			
				for (int i1=0; i1<setarr.size(); i1++) {
					int itemCount = setfreq[i1];
					if(( MenuManager.getSetByID(setarr.get(i1)).getpackageName() != "Empty") && itemCount!=-1 ) {
						double price = MenuManager.getSetByID(setarr.get(i1)).getpackagePrice();
						setrevenue += itemCount * price;
						System.out.printf("%-32s '%d'     %.2f        %.2f", MenuManager.getSetByID(setarr.get(i1)).getpackageName(), itemCount, price, itemCount * price);
						System.out.println();
					}
				}
			}
			// print total revenue to 2dp
			// System.out.printf("%-32s '%9.2f'%n", "", "", "Total Revenue from individual orders: ", revenue);
			System.out.printf("Total Revenue from individual orders: %.2f \n",revenue);
			System.out.printf("Total Revenue from set orders: %.2f \n", setrevenue);
        
        }
	}
}


