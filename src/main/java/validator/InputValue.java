package validator;

import listEntity.Action;
import listEntity.AddressItem;
import listEntity.EmployeeItem;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static listEntity.Action.*;
import static listEntity.AddressItem.*;
import static listEntity.EmployeeItem.*;

@Slf4j
public class InputValue {
    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    static Pattern patternPostCode = Pattern.compile("^\\d{5,6}");
    static Pattern patternYesOrNot = Pattern.compile("[YN]");
    static Pattern patternPhone = Pattern.compile("\\+38-\\d{3}-\\d{3}-\\d{2}-\\d{2}");
    static Pattern patternINN = Pattern.compile("^\\d{12}");
    public static String inputYesOrNot() {
        String str;
        try {
            str = READER.readLine();
            if (validateData(str, patternYesOrNot)) {
                System.out.println("Character is valid");
                return str;
            }
            log.info("Character is invalid. Try again");
            return inputYesOrNot();
        } catch (IOException e) {
            log.info("Error! incorrect number");
            return inputYesOrNot();
        }
    }

    public static String inputPhoneNumber() {
        System.out.println("Input number. Format example: +38-XXX-XXX-XX-XX");
        String number = null;
        try {
            number = READER.readLine();

            if (validateData(number, patternPhone)) {
                System.out.println("Number is valid");
                return number;
            }
            System.out.println("Number is invalid.\nTry again");
            return inputPhoneNumber();

        } catch (IOException e) {
            System.out.println("Error: " + e + "!! Input phone again");
            return inputPhoneNumber();
        }
    }

    public static long inputValidateLong() {
            try {
                long number = Long.parseLong(READER.readLine());
                if (number > 0) {
                    return number;
                }
                log.info("Incorrect value: " + number);
                System.out.println("Enter value again: ");
                throw new Exception();
            } catch (Exception exception) {
                log.info("Incorrect value: " + exception.getMessage());
                System.out.println("Enter value again: ");
                return inputValidateLong();
            }
        }


        public static String inputValidateStr () {

            try {
                String str = READER.readLine().trim();
                while (str.isEmpty()) {
                    log.info("Empty.");
                    System.out.println("Enter value again: ");
                    str = READER.readLine();
                }
                return str;
            } catch (IOException e) {
                log.info("Error! " + e);
                return inputValidateStr();
            }
        }


        public static Action inputAction () {
            try {
                System.out.println("Input the item from the list: "
                        + PRINT + ", "
                        + REMOVE + ", "
                        + INSERT + ", " +
                        UPDATE + ", ");
                Action item = Action.valueOf(READER.readLine());
                System.out.println("Item is valid");
                return item;
            } catch (Exception exception) {
                log.info("Incorrect value type. ");
                System.out.println("Enter value again: ");
                return inputAction();
            }
        }


        public static AddressItem inputAddressItem () {
            try {
                System.out.println("Input the item from the list: "
                        + COUNTRY + ", "
                        + TOWN + ", "
                        + STREET + ", " +
                        POST_CODE + ", ");
                AddressItem item = AddressItem.valueOf(READER.readLine());
                System.out.println("Item is valid");
                return item;
            } catch (Exception exception) {
                log.info("Incorrect value type. ");
                System.out.println("Enter value again: ");
                return inputAddressItem();
            }
        }

    public static EmployeeItem inputEmployeeItem() {
        try {
            System.out.println("Input the item from the list: "
                    + BIRTHDAY + ", "
                    + BIRTHDAY + ", "
                    + BIRTHDAY + ", " +
                    FIRST_NAME + ", "
            + LAST_NAME + ", "
            + INN + ", "
            + PHONE_NUMBER + ", "
            + PHONE_NUMBER + ", "
            + DEPARTMENT_ID + ", "
            + DEPARTMENT_ID);
            EmployeeItem item =EmployeeItem.valueOf(READER.readLine());
            System.out.println("Item is valid");
            return item;
        } catch (Exception exception) {
            log.info("Incorrect value type. ");
            System.out.println("Enter value again: ");
            return inputEmployeeItem();
        }
    }

    public static String inputPostCodeValue() {
        System.out.println("Enter POST_CODE: ");
        String postCode;
        try {
            postCode = inputValidateStr ();
            if (validateData(postCode, patternPostCode)) {
                System.out.println("PostCode is valid");
                return postCode;
            }
            System.out.println("PostCode is invalid. " +
                    "Check the PostCode.\nTry again");
                log.info("PostCode is invalid. " +
                        "Check the PostCode.\nTry again");
                return inputPostCodeValue();

        } catch (NullPointerException e) {
            log.info("error! incorrect PostCode" + e);
            return inputPostCodeValue();
        }
    }

    public static String inputINN() {
        System.out.println("Enter INN only numbers in the amount of 12 pieces: ");
        String inn;
        try {
            inn = inputValidateStr ();
            if (validateData(inn, patternINN)) {
                System.out.println("inn is valid");
                return inn;
            }
            System.out.println("INN is invalid. " +
                    "Check the INN.\nTry again");
            log.info("inn is invalid. " +
                    "Check the INN.\nTry again");
            return inputINN();

        } catch (NullPointerException e) {
            log.info("error! incorrect INN" + e);
            return inputINN();
        }
    }
        public static int inputYear () {
            try {
                int number = Integer.parseInt(READER.readLine());
                if (number > 2000 && number < 2023) {
                    return number;
                }
                log.info("Incorrect value: " + number);
                System.out.println("Enter value again: ");
                throw new Exception();
            } catch (Exception exception) {
                log.info("Incorrect value: " + exception.getMessage());
                System.out.println("Enter value again: ");
                return (int) inputValidateLong();
            }
        }
    public static LocalDate inputDate() {
        try {
            System.out.println("Input year: ");
            int year = inputYear();
            System.out.println("Input month: ");
            int month = createDataTime(1, 12);
            System.out.println("Input data: ");
            int data = createDataTime(1, createDayMonth(month));

            LocalDate date = LocalDate.of(year, month, data) ;
            LocalDate dateTimeNow = LocalDate.now();

            if(date.isBefore(dateTimeNow)
                    || date.isEqual(dateTimeNow)) {
                System.out.println("dateTime: " + date);
                return date;
            }
            System.out.println("Error! Input data more then current_date, try again.");
            return inputDate();

        } catch (Exception exception) {
            log.info("Incorrect value: " + exception.getMessage());
            System.out.println("Enter value again: ");
            return inputDate();
        }
    }

    private static int createDataTime(int min, int max){
        try{
            int number = Integer.parseInt(READER.readLine());
            if(number >= min && number <= max){
                return number;
            }
            log.info("Incorrect value: " + number);
            createDataTime(min, max);

        } catch (IOException e) {
            log.info("Incorrect value: " + e.getMessage());
            System.out.println("Enter value again: ");
        }
        return createDataTime(min, max);
    }

    public static int createDayMonth(int month){
        int maxDay;

        if (month == 2) {
            maxDay = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            maxDay = 30;
        } else {
            maxDay = 31;
        }
        return maxDay;
    }

        public static boolean validateData (String name, Pattern pattern){
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        }
    }


