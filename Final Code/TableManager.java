import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/**
 * TableManager Class
 */
public class TableManager {
	
	private static TreeMap<Integer, Integer> capacityAndNumOfTables= new TreeMap<Integer, Integer>() {{
		// put(tableCapacity, tableCount);
		put(10, 2);		// 2 tables with capacity 10
		put(8,  5);		// 5 tables with capacity 8
		put(4,  10);	// 10 tables with capacity 4
		put(2,  10);	// 10 tables with capacity 10
	}};

	/**
	 * total number of tables in restuarant
	 */
	public static final int NUM_TABLES = getNumTables();

	private Table[] tables; // Initialize array of Table type

	/**
	 * Gets the number of tables
	 * @return number of tables
	 */
	private static final int getNumTables() {
		int numTables = 0;

		//for-each loop
       for (Map.Entry<Integer, Integer> mapElement : capacityAndNumOfTables.entrySet()) {
   
    	   // Finding the value
            int tableCount = (int)mapElement.getValue();
            numTables += tableCount;
        }
		return numTables;
	}
	
	/**
	 * Constructor for TableManager
	 */
	public TableManager() {
		// Create array to store tables
		int numTables = NUM_TABLES;
		this.tables = new Table[numTables]; 

		// Init table elements in the array...
		int tableId = 0; //1 for the first table

		for (Map.Entry<Integer, Integer> mapElement : capacityAndNumOfTables.entrySet()) {
            int capacity = (int)mapElement.getKey();
            int tableCount = (int)mapElement.getValue();
  
			// Init as many tables as needed for this given capacity
			for ( int i=0; i < tableCount; i++ ) {
				this.tables[tableId] = new Table(tableId+1, capacity);
				tableId++;
			}
        }
		
	}

	/**
	 * Locate a table using the tableId
	 * @param tableId tableId to check
	 * @return {@link Table} with the given tableId
	 */
	public Table getTableById(int tableId) { 
		return this.tables[tableId - 1];
	}
	
	/**
	 * Lists all tables
	 * @return Table[] containing all tables
	 */
	private Table[] getAllTables() { return this.tables; }

	/**
	 * Returns a list of tables with the same status
	 * @param status table status to check
	 * @return ArrayList<Table> of tables with the specified status
	 */
	private ArrayList<Table> getTablesByStatus(Table.Status status) {
		ArrayList<Table> tables = new ArrayList<Table>();
		for (Table t : this.getAllTables()) {
			if ( status == t.getStatus() )
				tables.add(t);
		}
		return tables;
	}
	
	/**
	 * Returns a list of occupied tables
	 * @return list of occupied tables
	 */
	public ArrayList<Table> getOccupiedTables() { 
		return this.getTablesByStatus(Table.Status.OCCUPIED);
	}

	/**
	 * Returns a list of reserved tables
	 * @return list of reserved tables
	 */
	public ArrayList<Table> getReservedTables() { 
		return this.getTablesByStatus(Table.Status.RESERVED);
	}
	
	/**
	 * Returns a list of empty tables
	 * @return list of empty tables
	 */
	public ArrayList<Table> getEmptyTables() {
		ArrayList<Table> empty = new ArrayList<Table>();

		for ( Table t : this.tables ) {
			Table.Status status = t.getStatus();

			if (status == Table.Status.EMPTY) 
				empty.add(t);
			
			else if (status == Table.Status.RESERVED) {
				if ( t.isReservationExpired() ) {
					t.setStatus(Table.Status.EMPTY);
					empty.add(t);
				}
			}
		}
		
		return empty; 	
	}

	/**
	 * Sets {@link Table} using tableId to the given {@link Table.Status} 
	 * @param tableId tableId to check
	 * @param status table status to check
	 */
	public void setTableStatus(int tableId, Table.Status status) {
		Table table = this.getTableById(tableId);
		table.setStatus(status);
	}
	
	/**
	 * Sets {@link Table} using the tableId to occupied status 
	 * @param tableId tableId to check
	 */
	public void setTableOccupied(int tableId) {
		setTableStatus(tableId, Table.Status.OCCUPIED);
	}
	
	/**
	 * Sets {@link Table} using the tableId to reserved status 
	 * @param tableId tableId to check
	 */
	public void setTableReserved(int tableId) {
		setTableStatus(tableId, Table.Status.RESERVED);
	}
	
	/**
	 * Sets {@link Table} using the tableId to empty status 
	 * @param tableId tableId to check
	 */
	public void setTableEmpty(int tableId) {
		setTableStatus(tableId, Table.Status.EMPTY);
	}
	
	/**
	 * Find ID of smallest table that can fit the given numOfPax
	 * @param numOfPax  Capacity of the {@link Table} found will be bigger than numOfPax
	 * @return tableId, or -1 if not found 
	 */
	public int findEmptyTable(int numOfPax) {
		// Return table id
		Table foundTable = null; 
		ArrayList<Table> emptyTables = this.getEmptyTables();
		for ( Table t : emptyTables ) {
			if ( t.getCapacity() >= numOfPax ) {
				foundTable = t;
				break;
			}
		}
		if (foundTable == null) {
			return -1;
		}
		else {
			return(foundTable.getTableID());
		}
	}

	/**
	 * Checks if the table has the given status
	 * @param tableId  tableId to check
	 * @param status table status to check
	 * @return boolean whether the table has the given status
	 */
	public boolean isTableStatus(int tableId, Table.Status status) {
		return this.getTableById(tableId).getStatus() == status;
	}
	
	/**
	 * Returns true if the table with the given tableId has status occupied.
	 * @param tableId  tableId to check
	 * @return  Whether the {@link Table} is occupied
	 */
	public boolean isTableOccupied(int tableId) {
		return this.isTableStatus(tableId, Table.Status.OCCUPIED);
	}
	
	/**
	 * Returns true if the table with the given tableId has status reserved.
	 * @param tableId  tableId to check
	 * @return  Whether the {@link Table} is reserved
	 */
	public boolean isTableReserved(int tableId) {
		return this.isTableStatus(tableId, Table.Status.RESERVED);
	}
	
	/**
	 * Returns true if the table with the given tableId has status empty.
	 * @param tableId  tableId to check
	 * @return  Whether the {@link Table} is empty
	 */
	public boolean isTableEmpty(int tableId) {
		return this.isTableStatus(tableId, Table.Status.EMPTY);
	}

}
