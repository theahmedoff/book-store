package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {

    private double minPrice;
    private double maxPrice;
    private double minAge;
    private double maxAge;

}
