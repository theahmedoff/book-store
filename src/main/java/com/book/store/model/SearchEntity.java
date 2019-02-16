package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchEntity {

    private String[] categories;
    private double minPrice;
    private double maxPrice;
    private int minAge;
    private int maxAge;

    //methods
    public void parseAndSetPriceRange(String priceRange){
        if (priceRange!=null) {
            String[] prices = priceRange.split("-");
            this.minPrice = Double.parseDouble(prices[0]);
            this.maxPrice = Double.parseDouble(prices[1]);
        }else {
            this.minPrice=10;
            this.maxPrice=500;
        }
    }

    public void parseAndSetAgeRange(String ageRange){
        if (ageRange!=null) {
            String[] ages = ageRange.split("-");
            this.minAge = Integer.parseInt(ages[0]);
            this.maxAge = Integer.parseInt(ages[1]);
        }else {
            this.minAge=1;
            this.maxAge=99;
        }
    }

    //getter and setters
    public void setCategories(String[] categories) {
        this.categories = categories;
    }

    public String[] getCategories() {
        return categories;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public double getMaxPrice() {
        return maxPrice;
    }

    public int getMinAge() {
        return minAge;
    }

    public int getMaxAge() {
        return maxAge;
    }

}
