package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateField {
    public static boolean StudentIdCheck(String input) {
        if (input.matches("^(ST-)[0-9]{3}$")) {
            return true; // Input contains only numeric characters, including up to two decimal points
        } else {
            return false; // Input contains non-numeric characters or invalid decimal points
        }
    }

    public static boolean roomIdCheck(String input) {
        if (input.matches("^(RM-)[0-9]{3}$")) {
            return true; // Input contains only numeric characters, including up to two decimal points
        } else {
            return false; // Input contains non-numeric characters or invalid decimal points
        }
    }

    public static boolean ResevationIdCheck(String input) {
        if (input.matches("^(RE-)[0-9]{3}$")) {
            return true; // Input contains only numeric characters, including up to two decimal points
        } else {
            return false; // Input contains non-numeric characters or invalid decimal points
        }
    }

    public static boolean contactCheck(String contact){
        String contactRegex = "^(?:7|0|(?:\\\\\\\\+94))[0-9]{9,10}$";
        Pattern pattern = Pattern.compile(contactRegex);
        Matcher matcher = pattern.matcher(contact);
        return matcher.matches();
    }

}
