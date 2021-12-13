/**
 * Customer Class
 */
public class Customer {

	private String name;
	private String contact;
	
	/**
	 * Constructor for Customer Class
	 * @param name name of customer
	 * @param contact contact number of customer
	 */
	public Customer(String name, String contact) {
		this.name = name;
		this.contact = contact;
	}
	
	/**
	 * Get name of customer
	 * @return name of customer
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Get contact number of customer
	 * @return contact number of customer
	 */
	public String getContact() {
		return this.contact;
	}
	
	/**
	 * @param name Set name of customer
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set contact number of customer
	 * @param contact contact number of customer
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}
}
