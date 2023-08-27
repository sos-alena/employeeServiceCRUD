package entityController;

import entity.Address;
import entity.Department;
import entity.Employee;
import listEntity.AddressItem;
import listEntity.EmployeeItem;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static validator.InputValue.*;
import static validator.InputValue.inputYesOrNot;
import static validator.validationCountry.getNameCountry;

public class EmployeeController {

    EmployeeService employeeService = new EmployeeService();
    DepartmentService departmentService = new DepartmentService();
    DepartmentController departmentController = new DepartmentController();
    AddressController addressController = new AddressController();
    AddressService addressService = new AddressService();

    public Employee inputEmployee() throws SQLException {
        Employee employee = new Employee();
        LocalDate birthday = validBirthday();

        employee.setBirthday(birthday);
        LocalDate employmentDate = validEmploymentDate(birthday.minusYears(16));

        employee.setEmployment_data(employmentDate);

        System.out.println("Enter dataOfDismissal");
        LocalDate dataOfDismissal = addDateOfDismissal(employmentDate);

        employee.setData_of_dismissal(dataOfDismissal);

        System.out.println("Enter FirstName");
        String firstName = inputValidateStr();
        employee.setFirstName(firstName);
        System.out.println("Enter LastName");
        String lastName = inputValidateStr();
        employee.setLastName(lastName);
        System.out.println("Enter INN");
        String inn = inputINN();
        employee.setInn(inn);
        System.out.println("Enter phone-Number");
        String phoneNumber = inputPhoneNumber();
        employee.setPhone_number(phoneNumber);
        System.out.println("Enter Position");
        String position = inputValidateStr();
        employee.setPosition(position);
        Department department = addDepartmentToEmployee();
        employee.setDepartment(department);
        Address address = addAddressToEmployee();
        employee.setAddress(address);

        employeeService.add(employee);

        return employee;

    }

    private LocalDate addDateOfDismissal(LocalDate date) {

        if (isDataOfDismmissal()) {
            LocalDate localDate = validDataOfDismissal(date);
            return localDate;
        }
        return null;
    }

    private Department addDepartmentToEmployee() throws SQLException {
        System.out.println("Input Title Department: ");
        String title = inputValidateStr();
        Department department = departmentController.searchTitle(title);
        if (department == null) {
            Department departmentNew = departmentController.inputDepartment();
            return departmentNew;
        } else {
            return (department);
        }
    }

    private Address addAddressToEmployee() throws SQLException {
        System.out.println("Input id Address: ");
        Long id = inputValidateLong();
        Address address = addressController.searchId(id);
        if (address == null) {
            Address address1 = addressController.inputAddress();
            return address1;
        } else {
            return address;
        }

    }

    public void deleteEmployee() throws SQLException {
        System.out.println("Input number ID: ");
        String inn = inputINN();
        Employee employee = searchINN(inn);
        employeeService.remove(employee);
        System.out.println("Employee successfully deleted!");

    }

    public void editeEmployee() throws SQLException {
        //System.out.println("Input number ID: ");
        String inn = inputINN();
        Employee employee = searchINN(inn);
        if(employee != null){
            System.out.println(employee);
            cycleChoiceNameTable(employee);
            employeeService.update(employee);
            System.out.println("Employee successfully update!");System.out.println();
        }
        System.out.println("this employee does not exist");
    }

    private void selectNameTable(Employee employee) throws SQLException {
        EmployeeItem name = inputEmployeeItem();
        switch (name) {
            case BIRTHDAY -> employee.setBirthday(validBirthday());
            case DATA_OF_DISMISSAL -> employee.setData_of_dismissal(validDataOfDismissal(employee.getEmployment_data()));
            case EMPLOYMENT_DATA -> employee.setEmployment_data(validEmploymentDate(employee.getBirthday()));
            case FIRST_NAME -> employee.setFirstName(inputValidateStr());
            case LAST_NAME -> employee.setLastName(inputValidateStr());
            case INN -> employee.setInn(inputINN());
            case PHONE_NUMBER -> employee.setPhone_number(inputPhoneNumber());
            case POSITION -> employee.setPosition(inputValidateStr());
            case DEPARTMENT_ID -> employee.setDepartment(addDepartmentToEmployee());
            case ADDRESS_ID -> employee.setAddress(addAddressToEmployee());

        }
    }

    private void cycleChoiceNameTable(Employee employee) throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Employee, enter Y or N");
        String str = inputYesOrNot();
        if (!n.equals(str)) {
            selectNameTable(employee);
            cycleChoiceNameTable(employee);
        } else {
            System.out.println("Element unchanged!");
        }
    }


    public Employee searchINN(String str) {
        List<Employee> employees;
        try {
            employees = employeeService.getAll();
            for (Employee item : employees) {
                String inn = item.getInn();
                if (!(inn == null) || inn  == str) {
                    System.out.println("Success! This id exists");
                    return item;
                }
                System.out.println("Тhis inn does not exist");
                return null;
            }
        } catch ( SQLException e) {
            System.out.println("Error " + e );
        }
        return null;
    }

    private Boolean isDataOfDismmissal() {
        String switcher = "Y";
        System.out.println("Enter Y if the employee is fired or N if not fired");
        if (inputYesOrNot().equals(switcher)) {
            return true;
        }
        return false;
    }

    private LocalDate validEmploymentDate(LocalDate localDate) {
        System.out.println("Enter employmentDate");
        LocalDate employmentDate = inputDate();
        if (!employmentDate.isAfter(localDate)) {
            System.out.println("The date of employment cannot be less than the date of birth + 16 years");
            return validEmploymentDate(localDate);
        } else {

            return employmentDate;
        }
    }

    private LocalDate validDataOfDismissal(LocalDate localDate) {
        System.out.println("Enter Data of dismissal");
        LocalDate dataOfDismissal = inputDate();
        if (dataOfDismissal.isBefore(localDate)) {
            System.out.println("The date of dismissal cannot be less than the date of employment");
            return validDataOfDismissal(localDate);
        } else {

            return dataOfDismissal;
        }
    }

    private LocalDate validBirthday() {
        System.out.println("Enter Birthday");
        LocalDate birthday = inputDate();
        LocalDate date1 = birthday.plusYears(16);

        LocalDate dateNow = LocalDate.now();
        if (dateNow.isBefore(date1)) {
            System.out.println("Less than legal age for employment.");
            System.out.println("If this is an input error, try entering data again");
            System.out.println("Enter Y or N");
            String yn = inputYesOrNot();
            if(yn.equals("N")){
                System.exit(0);
                return null;
            }
            return validBirthday();
        } else {
            return birthday;
        }
    }
}

