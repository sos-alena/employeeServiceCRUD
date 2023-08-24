package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Department;
import entity.Employee;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Domain {
static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws SQLException, IOException {


        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();
Address address = addressService.getById(1L);
Department department = departmentService.getById(1L);



        Employee employee = getEmployee(address, department);
        employeeService.add(employee);


      /*  Address address = new Address();
        System.out.println("Enter country");
        String country = READER.readLine();
        address.setCountry(country);
        System.out.println("Enter town");
        String town = READER.readLine();
        address.setTown(town);
        System.out.println("street");
        String street = READER.readLine();
        address.setStreet(street);
        System.out.println("postCode");*/
       // String postCode = READER.readLine();
      //  address.setPostCode(postCode);

       // addressService.add(address);

       /* Department department = new Department();
        System.out.println("Title of department");
        String name = READER.readLine();
        department.setTitle(name);

        departmentService.add(department);*/





        System.out.println(department);

        HibernateUtil.shutdown();

    }

    private static Employee getEmployee(Address address, Department department) throws IOException {
        Employee employee = new Employee();
        System.out.println("Enter FirstName");
        String firstName = READER.readLine();
        employee.setFirstName(firstName);
        System.out.println("Enter LastName");
        String lastName = READER.readLine();
        employee.setLastName(lastName);
        System.out.println("Enter year");
        int year = Integer.parseInt(READER.readLine());
        System.out.println("Enter mounth");
        int mounth = Integer.parseInt(READER.readLine());
        System.out.println("Enter day");
        int day = Integer.parseInt(READER.readLine());


        LocalDate birthday = LocalDate.of(year, mounth, day);

        employee.setBirthday(birthday);

        System.out.println("Enter phone number");
        String phone_number = READER.readLine();
        employee.setPhone_number(phone_number);
        employee.setAddress(address);

        System.out.println("Enter year emploiment_date");
        int year2 = Integer.parseInt(READER.readLine());
        System.out.println("Enter mounth emploiment_date");
        int mounth2 = Integer.parseInt(READER.readLine());
        System.out.println("Enter day emploiment_date");
        int day2 = Integer.parseInt(READER.readLine());
        LocalDate employee_date = LocalDate.of(year2, mounth2, day2);
        employee.setEmployment_data(employee_date);

        employee.setData_of_dismissal(null);
        System.out.println("Enter position");
        String position = READER.readLine();
        employee.setPosition(position);
        employee.setDepartment(department);

        return employee;
    }
}
