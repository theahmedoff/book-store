package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
public class Category {
    private int idCategory;
    private String type;
    private List<Book> list;

    public Category(int idCategory, String type, List<Book> list) {
        this.idCategory = idCategory;
        this.type = type;
        this.list = new ArrayList<>();
    }

    public Category() {
        this.list = new ArrayList<>();
    }
}
