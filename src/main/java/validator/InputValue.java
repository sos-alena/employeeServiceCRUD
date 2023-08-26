package validator;

import listEntity.Action;
import listEntity.AddressItem;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static listEntity.Action.*;
import static listEntity.AddressItem.*;

@Slf4j
public class InputValue {
    static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));
    static Pattern patternPostCode = Pattern.compile("^\\d{5,6}");
    static Pattern patternYesOrNot = Pattern.compile("[YN]");
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
            System.out.println("Input value: ");
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

    public static String inputPostCodeValue() {
        System.out.println("Enter POST_CODE: ");
        String PostCode;
        try {
            PostCode = inputValidateStr ();
            if (validateData(PostCode, patternPostCode)) {
                System.out.println("PostCode is valid");
                return PostCode;
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

        public static boolean validateData (String name, Pattern pattern){
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();
        }
    }


