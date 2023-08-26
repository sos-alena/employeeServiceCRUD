package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Department;
import entityController.AddressController;
import entityController.DepartmentController;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;
import validator.validationCountry;

import java.sql.SQLException;
import java.util.Locale;

import static validator.InputValue.inputPostCodeValue;
import static validator.InputValue.inputValidateStr;

public class Start {

    public static void main(String[] args) throws SQLException {
        /*AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();

        Department department = departmentService.getById(1L);
        department.setTitle("Economic");
        departmentService.update(department);


        AddressController addressController = new AddressController();
        Address address = addressController.inputAddress();
        System.out.println(address);*/
        DepartmentController departmentController = new DepartmentController();

        departmentController.deleteDepartment();

        HibernateUtil.shutdown();



    }
}
