import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Reservation Manager Class that manages all reservations in the restaurant
 */

public class ReservationManager {

	private int maxReservationNum;
	List<Reservation> reservation;
	TableManager tableManager;
	
	/**
	 * Constructor for ReservationManager
	 * @param tableManagerInput tableManager
	 */
	public ReservationManager(TableManager tableManagerInput) {
		reservation = new ArrayList<Reservation>();
		maxReservationNum = 0;
		tableManager = tableManagerInput;
	}
	
	/**
	 * Method to create reservation
	 * @param nameInput name
	 * @param contactInput contact
	 * @param dateInput date
	 * @param numPaxInput numPax
	 * @throws Exception exception handling
	 */
	public void createReservationBooking(String nameInput, String contactInput, String dateInput, int numPaxInput) throws Exception {
		if (Pattern.matches("[689].......", contactInput)==false) {
			System.out.printf("This phone number is invalid!\n");
		}
		else if (numPaxInput <= 0) {
			System.out.printf("Number of Pax must be a positive number!\n");
		}
		else {
		
		int reservationIdInput = maxReservationNum + 1;
		maxReservationNum = reservationIdInput;
		Reservation newReservation = new Reservation(reservationIdInput);
		try{newReservation.assignInformation(nameInput, contactInput, dateInput, numPaxInput);}
		catch (Exception e) {
			System.out.printf("Invalid format for date! Date format should be as follows: yyyy-MM-ddTHH:mm:ss\n");
			removeReservationBooking(reservationIdInput);
			return;
		}
		if (LocalDateTime.now().isAfter(LocalDateTime.parse(dateInput))) {
			System.out.printf("Reservation time must be in the future!");
			removeReservationBooking(reservationIdInput);
			return;
		}
		else {
			//allocating table to customer according to number of pax. 
			int tableId = tableManager.findEmptyTable(numPaxInput);
			if (tableId==-1) {
				System.out.printf("No more table available to fit %d number of customers", numPaxInput);
				removeReservationBooking(reservationIdInput);
				return;
			}
			else {
				newReservation.setTableId(tableId);
				tableManager.setTableReserved(tableId);
			}
			//add newReservation object into reservation list
			reservation.add(newReservation);
			tableManager.getTableById(tableId).setDate(LocalDateTime.parse(dateInput));
			tableManager.getTableById(tableId).setExpiryDate(LocalDateTime.parse(dateInput));
			System.out.printf("Your reservation ID is:" + reservationIdInput +"\n" + "Your table ID is:" + tableId +"\n");}
	}}
	
	/**
	 * prints reservation information using reservation ID
	 * @param reservationIdInput reservation ID
	 */
	public void checkReservationBooking(int reservationIdInput) {
		LocalDateTime dateInput = LocalDateTime.now();
		int numPaxInput = 0;
		String nameInput = "";
		String contactInput = "";
		int tableIdInput = 0;
		//search for reservation
		for(int i=0;i<reservation.size();i++) {
			if (reservation.get(i).getReservationId() == reservationIdInput) {
				dateInput = reservation.get(i).getDate();
				numPaxInput = reservation.get(i).getNumPax();
				nameInput = reservation.get(i).getName();
				contactInput = reservation.get(i).getContact();
				tableIdInput = reservation.get(i).getTableId();
			}
		}
		
		if (numPaxInput == 0) {
			System.out.printf("No such booking!\n");
		}
		
		else { 
		
		System.out.printf("Name: %s%n", nameInput);
		System.out.printf("Contact: %s%n", contactInput);
		System.out.printf("Reservation ID: %d%n", reservationIdInput);
		System.out.printf("Table ID: %d\n", tableIdInput);
		System.out.printf("Date: %s%n", dateInput);
		System.out.printf("Number of Pax: %d\n", numPaxInput);
		}
		
	}
	
	/**
	 * removes reservation using given reservation id
	 * @param reservationIdInput reservation ID
	 */
	public void removeReservationBooking(int reservationIdInput) {
		int counter = 0;
		
		for(int i=0;i<reservation.size();i++) {
			if (reservation.get(i).getReservationId() == reservationIdInput) {
					tableManager.setTableEmpty(reservation.get(i).getTableId());
					reservation.remove(i);
					counter ++;
					break;}
	}
		if (counter == 0) {
			System.out.printf("This reservation ID does not exist!\n");	}
		}
	
	/**
	 * Automatically removes reservation after EXPIRY_MINS = 30 mins
	 */
	public void cancelExpiredBooking() {
		for (int i=0; i<reservation.size();i++) {
			if (tableManager.getTableById(reservation.get(i).getTableId()).isReservationExpired()==true){
				tableManager.setTableEmpty(reservation.get(i).getTableId());
				removeReservationBooking(reservation.get(i).getReservationId());
				i--;
			}
				}
	}
	
	/**
	 * Gets Table ID by reservation ID
	 * Returns -1 if reservation not found
	 * @param reservationIdInput reservation ID
	 * @return reserved {@link Table} using reservation ID 
	 */
	public int getTableIdByReservationId(int reservationIdInput) {
		int tableId = 0;
		for(int i=0;i<reservation.size();i++) {
			if (reservation.get(i).getReservationId() == reservationIdInput) {
				tableId = reservation.get(i).getTableId();
				return tableId;
			}
		}
		return -1;
	}

	/**
	 * Gets number of people at table by reservation ID
	 * Returns -1 if reservation not found
	 * @param reservationIdInput reservation ID
	 * @return number of people in a particular reservation using reservation ID 
	 */
	public int getNumPaxByReservationId(int reservationIdInput) {
		int numPax = 0;
		for(int i=0;i<reservation.size();i++) {
			if (reservation.get(i).getReservationId() == reservationIdInput) {
				numPax = reservation.get(i).getNumPax();
				return numPax;
			}
		}
		return -1;
	}
}