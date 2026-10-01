import java.util.ArrayList;
import java.util.Scanner;

public class StudentManager {

    private ArrayList<Student> students;

    public StudentManager() {
        students = new ArrayList<>();
    }

    public void addStudent(String name, int age, String grade) {
        Student newStudent = new Student(name, age, grade);
        students.add(newStudent);
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("The list is empty.");
            return; 
        }
        for (Student student : students) {
            System.out.println(student.getDetails());
        }
    }

    public void searchStudent(String name) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                System.out.println("Found! " + student.getDetails());
                return;
            }
        }
        System.out.println(name + " was not found.");
    }

    public void updateStudent(String oldName, String newName, int newAge, String newGrade) {
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(oldName)) {
                student.setName(newName);
                student.setAge(newAge);
                student.setGrade(newGrade);
                System.out.println("Student record updated successfully!");
                return;
            }
        }
        System.out.println("Could not update. " + oldName + " was not found.");
    }

    public void deleteStudent(String name) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getName().equalsIgnoreCase(name)) {
                students.remove(i);
                System.out.println(name + " has been deleted from the system.");
                return; 
            }
        }
        System.out.println("Could not delete. " + name + " was not found.");
    }

    public static void main(String[] args) {
        
        StudentManager manager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true; 

        System.out.println("Welcome to the Student Manager!");

        while (isRunning) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Add a student (Create)");
            System.out.println("2. Display all students (Read)");
            System.out.println("3. Search for a student (Read)");
            System.out.println("4. Update a student (Update)");
            System.out.println("5. Delete a student (Delete)");
            System.out.println("6. Exit");
            System.out.print("Choose an option (1-6): ");

            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                
                System.out.print("Enter age: ");
                int age = 0;
                // --- NEW CODE: Try-Catch loop for age ---
                while (true) {
                    try {
                        // We TRY to turn their text into a number
                        age = Integer.parseInt(scanner.nextLine());
                        break; // If successful, we break out of this little loop
                    } catch (NumberFormatException e) {
                        // If it fails, we CATCH the error and ask again
                        System.out.print("Invalid input! Please enter a number for the age: ");
                    }
                }
                
                System.out.print("Enter grade: ");
                String grade = scanner.nextLine();
                
                manager.addStudent(name, age, grade);
                System.out.println(name + " was added!");
                
            } else if (choice.equals("2")) {
                System.out.println("\n--- Student List ---");
                manager.displayStudents();
                
            } else if (choice.equals("3")) {
                System.out.print("Enter the name you want to search for: ");
                String searchName = scanner.nextLine();
                System.out.println("\n--- Search Results ---");
                manager.searchStudent(searchName);
                
            } else if (choice.equals("4")) {
                System.out.print("Enter the CURRENT name of the student to update: ");
                String oldName = scanner.nextLine();
                System.out.print("Enter their NEW name: ");
                String newName = scanner.nextLine();
                
                System.out.print("Enter their NEW age: ");
                int newAge = 0;
                // --- NEW CODE: Try-Catch loop for updating age ---
                while (true) {
                    try {
                        newAge = Integer.parseInt(scanner.nextLine());
                        break; 
                    } catch (NumberFormatException e) {
                        System.out.print("Invalid input! Please enter a number for the age: ");
                    }
                }
                
                System.out.print("Enter their NEW grade: ");
                String newGrade = scanner.nextLine();
                
                manager.updateStudent(oldName, newName, newAge, newGrade);
                
            } else if (choice.equals("5")) {
                System.out.print("Enter the name of the student to delete: ");
                String deleteName = scanner.nextLine();
                manager.deleteStudent(deleteName);
                
            } else if (choice.equals("6")) {
                System.out.println("Exiting program. Goodbye!");
                isRunning = false; 
                
            } else {
                System.out.println("Invalid choice. Please type a number between 1 and 6.");
            }
        }

        scanner.close();
    }
}