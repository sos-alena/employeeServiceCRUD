package employee.service.crud.service;

import employee.service.crud.models.Department;
import employee.service.crud.repositories.DepartmentRepository;
import employee.service.crud.repositories.DepartmentRepositoryIml;
import java.sql.SQLException;
import java.util.List;

public class DepartmentService {

    DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepositoryIml departmentRepositoryIml) {
        this.departmentRepository = departmentRepositoryIml;
    }

    public void add(Department department) throws SQLException {
        departmentRepository.add(department);
    }

    public List<Department> getAll() throws SQLException {
        return departmentRepository.getAll();
    }

    public Department getById(Long id) throws SQLException {
        return departmentRepository.getById(id);
    }

    public void update(Department department) throws SQLException {
        departmentRepository.update(department);
    }

    public void remove(Department department) throws SQLException {
        departmentRepository.remove(department);
    }
}
