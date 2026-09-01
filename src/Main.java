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
            System.out.println("6. Employee Statistics");
            System.out.println("7. Exit");
             System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    addEmployee(sc, dao);
                    break;

                case "2":
                    dao.viewEmployees();
                    break;

                case "3":
                    searchEmployee(sc, dao);
                    break;

                case "4":
                    updateEmployee(sc, dao);
                    break;

                case "5":
                    deleteEmployee(sc, dao);
                    break;

               case "6":
               dao.showStatistics();
                dao.showDepartmentStatistics();
                break;

              case "7":
              System.out.println("Thank you for using Employee Management System!");
              sc.close();
              return;

                default:
                    System.out.println("Invalid choice! Please enter 1 to 6.");
            }
        }
    }

    // =========================================
    // ADD EMPLOYEE
    // =========================================
    private static void addEmployee(Scanner sc, EmployeeDAO dao) {

        System.out.println("\n----- Add Employee -----");

        System.out.print("Enter name: ");
        String name = sc.nextLine().trim();

        if (name.isEmpty()) {
            System.out.println("Name cannot be empty!");
            return;
        }

        System.out.print("Enter email: ");
        String email = sc.nextLine().trim();

        if (!email.contains("@") || !email.contains(".")) {
            System.out.println("Invalid email!");
            return;
        }

        System.out.print("Enter department: ");
        String department = sc.nextLine().trim();

        if (department.isEmpty()) {
            System.out.println("Department cannot be empty!");
            return;
        }

        System.out.print("Enter salary: ");

        try {

            double salary = Double.parseDouble(sc.nextLine());

            if (salary < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }

            Employee employee = new Employee(
                    0,
                    name,
                    email,
                    department,
                    salary
            );

            dao.addEmployee(employee);

        } catch (NumberFormatException e) {

            System.out.println("Invalid salary! Please enter a number.");
        }
    }


    // =========================================
    // SEARCH EMPLOYEE
    // =========================================
    private static void searchEmployee(Scanner sc, EmployeeDAO dao) {

        while (true) {

            System.out.println("\n----- SEARCH EMPLOYEE -----");
            System.out.println("1. Search by ID");
            System.out.println("2. Search by Name");
            System.out.println("3. Search by Department");
            System.out.println("4. Back");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":

                    System.out.print("Enter employee ID: ");

                    try {

                        int id = Integer.parseInt(sc.nextLine());

                        if (id <= 0) {
                            System.out.println("ID must be greater than 0!");
                            break;
                        }

                        dao.searchEmployee(id);

                    } catch (NumberFormatException e) {

                        System.out.println("Invalid ID! Please enter a number.");
                    }

                    break;


                case "2":

                    System.out.print("Enter employee name: ");
                    String name = sc.nextLine();

                    if (name.trim().isEmpty()) {
                        System.out.println("Name cannot be empty!");
                        break;
                    }

                    dao.searchEmployeeByName(name);

                    break;


                case "3":

                    System.out.print("Enter department: ");
                    String department = sc.nextLine();

                    if (department.trim().isEmpty()) {
                        System.out.println("Department cannot be empty!");
                        break;
                    }

                    dao.searchEmployeeByDepartment(department);

                    break;


                case "4":

                    return;


                default:

                    System.out.println("Invalid choice! Please enter 1 to 4.");
            }
        }
    }


    // =========================================
    // UPDATE EMPLOYEE
    // =========================================
    private static void updateEmployee(Scanner sc, EmployeeDAO dao) {

        System.out.println("\n----- Update Employee -----");

        try {

            System.out.print("Enter employee ID: ");
            int id = Integer.parseInt(sc.nextLine());

            if (id <= 0) {
                System.out.println("ID must be greater than 0!");
                return;
            }

            System.out.print("Enter new name: ");
            String name = sc.nextLine();

            if (name.trim().isEmpty()) {
                System.out.println("Name cannot be empty!");
                return;
            }

            System.out.print("Enter new email: ");
            String email = sc.nextLine();

            if (!email.contains("@") || !email.contains(".")) {

               System.out.println("Invalid email!");

               return;
            }

            System.out.print("Enter new department: ");
            String department = sc.nextLine(); 

            if (department.trim().isEmpty()) {
                System.out.println("Department cannot be empty!");
                return;
            }

            System.out.print("Enter new salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            if (salary < 0) {
                System.out.println("Salary cannot be negative!");
                return;
            }

            Employee employee = new Employee(
                    id,
                    name,
                    email,
                    department,
                    salary
            );

            dao.updateEmployee(employee);

        } catch (NumberFormatException e) {

            System.out.println("Invalid number! Please enter a valid number.");
        }
    }


   // =========================================
    // DELETE EMPLOYEE
     // =========================================
   private static void deleteEmployee(Scanner sc, EmployeeDAO dao) {

      System.out.println("\n----- Delete Employee -----");

       try {

        System.out.print("Enter employee ID: ");
        int id = Integer.parseInt(sc.nextLine());

        if (id <= 0) {
            System.out.println("ID must be greater than 0!");
            return;
        }

        System.out.print("Are you sure you want to delete employee ID "
                + id + "? (yes/no): ");

        String confirmation = sc.nextLine();

        if (confirmation.equalsIgnoreCase("yes")) {

            dao.deleteEmployee(id);

        } else {

            System.out.println("Delete cancelled.");
        }

    } catch (NumberFormatException e) {

        System.out.println("Invalid ID! Please enter a number.");
    }
}
    
} 

