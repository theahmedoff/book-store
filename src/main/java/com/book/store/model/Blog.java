package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Blog {

    private int idBlog;
    private String title;
    private String desc;
    private LocalDateTime shareDate;
    private String imagePath;
    private User user;

}
