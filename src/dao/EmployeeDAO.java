package dao;

import model.Employee;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {

    // =========================================
    // ADD EMPLOYEE
    // =========================================
    public void addEmployee(Employee employee) {

        String sql = "INSERT INTO employee " +
                     "(name, email, department, salary) " +
                     "VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getDepartment());
            ps.setDouble(4, employee.getSalary());

            ps.executeUpdate();

            System.out.println("Employee added successfully!");

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Employee with this email already exists!");
            } else {
                System.out.println("Error adding employee!");
                e.printStackTrace();
            }
        }
    }

    // =========================================
// VIEW ALL EMPLOYEES
// =========================================
public void viewEmployees() {

    String sql = "SELECT * FROM employee ORDER BY name ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("\n---------------- EMPLOYEE LIST ----------------");

        System.out.printf("%-5s %-22s %-25s %-15s %-12s%n",
                "ID", "Name", "Email", "Department", "Salary");

        System.out.println(
                "--------------------------------------------------------------------------");

        boolean found = false;

        while (rs.next()) {

            found = true;

            System.out.printf("%-5d %-22s %-25s %-15s %-12.2f%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("department"),
                    rs.getDouble("salary"));
        }

        if (!found) {
            System.out.println("No employees found!");
        }

        System.out.println(
                "--------------------------------------------------------------------------");

    } catch (SQLException e) {

        System.out.println("Error retrieving employees!");
        e.printStackTrace();
    }
}


    // =========================================
    // SEARCH EMPLOYEE BY ID
    // =========================================
    public void searchEmployee(int id) {

        String sql = "SELECT * FROM employee WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    System.out.println("\n----- Employee Found -----");

                    printEmployee(rs);

                } else {

                    System.out.println("Employee not found!");
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching employee!");
            e.printStackTrace();
        }
    }


    // =========================================
    // SEARCH EMPLOYEE BY NAME
    // =========================================
    public void searchEmployeeByName(String name) {

        String sql = "SELECT * FROM employee WHERE name LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\n----- Search Results -----");

                while (rs.next()) {

                    found = true;

                    printEmployee(rs);
                }

                if (!found) {
                    System.out.println("No employee found with this name!");
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching employee by name!");
            e.printStackTrace();
        }
    }


    // =========================================
    // SEARCH EMPLOYEE BY DEPARTMENT
    // =========================================
    public void searchEmployeeByDepartment(String department) {

        String sql = "SELECT * FROM employee WHERE department LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + department + "%");

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\n----- Department Employees -----");

                while (rs.next()) {

                    found = true;

                    printEmployee(rs);
                }

                if (!found) {
                    System.out.println("No employees found in this department!");
                }
            }

        } catch (SQLException e) {

            System.out.println("Error searching employee by department!");
            e.printStackTrace();
        }
    }


    // =========================================
    // UPDATE EMPLOYEE
    // =========================================
    public void updateEmployee(Employee employee) {

        String sql = "UPDATE employee " +
                     "SET name=?, email=?, department=?, salary=? " +
                     "WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, employee.getName());
            ps.setString(2, employee.getEmail());
            ps.setString(3, employee.getDepartment());
            ps.setDouble(4, employee.getSalary());
            ps.setInt(5, employee.getId());

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee updated successfully!");

            } else {

                System.out.println("Employee not found!");
            }

        } catch (SQLException e) {

            if (e.getMessage().contains("Duplicate")) {
                System.out.println("Another employee already uses this email!");
            } else {
                System.out.println("Error updating employee!");
                e.printStackTrace();
            }
        }
    }


    // =========================================
    // DELETE EMPLOYEE
    // =========================================
    public void deleteEmployee(int id) {

        String sql = "DELETE FROM employee WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {

                System.out.println("Employee deleted successfully!");

            } else {

                System.out.println("Employee not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error deleting employee!");
            e.printStackTrace();
        }
    }


    // =========================================
    // PRINT EMPLOYEE DETAILS
    // =========================================
    private void printEmployee(ResultSet rs) throws SQLException {

        System.out.println("--------------------------");
        System.out.println("ID: " + rs.getInt("id"));
        System.out.println("Name: " + rs.getString("name"));
        System.out.println("Email: " + rs.getString("email"));
        System.out.println("Department: " + rs.getString("department"));
        System.out.println("Salary: " + rs.getDouble("salary"));
        System.out.println("--------------------------");
    }
    // =========================================
// EMPLOYEE STATISTICS
// =========================================
public void showStatistics() {

    String sql = "SELECT COUNT(*) AS total, " +
                 "AVG(salary) AS average, " +
                 "MAX(salary) AS highest, " +
                 "MIN(salary) AS lowest " +
                 "FROM employee";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        if (rs.next()) {

            int total = rs.getInt("total");
            double average = rs.getDouble("average");
            double highest = rs.getDouble("highest");
            double lowest = rs.getDouble("lowest");

            System.out.println("\n----- EMPLOYEE STATISTICS -----");

            System.out.println("Total Employees: " + total);
            System.out.printf("Average Salary: Rs.%.2f%n", average);
            System.out.printf("Highest Salary: Rs.%.2f%n", highest);
            System.out.printf("Lowest Salary: Rs.%.2f%n", lowest);
        }

    } catch (SQLException e) {

        System.out.println("Error retrieving employee statistics!");
        e.printStackTrace();
    }
}
// =========================================
// DEPARTMENT STATISTICS
// =========================================
public void showDepartmentStatistics() {

    String sql = "SELECT department, COUNT(*) AS total " +
                 "FROM employee " +
                 "GROUP BY department " +
                 "ORDER BY department ASC";

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        System.out.println("\n----- DEPARTMENT STATISTICS -----");

        boolean found = false;

        while (rs.next()) {

            found = true;

            System.out.printf("%-15s : %d employee(s)%n",
                    rs.getString("department"),
                    rs.getInt("total"));
        }

        if (!found) {
            System.out.println("No employees found!");
        }

    } catch (SQLException e) {

        System.out.println("Error retrieving department statistics!");
        e.printStackTrace();
    }
}
}