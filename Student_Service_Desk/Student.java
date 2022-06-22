package Student_Service_Desk;
import java.util.*;

public class Student implements Comparable {

    public static final int NOBODY = -1;

    private int SID;
    private String firstName;
    private String lastName;
    private List<String> classes;
    // for now we are NOT going to list classes
    // from previous quarters

    public Student(int SID, String firstName, String lastName, List<String> curClasses) {
        this.SID = SID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.classes = curClasses;
    }

    public int compareTo(Object other) {
        boolean typeOrSubType = other instanceof Student;
        if(!typeOrSubType) {
            return 0; //Cannot compare unlike objects.
            //it should return illegal argument exception but it is defined here in method.. so I am returning 0.
        }
        Student temp = (Student) other;
        int comparedVal = this.getLastName().compareTo(temp.getLastName());
        if(comparedVal == 0) { //first name is same
            comparedVal = this.getFirstName().compareTo(temp.getFirstName());
            if(comparedVal == 0) { //lastname is same
                if(this.getSID() < temp.getSID()) {
                    return -1;
                } else {
                    return 1;
                }

            } 
        }
        return comparedVal; 
    }

    public int getSID() {
        return SID;
    }

    public void setSID(int SID) {
        this.SID = SID;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<String> getClasses() {
        return this.classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "{" + " SID='" + getSID() + "'" + ", firstName='" + getFirstName() + "'" + ", lastName='" + getLastName()
                + "'" + ", classes='" + getClasses() + "'" + "}";
    }
}