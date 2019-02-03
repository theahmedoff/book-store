package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class Book {
    private int idBook;
    private String title;
    private String desc;
    private int quantity;
    private String imagePath;
    private String language;
    private LocalDate writeDate;
    private Author author;
    private List<Category> list;

    public Book(int idBook, String title, String desc, int quantity, String imagePath, String language, LocalDate writeDate, Author author, List<Category> list) {
        this.idBook = idBook;
        this.title = title;
        this.desc = desc;
        this.quantity = quantity;
        this.imagePath = imagePath;
        this.language = language;
        this.writeDate = writeDate;
        this.author = author;
        this.list = new ArrayList<>();
    }

    public Book() {
        this.list = new ArrayList<>();
    }
}
