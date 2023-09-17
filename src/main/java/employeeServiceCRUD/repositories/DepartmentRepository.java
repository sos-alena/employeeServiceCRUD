package employeeServiceCRUD.repositories;

import employeeServiceCRUD.models.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentRepository {
    void add(Department department) throws SQLException;

    List<Department> getAll() throws SQLException;

    Department getById(Long id) throws SQLException;

    void update(Department department) throws SQLException;
    void remove(Department department) throws SQLException;
}
