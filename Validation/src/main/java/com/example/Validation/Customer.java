package com.example.Validation;

import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    @NotNull(message = "is Required")
    @Size(min = 1, message = "is Required")
    private String lastName = "";

    @NotNull(message = "is Required")
    @Min(value = 0, message = "must be greater equal than 0")
    @Max(value = 10, message = "must be less than equal to 10")
    private Integer freePasses;

    @Pattern(regexp = "^[a-zA-Z0-9]{5}", message = "only 5 char/digits")
    private String postal;

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
