package employeeServiceCRUD.service;

import employeeServiceCRUD.models.Employee;
import employeeServiceCRUD.repositories.EmployeeRepositoryIml;

import java.sql.SQLException;
import java.util.List;

public class  EmployeeService {

    EmployeeRepositoryIml employeeRepositoryIml;


    public EmployeeService(EmployeeRepositoryIml employeeRepositoryIml) {
        this.employeeRepositoryIml = employeeRepositoryIml;
    }

    public void add(Employee employee) throws SQLException {
        employeeRepositoryIml.add(employee);
    }

    public List<Employee> getAll() throws SQLException {

        return employeeRepositoryIml.getAll();
    }

    public Employee getById(Long id) throws SQLException {

        return employeeRepositoryIml.getById(id);
    }


    public void update(Employee employee) throws SQLException {
        employeeRepositoryIml.update(employee);
    }

    public void remove(Employee employee) throws SQLException {
        employeeRepositoryIml.remove(employee);
    }
}
