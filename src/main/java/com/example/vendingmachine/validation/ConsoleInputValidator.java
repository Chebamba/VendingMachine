package com.example.vendingmachine.validation;

import java.util.Arrays;

public class ConsoleInputValidator {

    public static boolean containsCommandInputValidation(String userInput) {
        return Arrays.asList("addCategory", "addItem", "purchase", "list", "clear", "report", "exit").contains(userInput);
    }

    public static boolean reportByMonthOrByDate(String userInput) {
        return userInput.split("-").length - 1 == 2;
    }

}
