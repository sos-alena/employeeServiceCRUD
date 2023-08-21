package dao;

import entity.Address;
import entity.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentDAO {
    void add(Department department) throws SQLException;

    List<Department> getAll() throws SQLException;

    Department getById(Long id) throws SQLException;

    void update(Department department) throws SQLException;
    void remove(Department department) throws SQLException;
}
