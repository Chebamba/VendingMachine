package com.example.vendingmachine.report;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ReportQuantity {

    private String categoryName;
    private Double price;
    private Integer quantity;
    private Double total;
}
