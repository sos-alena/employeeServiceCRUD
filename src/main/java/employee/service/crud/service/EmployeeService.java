package employee.service.crud.service;

import employee.service.crud.models.Employee;
import employee.service.crud.repositories.EmployeeRepository;
import employee.service.crud.repositories.EmployeeRepositoryIml;
import java.sql.SQLException;
import java.util.List;

public class  EmployeeService {

    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepositoryIml employeeRepositoryIml) {
        this.employeeRepository = employeeRepositoryIml;
    }

    public void add(Employee employee) throws SQLException {
        employeeRepository.add(employee);
    }

    public List<Employee> getAll() throws SQLException {

        return employeeRepository.getAll();
    }

    public Employee getById(Long id) throws SQLException {

        return employeeRepository.getById(id);
    }

    public void update(Employee employee) throws SQLException {
        employeeRepository.update(employee);
    }
    public void remove(Employee employee) throws SQLException {
        employeeRepository.remove(employee);
    }
}
