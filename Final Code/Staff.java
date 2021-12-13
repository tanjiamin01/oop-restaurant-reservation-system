/** 
 * Represents Staff in the restaurant.
 * The staff is either hired or fired
*/

public class Staff {
    /**
     * The name of staff
    */
    private String name;
    /**
     * The gender of staff
     * Either female or male
    */
    private String gender;
    /**
     * The employee ID of staff
    */
    private int employeeId;
    /**
     * The job title of staff
    */
    private String jobTitle;
    /**
     * The hired status of staff
    */
    private String status;

    /**
     * Constructor for new staff
     * The default status of the staff is hired 
     * @param name          Staff's name
     * @param gender        Staff's gender
     * @param jobTitle      Staff's job title
     * @param employeeId    Staff's employee ID
     */
    public Staff(String name,String gender,String jobTitle, int employeeId){
        this.name = name;
        this.gender = gender;
        this.jobTitle = jobTitle;
        this.employeeId = employeeId;
        this.status = "hired";
    }

    /**
     * Sets new name for {@link Staff}
     * @param newName   Staff's new name
     */
    public void setName(String newName){
        this.name = newName;
    }

    /**
     * Sets new gender for {@link Staff}
     * @param newGender Staff's new gender
     */
    public void setGender(String newGender){
        this.gender = newGender;
    }

    /**
     * Sets new job title for {@link Staff}
     * @param newJobTitle   Staff's new job title
     */
    public void setJobTitle(String newJobTitle){
        this.jobTitle = newJobTitle;
    }

    /**
     * Fires staff by changing status of {@link Staff}
     */
    public void fire(){
        this.status = "fired";
    }

    /**
     * Returns the name of {@link Staff}
     * @return  Staff's name
     */
    public String getName(){
        return name;
    }

    /**
     * Returns the gender of {@link Staff}
     * @return  Staff's gender
     */
    public String getGender(){
        return gender;
    }

    /**
     * Returns the employeeId of {@link Staff}
     * @return  Staff's Employee ID
     */
    public int getEmployeeId(){
        return employeeId;
    }

    /**
     * Returns the job title of {@link Staff}
     * @return  Staff's job title
     */
    public String getJobTitle(){
        return jobTitle;
    }

    /**
     * Returns the employed status of {@link Staff}
     * @return  Staff's status
     */
    public int getStatus(){
        // returns 1 if hired and 0 if fired
        if (status == "fired"){
            return 0;
        }
        return 1;
    }

    /**
     * Prints the details of {@link Staff}
     * Details include: name, gender and jobtitle
     */
    public void printDetails(){
        System.out.printf("(ID no: %d)\n",employeeId);
        System.out.printf("Name: %s\nGender: %s\nJob Title: %s\n\n",name,gender,jobTitle);
    }
}
