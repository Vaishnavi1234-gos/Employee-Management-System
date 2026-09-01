# Employee Management System

A console-based Employee Management System developed using Java, JDBC, and MySQL.

## Features

- Add Employee
- View All Employees
- Search Employee by ID
- Update Employee
- Delete Employee
- MySQL database integration

## Technologies Used

- Java
- JDBC
- MySQL
- SQL
- Object-Oriented Programming
- DAO Design Pattern

## Project Structure

EMPLOYEEMANAGEMENTSYSTEM
│
├── src
│   ├── Main.java
│   │
│   ├── model
│   │   └── Employee.java
│   │
│   ├── dao
│   │   └── EmployeeDAO.java
│   │
│   ├── util
│   │   └── DBConnection.java
│   │
│   └── lib
│       └── MySQL Connector JAR
│
└── README.md

## Database

The application uses a MySQL database named:

employee_management

### Employee Table

- id
- name
- email
- department
- salary

## JDBC Flow

Main.java
    ↓
EmployeeDAO
    ↓
DBConnection
    ↓
JDBC
    ↓
MySQL

## CRUD Operations

### Create

Adds a new employee to the database.

### Read

Displays all employees and searches employees by ID.

### Update

Updates employee information using employee ID.

### Delete

Deletes an employee using employee ID.

## OOP Concepts Used

- Encapsulation
- Classes and Objects
- Constructors
- Getters and Setters

## How to Run

### Compile

```powershell
javac -cp "src\lib\mysql-connector-j-26.7.0.jar" -d out src\model\Employee.java src\util\DBConnection.java src\dao\EmployeeDAO.java src\Main.java

### Run

java -cp "out;src\lib\mysql-connector-j-26.7.0.jar" Main
## Author

Vaishnavi Goswami