package com.example.vendingmachine.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate localDate;

    @OneToOne
    private Category category;

    public Purchase(Category category, LocalDate localDate) {
        this.category = category;
        this.localDate = localDate;
    }
}
