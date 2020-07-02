package com.example.demo.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Entity

/*@NamedStoredProcedureQuery(
        name = "Adress.findNAdressesNamedQuery", // имя метода который создаём
        procedureName = "find_n_adresses", // проуедура как в скл
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.IN, name = "val",
                type = Integer.class) }) // на каждый ин и аут отдельные аннотации

@NamedStoredProcedureQuery(
        name = "Adress.countOfCountriesNamedQuery",
        procedureName = "count_of_countries",
        parameters = {
                @StoredProcedureParameter(mode = ParameterMode.OUT, name = "count",
                type = Integer.class) })*/

@NamedNativeQueries({

        @NamedNativeQuery(name = "Adress.findAdressById",
                query = "SELECT * FROM adress WHERE adress.id = :id;",
                resultClass = Address.class),

        @NamedNativeQuery(name = "Adress.countOfCities",
                query = "SELECT COUNT(DISTINCT city) FROM adress")
})

@Table(name = "adress")
public class Address implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @NotEmpty(message = "Please enter your country")
    private String country;
    @Column
    private String city;
    @Column
    private String street;
    @Column
    private int postalCode;
    @Column
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    public Address() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(int postalCode) {
        this.postalCode = postalCode;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "id=" + id +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", postalCode=" + postalCode +
                '}';
    }
}
