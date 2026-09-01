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

        String sql = "SELECT * FROM employee";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\n----- Employee List -----");

            while (rs.next()) {

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Salary: " + rs.getDouble("salary"));

                System.out.println("-------------------------");
            }

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

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                System.out.println("\n----- Employee Found -----");

                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("Salary: " + rs.getDouble("salary"));

                System.out.println("--------------------------");

            } else {

                System.out.println("Employee not found!");
            }

        } catch (SQLException e) {

            System.out.println("Error searching employee!");
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

            System.out.println("Error updating employee!");
            e.printStackTrace();
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
}