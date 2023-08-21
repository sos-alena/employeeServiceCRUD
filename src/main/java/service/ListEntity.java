package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public enum ListEntity {

    ADDRESS,
    EMPLOYEE,
    DEPARTMENT;

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static ListEntity inputAction() {
        try {
            System.out.println("Input the item from the list: "
                    + ADDRESS + ", " +
                    EMPLOYEE + ", " +
                    DEPARTMENT + ", ");
            ListEntity item = ListEntity.valueOf(READER.readLine());
            System.out.println("Item is valid");
            return item;
        } catch (Exception exception) {

            System.out.println("Enter value again: ");
            return inputAction();
        }
    }
}
