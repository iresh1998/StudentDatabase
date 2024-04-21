import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentDatabase {
    private static String[] studentNames = new String[10];
    private static int[] studentGrades = new int[10];
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Add a new student");
            System.out.println("2. Display all students");
            System.out.println("3. Search for a student by name");
            System.out.println("4. Calculate average grade");
            System.out.println("5. Sort students by grades in ascending order");
            System.out.println("6. Find the student with the highest grade");
            System.out.println("7. Find the student with the lowest grade");
            System.out.println("8. Update a student's grade");
            System.out.println("9. Remove a student from the database");
            System.out.println("10. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addStudent(scanner);
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    searchStudentByName(scanner);
                    break;
                case 4:
                    calculateAverageGrade();
                    break;
                case 5:
                    sortStudentsByGrade();
                    break;
                case 6:
                    findHighestGradeStudent();
                    break;
                case 7:
                    findLowestGradeStudent();
                    break;
                case 8:
                    updateStudentGrade(scanner);
                    break;
                case 9:
                    removeStudent(scanner);
                    break;
                case 10:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 10);

        scanner.close();
    }

    private static void addStudent(Scanner scanner) {
        if (studentCount >= studentNames.length) {
            
            int newSize = studentNames.length * 2; // Double the size
            String[] newStudentNames = new String[newSize];
            int[] newStudentGrades = new int[newSize];

            // Copy existing elements to the new arrays
            for (int i = 0; i < studentCount; i++) {
                newStudentNames[i] = studentNames[i];
                newStudentGrades[i] = studentGrades[i];
            }

            // Update names to the new arrays
            studentNames = newStudentNames; 
            studentGrades = newStudentGrades;
        }

        System.out.print("Enter student's name: ");
        String name = scanner.nextLine();
        // System.out.print("Enter student's grade: ");
        // int grade = scanner.nextInt();


        int grade;
        while (true) {
            System.out.print("Enter student's grade: ");
            try {
                grade = scanner.nextInt();
                break; // Exit the loop if input is valid
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter  Number for the grade !");
                scanner.nextLine(); // prevent infinite loop
            }

        }

        studentNames[studentCount] = name;
        studentGrades[studentCount] = grade;
        studentCount++;

        System.out.println("Student added successfully.");
    }

    private static void displayStudents() {
        System.out.println("Students in the database:");
        for (int i = 0; i < studentCount; i++) {
            System.out.println("Name: " + studentNames[i] + ", Grade: " + studentGrades[i]);
        }
    }

    private static void searchStudentByName(Scanner scanner) {
        System.out.print("Enter the name of the student to search for: ");
        String nameToSearch = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i].equalsIgnoreCase(nameToSearch)) {
                System.out.println("Student found:");
                System.out.println("Name: " + studentNames[i] + ", Grade: " + studentGrades[i]);
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found in the database.");
        }
    }

    private static void calculateAverageGrade() {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        int totalGrade = 0;
        for (int i = 0; i < studentCount; i++) {
            totalGrade += studentGrades[i];
        }

        double averageGrade = (double) totalGrade / studentCount;
        System.out.println("Average grade of all students: " + averageGrade);
    }
    private static void sortStudentsByGrade() {
        if (studentCount <= 1) {
            // No need to sort if there are 0 or 1 students
            return;
        }
    
        // Implement bubble sort to sort grades and corresponding names simultaneously
        for (int i = 0; i < studentCount - 1; i++) {
            for (int j = 0; j < studentCount - i - 1; j++) {
                if (studentGrades[j] > studentGrades[j + 1]) {
                    // Swap grades
                    int tempGrade = studentGrades[j];
                    studentGrades[j] = studentGrades[j + 1];
                    studentGrades[j + 1] = tempGrade;
    
                    // Swap names
                    String tempName = studentNames[j];
                    studentNames[j] = studentNames[j + 1];
                    studentNames[j + 1] = tempName;
                }
            }
        }
    
        System.out.println("Students sorted by grades in ascending order.");
    }

    private static void findHighestGradeStudent() {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        int highestGrade = Integer.MIN_VALUE;
        String highestGradeStudent = "";

        for (int i = 0; i < studentCount; i++) {
            if (studentGrades[i] > highestGrade) {
                highestGrade = studentGrades[i];
                highestGradeStudent = studentNames[i];
            }
        }

        if (!highestGradeStudent.isEmpty()) {
            System.out.println("Student with the highest grade:");
            System.out.println("Name: " + highestGradeStudent + ", Grade: " + highestGrade);
        } else {
            System.out.println("No student with a grade found.");
        }
    }

    private static void findLowestGradeStudent() {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        int lowestGrade = Integer.MAX_VALUE;
        String lowestGradeStudent = "";

        for (int i = 0; i < studentCount; i++) {
            if (studentGrades[i] < lowestGrade) {
                lowestGrade = studentGrades[i];
                lowestGradeStudent = studentNames[i];
            }
        }

        if (!lowestGradeStudent.isEmpty()) {
            System.out.println("Student with the lowest grade:");
            System.out.println("Name: " + lowestGradeStudent + ", Grade: " + lowestGrade);
        } else {
            System.out.println("No student with a grade found.");
        }
    }
    
    private static void updateStudentGrade(Scanner scanner) {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        System.out.print("Enter the name of the student whose grade you want to update: ");
        String nameToUpdate = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i].equalsIgnoreCase(nameToUpdate)) {
                System.out.print("Enter the new grade for " + studentNames[i] + ": ");
                int newGrade = scanner.nextInt();
                studentGrades[i] = newGrade;
                System.out.println("Grade updated successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found in the database.");
        }
    }

    private static void removeStudent(Scanner scanner) {
        if (studentCount == 0) {
            System.out.println("No students in the database.");
            return;
        }

        System.out.print("Enter the name of the student you want to remove: ");
        String nameToRemove = scanner.nextLine();
        boolean found = false;

        for (int i = 0; i < studentCount; i++) {
            if (studentNames[i].equalsIgnoreCase(nameToRemove)) {
                // Shift elements to remove the student
                for (int j = i; j < studentCount - 1; j++) {
                    studentNames[j] = studentNames[j + 1];
                    studentGrades[j] = studentGrades[j + 1];
                }
                studentCount--; // Decrement student count
                System.out.println("Student removed successfully.");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found in the database.");
        }
    }    






}
