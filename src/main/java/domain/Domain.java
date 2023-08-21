package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Department;
import entity.Employee;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Domain {

    public static void main(String[] args) throws SQLException {


        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();


        Address address = new Address();
        address.setCountry("DC");
        address.setTown("Gotham city");
        address.setStreet("Arkham street 1");
        address.setPostCode("12345");

        Department department = new Department();
        department.setTitle("Gotham PD");

        Employee employee = getEmployee(address, department);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        department.setEmployees(employees);

        addressService.add(address);
        employeeService.add(employee);
        departmentService.add(department);

        Address a = addressService.getById(1L);
        System.out.println(a);

 Employee e = employeeService.getById(1L);
        System.out.println(e);

Department p = departmentService.getById(1L);
        System.out.println(p);

        List<Address> addressList = addressService.getAll();
        System.out.println(addressList);

        List<Employee> employeeList = employeeService.getAll();
        System.out.println(employeeList);

        List<Department> departmentList = departmentService.getAll();
        System.out.println(departmentList);

        addressService.remove(address);

        employeeService.remove(employee);

        departmentService.remove(department);

        addressService.update(address);

        employeeService.update(employee);

        departmentService.update(department);

        HibernateUtil.shutdown();

    }

    private static Employee getEmployee(Address address, Department department) {
        Employee employee = new Employee();
        employee.setFirstName("James");
        employee.setLastName("Gordon");

        LocalDate birthday = LocalDate.of(1976, 12, 18);

        employee.setBirthday(birthday);
        employee.setPhone_number("+380961055964");
        employee.setAddress(address);

        LocalDate employee_date = LocalDate.of(2011, 1, 25);
        employee.setEmployment_data(employee_date);

        employee.setData_of_dismissal(null);

        employee.setPosition("Accounter");
        employee.setDepartment(department);
        return employee;
    }
}
