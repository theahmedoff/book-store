package com.book.store.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillingInfo {

    private int idBillingInfo;
    private String firstname;
    private String lastname;
    private String companyName;
    private String country;
    private String address;
    private String postcode;
    private String phone;
    private String email;
    private User user;

    public BillingInfo(User user) {
        this.firstname = "";
        this.lastname = "";
        this.companyName = "";
        this.country = "";
        this.address = "";
        this.postcode = "";
        this.phone = "";
        this.email = "";
        this.user = user;
    }
}
