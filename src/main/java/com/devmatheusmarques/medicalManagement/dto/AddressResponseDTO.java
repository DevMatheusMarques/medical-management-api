package com.devmatheusmarques.medicalManagement.dto;

import jakarta.validation.constraints.Pattern;

public class AddressResponseDTO {
    @Pattern(regexp = "^[0-9]{8}$")
    private String zipCode;
    private String street;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;

    public AddressResponseDTO() {
    }

    public AddressResponseDTO(String zipCode, String street, String complement, String neighborhood, String city, String state) {
        this.zipCode = zipCode;
        this.street = street;
        this.complement = complement;
        this.neighborhood = neighborhood;
        this.city = city;
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
