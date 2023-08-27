package entityController;

import entity.Address;
import entity.Employee;
import listEntity.AddressItem;
import service.AddressService;
import service.EmployeeService;

import java.sql.SQLException;
import java.util.List;

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
        Address address = searchId(id);
        if (address == null) {
            System.out.println("Address with this ID does not exist.");
        } else {
            System.out.println(address);
            int counter = checkIdAddressInEmployee(id);
          if (counter == 0) {
                addressService.remove(address);
                System.out.println("Address successfully delete!");

            } else {
            System.out.println("Unable to delete address with id because " +
                    "it is associated with the table Employee) ");
          }
        }
    }

    public void editeAddress() throws SQLException {
        System.out.println("Input number ID: ");
        Long id = inputValidateLong();
       Address address = searchId(id);
        if (address == null) {
            System.out.println("Address with this ID does not exist.");

        } else {
            System.out.println(address);
            cycleChoiceNameTable(address);
            addressService.update(address);
            System.out.println("Address successfully update!");

        }

    }

    private void selectNameTable(Address address) {
        AddressItem name = inputAddressItem();
        switch (name) {
            case COUNTRY -> address.setCountry(getNameCountry());
            case TOWN -> address.setTown(inputValidateStr());
            case STREET -> address.setStreet(inputValidateStr());
            case POST_CODE -> address.setPostCode(inputPostCodeValue());
        }
    }

    private void cycleChoiceNameTable(Address address) {
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

    private int checkIdAddressInEmployee(Long id){
        EmployeeService employeeService = new EmployeeService();
        List<Employee> employees = null;
        try {
            employees = employeeService.getAll();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

        int temp = 0;
        for (Employee item: employees) {
            Address address = item.getAddress();
            if (!(address == null)) {
                Long ida = address.getId();
                if(id.equals(ida)) {

                    temp++;
                }
            }
        }
         return temp;
    }

    public Address searchId(Long id) {
        List<Address> addresses;
        try {
            addresses = addressService.getAll();
            for (Address item : addresses) {
                Long ida = item.getId();
                if (ida.equals(id)) {
                    System.out.println("Success! This id exists");
                    return item;

                }
            }
          } catch ( SQLException e) {
            System.out.println("Error " + e );
        }
        return null;
    }
    public void selectAllAddress() throws SQLException {
        System.out.println(addressService.getAll());
    }
}

