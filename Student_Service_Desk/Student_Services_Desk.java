package Student_Service_Desk;
// This program reads an input file of students and allows the user to
// lookup information about a student given the ID number.
// This is meant to loosely simulate what happens at the Student
// Services desk.
//
// In real life when a student scans their Student ID card
// the computer reads their Student ID Number from the bar code
// The computer then looks up the information about the student
// and allows the employee to look and and modify the information.
//


import java.util.*;

public class Student_Services_Desk {

    private static int nextSID = 22; // must be greater than any of the Students' IDs that we pre-load

    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);

        Map<Integer, Student> studentInfo = new TreeMap<Integer, Student>();
        studentInfo.put(21, new Student(21, "Zog", "TheDestroyer",
                new ArrayList<String>(List.of("BIT 143", "MATH 411", "ENG 120"))));
        studentInfo.put(20,
                new Student(20, "Mary", "Sue", new ArrayList<String>(List.of("BIT 142", "MATH 142", "ENG 100"))));
        studentInfo.put(1,
                new Student(1, "Joe", "Bloggs", new ArrayList<String>(List.of("BIT 115", "MATH 141", "ENG 101"))));

        char choice = 'L'; // anything but 'q' is fine
        while (choice != 'q') {
            System.out.println("\nWhat would you like to do next? ");
            System.out.println("F - Find a specific student");
            System.out.println("L - List all the students (for debugging purposes)");
            System.out.println("A - Add a new student");
            System.out.println("D - Delete an existing student");
            System.out.println("M - Modify an existing student");
            System.out.println("Q - Quit (exit) the program");
            System.out.print("What is your choice?\n(type in the letter & then the enter/return key) ");
            choice = keyboard.nextLine().trim().toLowerCase().charAt(0);
            System.out.println();

            switch (choice) {
            case 'f':
                System.out.println("Find a student (based on their ID number):\n");
                System.out.print("What is the ID number of the student to find? ");
                String user_input = keyboard.nextLine().trim().toLowerCase();
                Integer sid = Integer.valueOf(user_input);
                Student theStudent = studentInfo.get(sid);
                if(theStudent != null) {
                    System.out.printf("%s, %s (SID=%d)\nClasses:\n", sid, theStudent.getFirstName() + " " + theStudent.getLastName(), theStudent.getSID());
                    System.out.println("\t" + theStudent.getClasses().toString());
                } else {
                    System.out.println("Didn't find a student with ID # " + user_input);
                }
                break;

            case 'l':
                System.out.println("The following students are registered:");
                for(Student s: studentInfo.values()) {
                    System.out.println(s.toString());
                }

                break;
            case 'a':
                System.out.println("Adding a new student\n");
                System.out.println("Please provide the following information:");
                System.out.print("Student's first name? ");
                String first_name = keyboard.nextLine().trim();
                System.out.print("Student's last name? ");
                String last_name = keyboard.nextLine().trim();

                System.out.println("Type the name of class, or leave empty to stop.  Press enter/return regardless");
                String moreClasses = "y";
                ArrayList<String> enteredClasses = new ArrayList<>();
                while(moreClasses.equalsIgnoreCase("y")) {
               
               String class_name = keyboard.nextLine().trim();
               if(class_name != null && !class_name.equals("")) {
                enteredClasses.add(class_name);
               
               }
               System.out.println("Add more Classes y/n?");
               moreClasses = keyboard.nextLine().trim();
                }
                Student newStudent = new Student(nextSID, first_name, last_name, enteredClasses);
                studentInfo.put(nextSID, newStudent);
                nextSID++;
                break;
            case 'd':
                System.out.println("Deleting an existing student\n");
                System.out.print("What is the ID number of the student to delete? ");
                String user_tobe_deleted = keyboard.nextLine().trim().toLowerCase();
                Integer removeSid = Integer.valueOf(user_tobe_deleted);
                Student deleted = studentInfo.get(removeSid);
                if(deleted != null) {
                    studentInfo.remove(removeSid);
                    System.out.println("Student account found and removed from the system!");
                } else {
               
                    System.out.println("Didn't find a student with ID # " + user_tobe_deleted);
                }
                break;
            case 'm':
                System.out.println("Modifying an existing student\n");
                System.out.print("What is the ID number of the student to modify? ");
                String user_to_modify = keyboard.nextLine().trim().toLowerCase();
                Integer modifySid = Integer.valueOf(user_to_modify);
                Student modifyStudent = studentInfo.get(modifySid);
                if(modifyStudent != null) {
                    System.out.println(
                            "Student account found!\nFor each of the following type in the new info or leave empty to keep the existing info.  Press enter/return in both cases.");
                    System.out.print("Student's first name: " + "PLACEHOLDER" + " New first name? ");
                    String modified_fn = keyboard.nextLine().trim();
                    System.out.print("Student's last name: " + "PLACEHOLDER" + " New last name? ");
                    String modified_ln = keyboard.nextLine().trim();
                    System.out.println("Updating class list");
                    List<String> classes = modifyStudent.getClasses();
                    System.out.println("Here are the current classes: " + classes);
                    System.out.println("First, are there any classes you'd like to remove? (y/n)");
                    String shouldRemove = keyboard.nextLine().trim().toLowerCase();
                    List<String> newClasses = new ArrayList<>();
                    if(shouldRemove.startsWith("y")) {
                        for(String c: classes) {
                        System.out.println(c + "\nType d<enter/return> to remove, or just <enter/return> to keep ");
                        char decision = ' ';
                        try {
                            decision = keyboard.nextLine().trim().toLowerCase().charAt(0);
                        } catch(Exception e) {
                            //ignore exception ... consider it ''
                        }
                        if(decision == 'd') {
                            System.out.println("Removing " + c + "\n");
                           
                        } else  {
                            System.out.println("Keeping " + c + "\n");
                            newClasses.add(c);
                        }

                        }
                    System.out.println(
                            "Type the name of the class you'd like to add, or leave empty to stop.  Press enter/return regardless");
                            String classToAdd = keyboard.nextLine().trim();
                            newClasses.add(classToAdd);
                            modifyStudent.setFirstName(modified_fn);
                            modifyStudent.setLastName(modified_ln);
                            modifyStudent.setClasses(newClasses);
                    System.out.println("Here's the student's new, updated info: " + modifyStudent);
                }
            } else {
            System.out.println("Studen with ID " + user_to_modify +  " not Found.");
            }
                break;
            case 'q':
                System.out.println("Thanks for using the program - goodbye!\n");
                break;
            default:
                System.out.println("Sorry, but I didn't recognize the option " + choice);
                break;
            }

        }
    }
}