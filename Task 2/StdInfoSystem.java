import java.util.*;

class Student {
    private String name;
    private String address;
    private String contact;     
    private String program;

    // Constructor
    public Student(String name, String address, String contact, String program) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.program = program;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    // toString method to print student information
    @Override
    public String toString() {
        return "Name: " + name + ", Address: " + address + ", Contact: " + contact + ", Program: " + program;
    }
}

class Course {
    private String courseCode;
    private String courseName;
    private int capacity;
    private List<Student> enrolledStudents;

    // Constructor of course class
    public Course(String courseCode, String courseName, int capacity) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.capacity = capacity;
        this.enrolledStudents = new ArrayList<>();
    }

    // Getters and Setters of course class
    public String getCourseName() {
        return courseName;
    }
    
    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }
    
    // methods for adding/removing students, checking capacity 
    
    public void addStudent(Student student) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(student);
            System.out.println("Student " + student.getName() + " enrolled in course " + courseCode);
        } else {
            System.out.println("Course " + courseCode + " is already full");
        }
    }
}

public class StdInfoSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Student");
            System.out.println("2. Print All Records");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    // Create student
                    System.out.println("\nEnter student details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Address: ");
                    String address = scanner.nextLine();
                    System.out.print("Contact: ");
                    String contact = scanner.nextLine();
                    System.out.print("Program: ");
                    String program = scanner.nextLine();
                    Student student = new Student(name, address, contact, program);
                    students.add(student);
                    System.out.println("Student created successfully: " + student);
                    break;
                case 2:
                    // Print all records
                    System.out.println("\nAll Student Records:");
                    for (Student s : students) {
                        System.out.println(s);
                    }
                    break;
                case 3:
                    // Exit
                    System.out.println("Thanks for using");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }    
}
