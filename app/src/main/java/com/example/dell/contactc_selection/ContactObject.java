package com.example.dell.contactc_selection;

import java.io.Serializable;

/**
 * Created by DELL on 25/11/2015.
 */
public class ContactObject implements Serializable {

    private static final long serialVersionUID=1L;
    private String name;
    private String phone;
    private String website;



    public ContactObject(String name, String phone, String website) {
        super();
        this.name = name;
        this.phone = phone;
        this.website = website;
    }

    public String getName() {
        return this.name;
    }

    public String getPhone() {
        return  this.phone;
    }

    public String getWebsite() {
        return this.website;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setWebsite(String website) {
        this.website = website;
    }
}

