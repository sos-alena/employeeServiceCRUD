package domain;

import bl.HibernateUtil;
import entity.Address;
import entity.Department;
import entity.Employee;
import entityController.AddressController;
import entityController.DepartmentController;
import entityController.EmployeeController;
import service.AddressService;
import service.DepartmentService;
import service.EmployeeService;
import validator.validationCountry;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import static bl.HibernateUtil.shutdown;
import static validator.InputValue.*;

public class Start {

    public static void main(String[] args) throws SQLException {

     EmployeeController employeeController = new EmployeeController();

     String str = inputValidateStr ();
    employeeController.searchINN(str);
        shutdown();

    }
}
