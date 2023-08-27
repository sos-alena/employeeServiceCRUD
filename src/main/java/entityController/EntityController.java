package entityController;

import entity.Address;
import listEntity.Action;
import listEntity.AddressItem;
import listEntity.ListEntity;

import java.sql.SQLException;

import static listEntity.Action.*;
import static validator.InputValue.*;
import static validator.InputValue.inputYesOrNot;
import static validator.validationCountry.getNameCountry;

public class EntityController {
    AddressController addressController = new AddressController();
    DepartmentController departmentController = new DepartmentController();
    EmployeeController employeeController = new EmployeeController();


    public void selectEntity() throws SQLException {
        ListEntity name = inputEntity();
        switch (name) {
            case ADDRESS -> cycleChoiceActionAddress();
            case EMPLOYEE -> cycleChoiceActionEmployee();
            case DEPARTMENT -> cycleChoiceActionDepartment();
           }
    }

    private void selectActionAddress() throws SQLException {
        Action name = inputAction();
        switch (name) {
            case PRINT -> addressController.selectAllAddress();
            case REMOVE -> addressController.deleteAddress();
            case INSERT -> addressController.inputAddress();
            case UPDATE -> addressController.editeAddress();
        }
    }

    private void cycleChoiceActionAddress() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Address, enter Y or N");
        String str = inputYesOrNot();
        if (!n.equals(str)) {
            selectActionAddress();
            cycleChoiceActionAddress();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionDepartment() throws SQLException {
        Action name = inputAction();
        switch (name) {
            case PRINT -> departmentController.selectAllDepartment();
            case REMOVE -> departmentController.deleteDepartment();
            case INSERT -> departmentController.inputDepartment();
            case UPDATE -> departmentController.editeDepartment();
        }
    }

    private void cycleChoiceActionDepartment() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Department, enter Y or N");
        String str = inputYesOrNot();
        if (!n.equals(str)) {
            selectActionDepartment();
            cycleChoiceActionDepartment();
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private void selectActionEmployee() throws SQLException {
        Action name = inputAction();
        switch (name) {
            case PRINT -> employeeController.selectAllEmployee();
            case REMOVE -> employeeController.deleteEmployee();
            case INSERT -> employeeController.inputEmployee();
            case UPDATE -> employeeController.editeEmployee();
        }
    }

    private void cycleChoiceActionEmployee() throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Employee, enter Y or N");
        String str = inputYesOrNot();
        if (!n.equals(str)) {
            selectActionEmployee();
            cycleChoiceActionEmployee();
        } else {
            System.out.println("Element unchanged!");
        }
    }

}
