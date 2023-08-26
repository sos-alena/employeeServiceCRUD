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
        if (searchId(id)) {
            Department department = departmentService.getById(id);
            departmentService.remove(department);
            System.out.println("Department successfully deleted!");
        }

    }

    public void editeDepartment() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
        if (searchId(id)) {
            Department department = departmentService.getById(id);
            System.out.println(department);
            System.out.println("Enter department title for update: ");
            String title = inputValidateStr ();
            if(!searchTitle(title)){
                department.setTitle(title);
                departmentService.update(department);
                System.out.println("Department successfully update!");
            }else {
                System.out.println("Department with the same Title already exists.");
            }

        }
    }
    private static void checkId(Long id) throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.getAll();
        for (Employee item : employees) {
            Department department = item.getDepartment();
            Long ida = department.getId();
            if (Objects.equals(id, ida)) {
                System.out.println("Yes! The current Id is an external key for the table Employee");
                System.out.println(item);


            } else {
                System.out.println("No such ID exists");
            }
        }
    }

    private Boolean searchId(Long id) throws SQLException {
        List<Department> departments = departmentService.getAll();
        for (Department item:departments){
            Long ida = item.getId();
            if(ida.equals(id)){
                System.out.println("Success! This id exists");
                return true;
            } else {
                System.out.println("this ID does not exist in the database Department");
            }

        }
        return false;
    }

    private Boolean searchTitle(String title) throws SQLException {
        List<Department> departments = departmentService.getAll();
        for (Department item:departments){
            String string = item.getTitle();
            if(string.equals(title)){
                System.out.println("Success! This id exists");
                return true;
            }

        }
        return false;
    }

}

