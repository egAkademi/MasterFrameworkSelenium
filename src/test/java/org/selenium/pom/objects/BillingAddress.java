package org.selenium.pom.objects;

public class BillingAddress {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String posteCode;
    private String email;
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    private String state;

    public BillingAddress(){
    }
    public BillingAddress(String firstName, String lastName, String address, String city, String posteCode, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.posteCode = posteCode;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public BillingAddress setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public BillingAddress setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public BillingAddress setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getCity() {
        return city;
    }

    public BillingAddress setCity(String city) {
        this.city = city;
        return this;
    }

    public String getPosteCode() {
        return posteCode;
    }

    public BillingAddress setPosteCode(String posteCode) {
        this.posteCode = posteCode;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public BillingAddress setEmail(String email) {
        this.email = email;
        return this;
    }
}
