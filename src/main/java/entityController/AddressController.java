package entityController;

import entity.Address;
import entity.Employee;
import listEntity.AddressItem;
import service.AddressService;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static listEntity.AddressItem.*;
import static validator.InputValue.*;
import static validator.validationCountry.getNameCountry;

public class AddressController {

    AddressService addressService = new AddressService();

    public Address inputAddress() throws SQLException {
        Address address = new Address();
        System.out.println("Enter country");
        String country = getNameCountry();
        address.setCountry(country);
        System.out.println("Enter town");
        String town = inputValidateStr();
        address.setTown(town);
        System.out.println("street");
        String street = inputValidateStr();
        address.setStreet(street);
        System.out.println("postCode");
        String postCode = inputPostCodeValue();
        address.setPostCode(postCode);

        addressService.add(address);

        return address;
    }

    public void deleteAddress() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
        if (searchId(id)) {
            Address address = addressService.getById(id);
            addressService.remove(address);
            System.out.println("Address successfully deleted!");
        }
        System.out.println("Address with this ID does not exist.");

    }

    public void editeAddress() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
        if (searchId(id)) {
            Address address = addressService.getById(id);
            System.out.println(address);
            cycleChoiceNameTable(address);
            addressService.update(address);
            System.out.println("Address successfully update!");
        } else {
            System.out.println("Address with this ID does not exist.");
        }

    }

    private void selectNameTable(Address address) throws SQLException {
        AddressItem name = inputAddressItem();
        switch (name) {
            case COUNTRY -> address.setCountry(getNameCountry());
            case TOWN -> address.setTown(inputValidateStr());
            case STREET -> address.setStreet(inputValidateStr());
            case POST_CODE -> address.setPostCode(inputPostCodeValue());
        }
    }

    private void cycleChoiceNameTable(Address address) throws SQLException {
        String n = "N";
        System.out.println("To continue working with the Address, enter Y or N");
        String str = inputYesOrNot();
        if (!n.equals(str)) {
            selectNameTable(address);
            cycleChoiceNameTable(address);
        } else {
            System.out.println("Element unchanged!");
        }
    }

    private static void checkId(Long id) throws SQLException {
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = employeeService.getAll();
        for (Employee item : employees) {
            Address address = item.getAddress();
            Long ida = address.getId();
            if (Objects.equals(id, ida)) {
                System.out.println("Yes! The current Id is an external key for the table Employee");
                System.out.println(item);


            } else {
                System.out.println("No such ID exists");
            }
        }
    }

    private static Boolean searchId(Long id) throws SQLException {
        AddressService addressService = new AddressService();
        List<Address> addresses = addressService.getAll();
        for (Address item:addresses){
            Long ida = item.getId();
            if(ida.equals(id)){
                System.out.println("Success! This id exists");
                return true;
            }

            }
          return false;
        }

}

