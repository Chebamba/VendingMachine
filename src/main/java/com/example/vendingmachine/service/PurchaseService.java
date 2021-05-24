package com.example.vendingmachine.service;

import com.example.vendingmachine.model.Category;
import com.example.vendingmachine.model.Purchase;
import com.example.vendingmachine.report.ReportQuantity;
import com.example.vendingmachine.repository.CategoryRepository;
import com.example.vendingmachine.repository.PurchaseRepository;
import com.example.vendingmachine.validation.ConsoleInputValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final CategoryRepository categoryRepository;

    public void report(List<String> input) {
        String[] dateParams = input.get(1).split("-");

        if (ConsoleInputValidator.reportByMonthOrByDate(input.get(1))) {
            LocalDate localDate = LocalDate.of(Integer.parseInt(dateParams[0]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[2]));
            displayReport(purchaseRepository.findAllByLocalDateAfter(localDate));
        } else {
            int year = Integer.parseInt(dateParams[0]);
            int month = Integer.parseInt(dateParams[1]);

            LocalDate startDate = LocalDate.of(year, month, 1);
            LocalDate endDate = LocalDate.of(year, month, startDate.getMonth().length(isLeapYear(year)));

            displayReport(purchaseRepository.getAllBetweenDates(startDate, endDate));
        }

    }

    public void purchaseItem(List<String> input) {
        String[] dateParams = input.get(2).split("-");
        LocalDate localDate = LocalDate.of(Integer.parseInt(dateParams[0]), Integer.parseInt(dateParams[1]), Integer.parseInt(dateParams[2]));

        Optional<Category> byName = categoryRepository.findByName(input.get(1));

        if (byName.isPresent()) {
            Category category = byName.get();
            if (category.getAmount() > 0) {
                category.setAmount(category.getAmount() - 1);
                categoryRepository.save(category);
                purchaseRepository.save(new Purchase(category, localDate));
            } else {
                System.out.println("Something went wrong. No items in category");
            }
        }
    }

    private void displayReport(List<Purchase> purchases) {

        List<ReportQuantity> reportQuantityList = convertPurchasesIntoDtoList(purchases);

        reportQuantityList.forEach(dto -> System.out.println(dto.getCategoryName() + " " + dto.getPrice() + " " + dto.getQuantity()));

        double sum = reportQuantityList.stream().mapToDouble(ReportQuantity::getTotal).sum();

        System.out.println("> Total " + sum);
    }

    private List<ReportQuantity> convertPurchasesIntoDtoList(List<Purchase> purchases) {
        Map<Category, List<Purchase>> purchasesByCategory = purchases.stream().collect(Collectors.groupingBy(Purchase::getCategory));

        return purchasesByCategory.entrySet()
                .stream()
                .map(purchaseByCategoryEntry ->
                        new ReportQuantity(purchaseByCategoryEntry.getKey().getName(),
                                purchaseByCategoryEntry.getKey().getPrice(),
                                purchaseByCategoryEntry.getValue().size(),
                                purchaseByCategoryEntry.getValue().size() * purchaseByCategoryEntry.getKey().getPrice()))
                .sorted(Comparator.comparing(ReportQuantity::getCategoryName))
                .collect(Collectors.toList());
    }

    private boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }
}
