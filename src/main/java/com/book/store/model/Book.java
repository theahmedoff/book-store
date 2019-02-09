package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Book {

    private int idBook;
    private String title;
    private String desc;
    private String imagePath;
    private String language;
    private LocalDate writeDate;
    private Author author;
    private List<Category> list;
    private Stock stock;


    public Book() {
        this.list = new ArrayList<>();
    }

    public void addCategory(Category category) {
        list.add(category);
    }

}
