import java.text.ParseException;
import java.time.LocalDateTime;

/**
 * Reservation Class
 */
public class Reservation {
	private LocalDateTime date;
	private int numPax;
	private int reservationId;
	private String name;
	private String contact;
	private int tableId;

	/**
	 * Constructor for Reservation
	 * @param reservationIdInput reservation ID
	 */
	public Reservation(int reservationIdInput){
		name = "Unknown";
		contact = "Unknown";
		date = LocalDateTime.now();
		numPax = 0;
		reservationId = reservationIdInput;
		tableId = 0;
	}
	
	/**
	 * Assigns information to reservation
	 * @param nameInput name
	 * @param contactInput contact
	 * @param dateInput date
	 * @param numPaxInput numPax
	 * @throws ParseException exception handling
	 */
	public void assignInformation(String nameInput, String contactInput, String dateInput, int numPaxInput) throws ParseException {
		LocalDateTime dateInputT= LocalDateTime.parse(dateInput);
		name = nameInput;
		contact = contactInput;
		date = dateInputT;
		numPax = numPaxInput;
	}
	
	/**
	 * set reservation table ID 
	 * @param tableIdInput table ID
	 */
	public void setTableId(int tableIdInput) {
		tableId = tableIdInput;
	}
	
	/**
	 * get reserved table ID
	 * @return table ID
	 */
	public int getTableId() {
		return tableId;
	}
	
	/**
	 * get date of reservation 
	 * @return reservation date
	 */
	public LocalDateTime getDate() {
		return date;
	}
	
	/**
	 * get number of people in reservation
	 * @return number of people in reservation booking
	 */
	public int getNumPax() {
		return numPax;
	}
	
	/**
	 * get reservation ID 
	 * @return reservation ID
	 */
	public int getReservationId() {
		return reservationId;
	}
	
	/**
	 * get customer name from reservation
	 * @return customer name 
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * get contact number of reservation
	 * @return contact number of customer
	 */
	public String getContact() {
		return contact;
	}
}