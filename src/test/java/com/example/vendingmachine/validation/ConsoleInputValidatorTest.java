package com.example.vendingmachine.validation;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInputValidatorTest {
    @Test
    void when_containsCommandInputValidation_then_returnTrue() {
        String userInputCommand = "addCategory";

        boolean result = ConsoleInputValidator.containsCommandInputValidation(userInputCommand);

        assertTrue(result);
    }

    @Test
    void when_containsCommandInputValidation_then_returnFalse(){
     String userInputCommand = "123";

     boolean result = ConsoleInputValidator.containsCommandInputValidation(userInputCommand);

     assertFalse(result);
    }

    @Test
    void when_reportByMonthOrByDate_then_returnTrue() {
        String userInput = "2021-04-04";

        boolean result = ConsoleInputValidator.reportByMonthOrByDate(userInput);

        assertTrue(result);
    }

    @Test
    void when_reportByMonthOrByDate_then_returnFalse(){
        String userInput = "2021-04";

        boolean result = ConsoleInputValidator.reportByMonthOrByDate(userInput);

        assertFalse(result);
    }
}