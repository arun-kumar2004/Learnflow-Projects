import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private int id;
    private String name;
    private double hourlyRate;
    private Map<String, Integer> attendance;
    private int dailyWorkingHours;

    public Employee(int id, String name, double hourlyRate) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.attendance = new HashMap<>();
        this.dailyWorkingHours = 8; // Default value
    }

    public void markAttendance(int workingDays, int dailyWorkingHours) {
        if (workingDays > 30) {
            System.out.println("Warning: Working days exceed one month (30 days).");
        }
        this.dailyWorkingHours = dailyWorkingHours;
        attendance.put("Month", workingDays);
    }

    public double calculateTotalHoursWorked() {
        int totalDays = attendance.getOrDefault("Month", 0);
        return totalDays * dailyWorkingHours;
    }

    public double calculateGrossSalary() {
        return Math.round(calculateTotalHoursWorked() * hourlyRate * 10.0) / 10.0;
    }

    public double calculateTax() {
        double grossSalary = calculateGrossSalary();
        if (grossSalary < 50000) {
            return 0.0; // No tax
        } else if (grossSalary <= 100000) {
            return Math.round(grossSalary * 0.01 * 10.0) / 10.0; // 1% tax
        } else if (grossSalary <= 150000) {
            return Math.round(grossSalary * 0.02 * 10.0) / 10.0; // 2% tax
        } else {
            return Math.round(grossSalary * 0.03 * 10.0) / 10.0; // 3% tax (or any other rate as per your requirement)
        }
    }

    public double calculateNetSalary() {
        return Math.round((calculateGrossSalary() - calculateTax()) * 10.0) / 10.0;
    }

    public String generatePayslip() {
        return "Employee ID: " + id + "\nName: " + name + "\nTotal Hours Worked: " + calculateTotalHoursWorked() +
                "\nGross Salary: $" + calculateGrossSalary() + "\nTax: $" + calculateTax() +
                "\nNet Salary: $" + calculateNetSalary();
    }
}

public class EmployeePayrollSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
            
        System.out.println("Welcome in Employee Payroll System");
        System.out.print("Enter employee ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter employee name: ");
        String name = scanner.nextLine();
        System.out.print("Enter hourly rate: ");
        double hourlyRate = scanner.nextDouble();

        Employee employee = new Employee(id, name, hourlyRate);

        // Attendance marking
        System.out.println("\nAttendance Marking:");
        System.out.print("Enter number of working days in the month: ");
        int workingDays = scanner.nextInt();
        System.out.print("Enter daily working hours: ");
        int dailyWorkingHours = scanner.nextInt();
        employee.markAttendance(workingDays, dailyWorkingHours);

        System.out.println("\nPayslip:");
        System.out.println(employee.generatePayslip());
        System.out.println("Thanks for using ");

        scanner.close();
    }
}