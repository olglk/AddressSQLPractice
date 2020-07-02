package com.example.demo.repository;

import com.example.demo.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    @Query(value = "SELECT country FROM adress  WHERE country LIKE %?1%", nativeQuery = true)
    public List<String> getCountry(String country);

    @Query("SELECT a.country FROM Address a WHERE a.country LIKE %?1%")
    public List<String> getCountryJPQL(String country);

    @Query(value = "SELECT COUNT(DISTINCT country) FROM adress AS countofcountries", nativeQuery = true)
    public int distinctCountries();

    @Query(value = "SELECT * FROM adress WHERE country LIKE '%e' AND postal_code < 20000", nativeQuery = true)
    public List<String> getCountryEndsE();


    // FUNCTION FIND FIRST N ADRESSES (IN)
    @Query(value = "CALL `first_n_adresses`(:val);", nativeQuery = true)
    public List<Address> firstNAdresses(int val);

    @Procedure(name = "Adress.findNAdressNamedQuery")
    public List<Address> findNAdressNamedQuery(@Param("n") int n);

    //FUNCTION COUNT OF COUNTRIES (OUT)
    @Procedure(procedureName = "count_of_countries", outputParameterName = "count")
    public int countOftCountries();

    @Procedure(name = "Adress.countOfCountriesNamedQuery")
    public int countOfCountriesNamedQuery();

    //FUNCTION FIND ADRESS BY ID (IN)
    @Query(nativeQuery = true)
    public List<Address> findAdressById(@Param("id") int id);

    //FUNCTION COUNT OF CITIES
    @Query(nativeQuery = true)
    public int countOfCities();


}
