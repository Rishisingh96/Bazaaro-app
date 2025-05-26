package com.rishi.modal;

import lombok.Data;
/*
Sure!
@Data is a Lombok annotation that automatically generates for your class:

✅ Getters (getField())

✅ Setters (setField(value))

✅ toString() method

✅ equals() and hashCode() methods

✅ A constructor for final fields
*/
@Data
public class BusinessDetails {

    private String businessName;

    private String businessEmail;

    private String businessMobile;

    private String logo;

    private String banner;




}
