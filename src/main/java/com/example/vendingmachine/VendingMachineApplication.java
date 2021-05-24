package com.example.vendingmachine;

import com.example.vendingmachine.service.CategoryService;
import com.example.vendingmachine.service.PurchaseService;
import com.example.vendingmachine.validation.ConsoleInputValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Slf4j
@SpringBootApplication
public class VendingMachineApplication implements CommandLineRunner {

    private final CategoryService categoryService;
    private final PurchaseService purchaseService;

    public VendingMachineApplication(CategoryService categoryService,
                                     PurchaseService purchaseService) {
        this.categoryService = categoryService;
        this.purchaseService = purchaseService;
    }

    public static void main(String[] args) {
        SpringApplication.run(VendingMachineApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in).useDelimiter("\n");

        while (true) {
            printMenu();
            System.out.print("> ");
            String userInput = scanner.next();
            List<String> userInputString = Arrays.stream(userInput.split("\\s(?=(?:[^'\"`]*(['\"`])[^'\"`]*\\1)*[^'\"`]*$)")).map(String::trim).map(str -> str.replace("\"", "")).collect(Collectors.toList());

            Map<String, Consumer<List<String>>> commands = new HashMap<>();
            commands.put("addCategory", categoryService::addCategory);
            commands.put("addItem", categoryService::addItem);
            commands.put("purchase", purchaseService::purchaseItem);
            commands.put("list", categoryService::list);
            commands.put("clear", categoryService::clear);
            commands.put("report", purchaseService::report);
            commands.put("exit", exitString -> System.exit(0));

            if (ConsoleInputValidator.containsCommandInputValidation(userInputString.get(0))) {
                commands.get(userInputString.get(0)).accept(userInputString);
            } else {
                System.out.println("\nPlease enter correctly option!\n");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n======Console Vending Machine Menu======\n");
        System.out.println("| Number\t| Command\n");
        System.out.println("|[1]\t| - addCategory");
        System.out.println("|[2]\t| - addItem");
        System.out.println("|[3]\t| - purchase");
        System.out.println("|[4]\t| - list");
        System.out.println("|[5]\t| - clear");
        System.out.println("|[6]\t| - report");
        System.out.println("|[7]\t| - exit");
        System.out.println("\n========Enter Your Menu Choice=========");
    }
}