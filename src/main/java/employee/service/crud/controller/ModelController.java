package employee.service.crud.controller;

import employee.service.crud.controller.validator.InputValue;
import employee.service.crud.controller.enums.Actions;
import employee.service.crud.controller.enums.Models;

import java.sql.SQLException;

import static employee.service.crud.repositories.bl.HibernateUtil.shutdown;

public class ModelController {
    AddressController addressController;
    DepartmentController departmentController;
    EmployeeController employeeController;

    public ModelController(AddressController addressController, DepartmentController departmentController, EmployeeController employeeController) {
        this.addressController = addressController;
        this.departmentController = departmentController;
        this.employeeController = employeeController;
    }

    public void app() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the APP, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectEntity();
            app();
        } else {
            System.out.println("Element unchanged!");
        }
        shutdown();
    }

    private void selectEntity() throws SQLException {
        Models name = InputValue.inputEntity();
        switch (name) {
            case ADDRESS -> handleChoiceActionAddress();
            case EMPLOYEE -> handleChoiceActionEmployee();
            case DEPARTMENT -> handleChoiceActionDepartment();
        }
    }

    private void selectActionAddress() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> addressController.selectAllAddress();
            case REMOVE -> addressController.deleteAddress();
            case INSERT -> addressController.addAddress();
            case UPDATE -> addressController.editeAddress();
        }
    }

    private void handleChoiceActionAddress() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Address, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionAddress();
            handleChoiceActionAddress();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionDepartment() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> departmentController.selectAllDepartment();
            case REMOVE -> departmentController.deleteDepartment();
            case INSERT -> departmentController.addDepartment();
            case UPDATE -> departmentController.editeDepartment();
        }
    }

    private void handleChoiceActionDepartment() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Department, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionDepartment();
            handleChoiceActionDepartment();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionEmployee() throws SQLException {
        Actions name = InputValue.inputAction();
        switch (name) {
            case PRINT -> employeeController.selectAllEmployee();
            case REMOVE -> employeeController.deleteEmployee();
            case INSERT -> employeeController.addEmployee();
            case UPDATE -> employeeController.editeEmployee();
        }
    }

    private void handleChoiceActionEmployee() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Employee, enter Y or N");
        String str = InputValue.inputYesOrNot();
        if (!n.equals(str)) {
            selectActionEmployee();
            handleChoiceActionEmployee();
        } else {
            System.out.println("Element unchanged!");
        }
    }
}
