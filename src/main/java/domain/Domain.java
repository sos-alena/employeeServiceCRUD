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

import static bl.HibernateUtil.shutdown;

public class Domain {
static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws SQLException, IOException {


        AddressService addressService = new AddressService();
        EmployeeService employeeService = new EmployeeService();
        DepartmentService departmentService = new DepartmentService();


        shutdown();
    }
}
