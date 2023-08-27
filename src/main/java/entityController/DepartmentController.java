package entityController;

import entity.Address;
import entity.Department;
import entity.Employee;
import listEntity.AddressItem;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static validator.InputValue.*;
import static validator.InputValue.inputYesOrNot;
import static validator.validationCountry.getNameCountry;

public class DepartmentController {

    DepartmentService departmentService = new DepartmentService();

    public Department inputDepartment() throws SQLException {
        Department department = new Department();
        System.out.println("Enter name of Department");
        String title = inputValidateStr();
        department.setTitle(title);
        departmentService.add(department);

        return department;
    }

    public void deleteDepartment() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
        Department department = searchId(id);
        if (department == null) {
            System.out.println("Depatment with this ID does not exist.");
        } else {
            System.out.println(department);
            int counter = checkIdDepartmentInEmployee(id);
            if (counter == 0) {
                departmentService.remove(department);
                System.out.println("Department successfully delete!");

            } else {
                System.out.println("Unable to delete department with id because " +
                        "it is associated with the table Employee) ");
            }
        }
    }

    public void editeDepartment() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
        if (!(searchId(id) == null)) {
            Department department = departmentService.getById(id);
            System.out.println(department);
            System.out.println("Enter department title for update: ");
            String title = inputValidateStr ();
            if(!(searchTitle(title) == null)){
                department.setTitle(title);
                departmentService.update(department);
                System.out.println("Department successfully update!");
            }else {
                System.out.println("Department with the same Title already exists.");
            }

        }
    }
    private int checkIdDepartmentInEmployee(Long id){
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = null;
        try {
            employees = employeeService.getAll();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

        int temp = 0;

        for (Employee item: employees) {
            Department department = item.getDepartment();
            if (!(department == null)) {
                Long ida = department.getId();
                if(id.equals(ida)) {

                    temp++;
                }
            }
        }
        return temp;
    }

    private Department searchId(Long id) throws SQLException {
        List<Department> departments = departmentService.getAll();
        for (Department item:departments){
            Long ida = item.getId();
            if(ida.equals(id)){
                System.out.println("Success! This id exists");
                return item;
            }

        }
        return null;
    }

    public Department searchTitle(String title) throws SQLException {
        List<Department> departments = departmentService.getAll();
        for (Department item:departments){
            String string = item.getTitle();
            if(string.equals(title)){
                System.out.println("Success! This id exists");
                return item;
            }

        }
        return null;
    }

    public void selectAllDepartment() throws SQLException {
        System.out.println(departmentService.getAll());
    }

}

