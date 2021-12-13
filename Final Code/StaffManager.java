import java.util.Scanner;

/** 
 * Represents the system managing Staff in the restaurant
 * The restaurant has a staff capacity of 100
*/

public class StaffManager{

    /**
     * The maximum number of staff allowed
    */
    static final int MAX_STAFF = 100;
    /**
     * The array of staff in the restaturant
    */
    Staff[] staffs;
    /**
     * The current number of staff employed
    */
    static int numEmployees = 0;

    /**
     * Creates an array of staff
     * Staff capacity should be 100 
    */
    public StaffManager(){
        staffs = new Staff[MAX_STAFF];
    }

    /**
     * Creates the default staff that are already hired and working in the restaurant.
     * This function is declared at the start of the MainUI so there are existing staff.
     * @param name      Default staff's name
     * @param gender    Default staff's gender
     * @param jobTitle  Default staff's job title
    */
    public void defaultStaff(String name,String gender,String jobTitle){
        int employeeId = numEmployees;
        staffs[employeeId] = new Staff(name,gender,jobTitle,employeeId);
        numEmployees++;
    }

    /**
     * Creates new staff by taking in inputs.
     * This function is used when new staff are to be created.
     * @param name      New staff's name
     * @param gender    New staff's gender
     * @param jobTitle  New staff's job title
    */
    public void createStaff(String name,String gender,String jobTitle){
        if (name == ""){
            System.out.println("\nUnable to create new staff.\nStaff's name cannot be empty.");
            return;
        }

        switch(gender){
            case "f":
            case "F":
                gender = "F";
                break;
            case "m":
            case "M":
                gender = "M";
                break;
            default:
                System.out.println("\nUnable to create new staff.\nStaff's gender entered is invalid.");
                return;
        }
    
        switch(jobTitle){
            case "chef":
            case "Chef":
                jobTitle = "Chef";
                break;
            case "waiter":
            case "Waiter":
                jobTitle = "Waiter";
            case "manager":
            case "Manager":
                jobTitle = "Manager";
            default:
                System.out.println("\nUnable to create new staff.\nStaff's job title entered is invalid.");
                return;
        }
        int employeeId = numEmployees;
        if (numEmployees == MAX_STAFF){
            System.out.println("Staff team is full.");
            return;
        }
        if (numEmployees > 0){
            for (int i = 0; i < numEmployees; i++){
                if (staffs[i].getStatus() == 0){
                    employeeId = i;
                    break;
                }
            }
        }
        staffs[employeeId] = new Staff(name,gender,jobTitle,employeeId);
        numEmployees++;
        System.out.println();
        System.out.printf("New staff created.\n",name,employeeId);
        staffs[employeeId].printDetails();
    }

    /**
     * Checks the validity of staff by taking in employeeId as input.
     * This function is used for reusability in other functions to simplify code
     * @param employeeId      Staff's employeeId for checking
     * @return                Returns true if staff exists and false when otherwise
    */
    public boolean checkStaff(int employeeId){
        if (employeeId <0 || employeeId >= numEmployees || staffs[employeeId].getStatus() == 0){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * Prints out the list of all hired staff
     * This function is used when new staff are to be created.
    */
    public void viewAllStaff(){
        Scanner sc = new Scanner(System.in);
        if (numEmployees == 0){
            System.out.println("No staff hired.");
            return;
        }

        int j = numEmployees;
        System.out.println("Staff details:");
        for (int i = 0;i < j; i++){
            if (staffs[i].getStatus() == 1){
                staffs[i].printDetails();
            }
            else{
                j++;
            }
        }
        return;
    }

    /**
     * Removes existing hired staff by taking in employeeId as input.
     * This function is used when current staff are to be fired.
     * @param employeeId      Staff to-be-fired's employeeId
    */
    public void removeStaff(int employeeId){
        Scanner sc = new Scanner(System.in);
        if (numEmployees == 0){
            System.out.println("No staff to remove.");
        }
        if (checkStaff(employeeId) == false){
            System.out.println("Invalid ID. Unable to remove.");
            return;
        }
        if (staffs[employeeId].getStatus() == 1){
            System.out.println("Staff to remove:");
            staffs[employeeId].printDetails();
        }
        System.out.println("Confirm remove staff?");
        System.out.println("Enter 'Y' to confirm. Else click any other key to cancel.");
        char choice = sc.next().charAt(0);
        if (choice == 'Y' || choice == 'y'){
            staffs[employeeId].fire();
            numEmployees--;
        }
        else{
            System.out.println("Removal aborted.");
            return;
        }
        System.out.printf("Staff (ID no: %d) removed.\n", employeeId);
    }

    /**
     * Updates existing hired staff details by taking in inputs.
     * This function is used when existing staff have details to be updated.
     * @param employeeId      Existing staff's employeeId
    */
    public void updateStaff(int employeeId){
        Scanner sc = new Scanner(System.in);
        if (numEmployees == 0){
            System.out.println("No staff hired currently.");
            return;
        }
            
        // ADD IN THIS
        if(checkStaff(employeeId) == false){
            System.out.println("Invalid ID. Unable to update.");
            return;
        }
        staffs[employeeId].printDetails();

        System.out.print(
            "Select update:\n"+
            "(1) Name\n"+
            "(2) Gender\n"+
            "(3) Job Title\n"+
            "(4) Go Back\n\n"+
            "Enter choice (1-4): "
        );
        int choice = sc.nextInt();
        while (choice <1 || choice > 4){
            System.out.println("Invalid choice. Try Again.\n");
            System.out.print(
                "Select update:\n"+
                "(1) Name\n"+
                "(2) Gender\n"+
                "(3) Job Title\n"+
                "(4) Go Back\n\n"+
                "Enter choice (1-4): "
            );
            choice = sc.nextInt();
        }

        switch(choice){
            case 1:
                System.out.print("Enter new name: ");
                String newName = sc.next();
                if (newName == ""){
                    System.out.println("\nUnable to update staff's name.\nStaff's name cannot be empty.");
                    return;
                }
                staffs[employeeId].setName(newName);
                break;
            case 2:
                System.out.print("Enter new gender (): ");
                String newGender = sc.next();
                switch(newGender){
                    case "f":
                    case "F":
                        newGender = "F";
                        break;
                    case "m":
                    case "M":
                        newGender = "M";
                        break;
                    default:
                        System.out.println("\nUnable to create new staff.\nStaff's gender entered is invalid.");
                        return;
                }
                staffs[employeeId].setGender(newGender);
                break;
            case 3:
                System.out.print("Enter new job title(Chef/Manager/Waiter): ");
                String newJobTitle = sc.next();

                switch(newJobTitle){
                    case "chef":
                    case "Chef":
                        newJobTitle = "Chef";
                        break;
                    case "waiter":
                    case "Waiter":
                        newJobTitle = "Waiter";
                    case "manager":
                    case "Manager":
                        newJobTitle = "Manager";
                    default:
                        System.out.println("\nUnable to create new staff.\nStaff's job title entered is invalid.");
                        return;
                }
                staffs[employeeId].setJobTitle(newJobTitle);
                break;
            case 4:
                break;
        }
        System.out.println("\nNew details:");
        staffs[employeeId].printDetails();
        return;
    }
}