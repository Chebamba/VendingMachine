package com.example.vendingmachine.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private double price;

    private int amount;

    public Category(String name, double price, int amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
}