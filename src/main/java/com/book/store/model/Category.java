package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class Category {

    private int idCategory;
    private String type;
    private List<Book> list;

    public Category() {
        this.list = new ArrayList<>();
    }

}
