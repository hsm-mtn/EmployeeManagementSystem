import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee{

    private String id;
    private String name;
    private String department;
    private double salary;
    
    public Employee(String id, String name, String department, double samary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = samary;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double samary) {
        this.salary = samary;
    }

    @Override
    public String toString() {
        return "[id=" + id + ", name=" + name + ", department=" + department + ", salary=" + salary + "$]";
    }

    



}
public class EmployeeManagementSystem {

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        loadEmployee(employees);
        System.out.println("welcome to the employee management system");

        while (true) {
            System.out.println("please select an option");
            System.out.println("1.add a new employee and his info");
            System.out.println("2.view all employees");
            System.out.println("3.look for a spicific employee");
            System.out.println("4.update an exesting employee");
            System.out.println("5.delete an employee");
            System.out.println("6.save and exite");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 : 
                    addEmployee(sc, employees); 
                    break;
                case 2 :
                    viewAllEmployees(employees);
                    break;
                case 3 :
                    searchEmployees(sc, employees);
                    break;
                case 4 :
                    updateEmployee(sc, employees);
                    break;
                case 5 :
                    deleteEmployee(sc, employees);
                    break;
                case 6 :
                saveEmployee(employees);
                    System.out.println("saving and exiting .....");
                    return;
                default:
                System.out.println("Invalid choice !");
                    break;
            }
            
        }
  
        
    }
    private static void addEmployee(Scanner sc, List<Employee> employees){
        System.out.println("please enter the info");
        System.out.println("Id :");
        String getId = sc.nextLine();
        System.out.println("the name of the employee :");
        String getName = sc.nextLine();
        System.out.println("the department :");
        String getDepartment = sc.nextLine();
        System.out.println("the salary :");
        double getSamary = sc.nextDouble();
        sc.nextLine();

        employees.add(new Employee(getId, getName, getDepartment, getSamary));
        System.out.println("the employee has been added successfully");
        
    }

    private static void viewAllEmployees(List<Employee> employees){
        if(employees.isEmpty()){
            System.out.println("No employees founf!");
        }else {
            for(Employee emp : employees){
                System.out.println(emp);
            }
        }
    }

    private static void searchEmployees(Scanner sc, List<Employee> employees){
        System.out.println("enter employee name or id to search");
        String search = sc.nextLine();
            for(Employee emp : employees){
                    if(emp.getId().equals(search) || emp.getName().equals(search)){
                        System.out.println(emp);
                        return;
                    }
            }
            System.out.println("Employee not found");

    }

    private static void updateEmployee(Scanner sc, List<Employee> employees){
        System.out.println("please enter the employee id you want to update");
        String id = sc.nextLine();
        for( Employee emp : employees){
                if( emp.getId().equals(id)){
                    System.out.println("enter the new department :");
                    String department = sc.nextLine();
                    System.out.println("enter the new salary");
                    double salary = sc.nextDouble();
                    sc.nextLine();

                    emp.setDepartment(department);
                    emp.setSalary(salary);
                    System.out.println("the employee updated successfully");
                    return;

                }
        }
        System.out.println("Employee not found");


    }

    private static void deleteEmployee(Scanner sc, List<Employee> employees){
        System.out.println("enter employee id to delete ");
        String id = sc.nextLine();
        for(Employee emp : employees){
            if(emp.getId().equals(id)){
                employees.remove(emp);
                System.out.println("employee deleted successfully");
                return;
            }
        }
        System.out.println("employee not found");
    }

    private static void saveEmployee(List<Employee> employees){
        try (FileWriter writer = new FileWriter("employees.txt")) {
            for (Employee emp : employees) {
                writer.write(emp.getId() + "," + emp.getName() + "," + emp.getDepartment() + "," + emp.getSalary() + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving employees!");
        }
    }

    private static void loadEmployee(List<Employee> employees){
           try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                employees.add(new Employee(parts[0], parts[1], parts[2], Double.parseDouble(parts[3])));
            }
        } catch (IOException e) {
            System.out.println("No employees found or error loading employees.");
        }

    }


}