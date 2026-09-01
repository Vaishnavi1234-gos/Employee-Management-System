import dao.EmployeeDAO;
import model.Employee;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {

            System.out.println("\n================================");
            System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Search Employee");
            System.out.println("4. Update Employee");
            System.out.println("5. Delete Employee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                // ADD
                case 1:

                    System.out.print("Enter name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter department: ");
                    String department = sc.nextLine();

                    System.out.print("Enter salary: ");
                    double salary = sc.nextDouble();

                    Employee employee = new Employee(
                            0,
                            name,
                            email,
                            department,
                            salary
                    );

                    dao.addEmployee(employee);
                    break;


                // VIEW
                case 2:

                    dao.viewEmployees();
                    break;


                // SEARCH
                case 3:

                    System.out.print("Enter employee ID to search: ");
                    int searchId = sc.nextInt();

                    dao.searchEmployee(searchId);
                    break;


                // UPDATE
                case 4:

                    System.out.print("Enter employee ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter new name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter new email: ");
                    String newEmail = sc.nextLine();

                    System.out.print("Enter new department: ");
                    String newDepartment = sc.nextLine();

                    System.out.print("Enter new salary: ");
                    double newSalary = sc.nextDouble();

                    Employee updatedEmployee = new Employee(
                            updateId,
                            newName,
                            newEmail,
                            newDepartment,
                            newSalary
                    );

                    dao.updateEmployee(updatedEmployee);
                    break;


                // DELETE
                case 5:

                    System.out.print("Enter employee ID to delete: ");
                    int deleteId = sc.nextInt();

                    dao.deleteEmployee(deleteId);
                    break;


                // EXIT
                case 6:

                    System.out.println(
                            "Thank you for using Employee Management System!"
                    );

                    sc.close();
                    return;


                default:

                    System.out.println(
                            "Invalid choice! Please try again."
                    );
            }
        }
    }
}