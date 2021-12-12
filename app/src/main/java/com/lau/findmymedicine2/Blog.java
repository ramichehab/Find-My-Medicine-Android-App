package com.lau.findmymedicine2;

public class Blog {

    public String medicineName;
    public String Quantity;
    public String ContactInfo;

    public Blog() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Blog(String medicineName,String Quantity,String ContactInfo) {
        this.medicineName = medicineName;
        this.Quantity = Quantity;
        this.ContactInfo = ContactInfo;
    }
}
