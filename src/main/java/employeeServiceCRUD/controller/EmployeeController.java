package employeeServiceCRUD.controller;

import employeeServiceCRUD.controller.validator.InputValue;
import employeeServiceCRUD.models.Address;
import employeeServiceCRUD.models.Department;
import employeeServiceCRUD.models.Employee;
import employeeServiceCRUD.controller.enums.EmployeeColumns;
import employeeServiceCRUD.service.EmployeeService;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class EmployeeController {

    AddressController addressController;
    EmployeeService employeeService;
    DepartmentController departmentController;

    public EmployeeController(AddressController addressController, EmployeeService employeeService, DepartmentController departmentController) {
        this.addressController = addressController;
        this.employeeService = employeeService;
        this.departmentController = departmentController;
    }

    public void inputEmployee() throws SQLException {
        Employee employee = new Employee();
        LocalDate birthday = validBirthday();
        employee.setBirthday(birthday);
        LocalDate employmentDate = validEmploymentDate(birthday.minusYears(16));
        employee.setEmployment_data(employmentDate);
        System.out.println("Enter dataOfDismissal");
        LocalDate dataOfDismissal = addDateOfDismissal(employmentDate);
        employee.setData_of_dismissal(dataOfDismissal);
        System.out.println("Enter FirstName");
        String firstName = InputValue.inputValidateStr();
        employee.setFirstName(firstName);
        System.out.println("Enter LastName");
        String lastName = InputValue.inputValidateStr();
        employee.setLastName(lastName);
        System.out.println("Enter INN");
        String inn = InputValue.inputINN();
        employee.setInn(inn);
        System.out.println("Enter phone-Number");
        String phoneNumber = InputValue.inputPhoneNumber();
        employee.setPhone_number(phoneNumber);
        System.out.println("Enter Position");
        String position = InputValue.inputValidateStr();
        employee.setPosition(position);
        Department department = addDepartmentToEmployee();
        employee.setDepartment(department);
        Address address = addAddressToEmployee();
        employee.setAddress(address);

        employeeService.add(employee);

    }

    private LocalDate addDateOfDismissal(LocalDate date) {

        if (isDataOfDismmissal()) {
            return validDataOfDismissal(date);
        }
        return null;
    }

    private Department addDepartmentToEmployee() throws SQLException {
        System.out.println("Input Title Department: ");
        String title = InputValue.inputValidateStr();
        Department department = departmentController.searchTitle(title);
        System.out.println(department);
        if (department == null) {
            return departmentController.inputDepartment();
        } else {
            return (department);
        }
    }

    private Address addAddressToEmployee() throws SQLException {
        System.out.println("Input id Address: ");
        Long id = InputValue.inputValidateLong();
        Address address = addressController.searchId(id);
        if (address == null) {
            return addressController.inputAddress();
        } else {
            return address;
        }

    }

    public void deleteEmployee() throws SQLException {
        String inn = InputValue.inputINN();
        Employee employee = searchINN(inn);
        if (employee != null) {
            employeeService.remove(employee);
            System.out.println("Employee successfully deleted!");
        } else {
            System.out.println("Employee is not excite");
        }

    }

    public void editeEmployee() throws SQLException {
        String inn = InputValue.inputINN();
        Employee employee = searchINN(inn);
        if (employee != null) {
            cycleChoiceNameTable(employee);
            employeeService.update(employee);
            System.out.println("Employee successfully update!");
            System.out.println();
        } else {
            System.out.println("NoT");
        }
    }

    private void selectNameTable(Employee employee) throws SQLException {
        EmployeeColumns name = InputValue.inputEmployeeItem();
        switch (name) {
            case BIRTHDAY -> employee.setBirthday(validBirthday());
            case DATA_OF_DISMISSAL ->
                    employee.setData_of_dismissal(validDataOfDismissal(employee.getEmployment_data()));
            case EMPLOYMENT_DATA -> employee.setEmployment_data(validEmploymentDate(employee.getBirthday()));
            case FIRST_NAME -> employee.setFirstName(InputValue.inputValidateStr());
            case LAST_NAME -> employee.setLastName(InputValue.inputValidateStr());
            case INN -> employee.setInn(InputValue.inputINN());
            case PHONE_NUMBER -> employee.setPhone_number(InputValue.inputPhoneNumber());
            case POSITION -> employee.setPosition(InputValue.inputValidateStr());
            case DEPARTMENT_ID -> employee.setDepartment(addDepartmentToEmployee());
            case ADDRESS_ID -> employee.setAddress(addAddressToEmployee());

        }
    }

    private void cycleChoiceNameTable(Employee employee) throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Employee, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectNameTable(employee);
            cycleChoiceNameTable(employee);
        } else {
            System.out.println("Element unchanged!");
        }
    }


    public Employee searchINN(String str) throws SQLException {
        List<Employee> employees = employeeService.getAll();

        for (Employee employee : employees) {
            String inn = employee.getInn();

            if (checkInnFinal(inn, str)) {
                System.out.println(employee);
                return employee;
            }
        }

        return null;
    }

    private String checkEqualsINN(String inn, String str) {
        return (!Objects.equals(inn, str)) ? null : inn;
    }

    private boolean checkInnFinal(String inn, String str) {
        return checkEqualsINN(inn, str) != null;
    }

    private Boolean isDataOfDismmissal() {
        String switcher = "Y";
        System.out.println("Enter Y if the employee is fired or N if not fired");
        return InputValue.inputYesOrNot().equals(switcher);
    }

    private LocalDate validEmploymentDate(LocalDate localDate) {
        System.out.println("Enter employmentDate");
        LocalDate employmentDate = InputValue.inputDate();
        if (!employmentDate.isAfter(localDate)) {
            System.out.println("The date of employment cannot be less than the date of birth + 16 years");
            return validEmploymentDate(localDate);
        } else {

            return employmentDate;
        }
    }

    private LocalDate validDataOfDismissal(LocalDate localDate) {
        System.out.println("Enter Data of dismissal");
        LocalDate dataOfDismissal = InputValue.inputDate();
        if (dataOfDismissal.isBefore(localDate)) {
            System.out.println("The date of dismissal cannot be less than the date of employment");
            return validDataOfDismissal(localDate);
        } else {

            return dataOfDismissal;
        }
    }

    private LocalDate validBirthday() {
        System.out.println("Enter Birthday");
        LocalDate birthday = InputValue.inputDate();
        LocalDate date1 = birthday.plusYears(16);

        LocalDate dateNow = LocalDate.now();
        if (dateNow.isBefore(date1)) {
            System.out.println("Less than legal age for employment.");
            System.out.println("If this is an input error, try entering data again");
            System.out.println("Enter Y or N");
            String yn = InputValue.inputYesOrNot();
            if (yn.equals("N")) {
                System.exit(0);
                return null;
            }
            return validBirthday();
        } else {
            return birthday;
        }
    }

    public void selectAllEmployee() throws SQLException {
        System.out.println(employeeService.getAll());
    }
}

