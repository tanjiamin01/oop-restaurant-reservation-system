import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class Table {

	public enum Status {
		EMPTY,
		OCCUPIED,
		RESERVED
	}
	
	private int tableID;
	private int capacity;
	private Status status;
	private LocalDateTime date;
	private LocalDateTime expiryDate;
	public static final int EXPIRY_MINS = 30;

	public Table(int id, int capacity) {
		this.tableID = id;
		this.capacity = capacity;
		this.status = Status.EMPTY;
	}

	public int getTableID() {
		return (this.tableID);
	}

	public void setTableID(int ID) {
		this.tableID = ID;
	}

	public int getCapacity() {
		return this.capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public Status getStatus() {
		return this.status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDateTime getDate() { return this.date; }
	
	public void setDate(LocalDateTime date) { this.date = date; } 
	
	public LocalDateTime getExpiryDate() { return this.expiryDate; }
	
	public void setExpiryDate(LocalDateTime date) { this.expiryDate = date.plusMinutes(EXPIRY_MINS); } 
	
	
	public boolean isReservationExpired() {
		LocalDateTime exp = this.getDate();

		if (exp == null)
			return true;

		return LocalDateTime.now().isAfter(this.getDate().plusMinutes(EXPIRY_MINS));
	}
	
	public String getDateFormatted(LocalDateTime date){
		DateTimeFormatter dateFmt = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm");
		return (date != null)?dateFmt.format(date):"null";
	}
	public String getDateString() { return this.getDateFormatted(this.getDate());}
	public String getExpiryDateString() {
		if (this.getDate()!=null){
			return this.getDateFormatted(this.getDate().plusMinutes(EXPIRY_MINS));
		}
		return null;
	}

	@Override
	public String toString() {
		return String.format(
				"Table[id = %d, status = %s, capacity = %d, reservation date = %s, expiry date = %s]",
				this.getTableID(),
				this.getStatus(),
				this.getCapacity(),
				this.getDateString(),
				this.getExpiryDateString());
	}
	
}