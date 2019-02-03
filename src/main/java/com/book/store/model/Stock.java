package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    private int idStock;
    private int quantity;
    private double price;
    private LocalDateTime lastAddedDate;
    private Book book;

}
