package com.example.demo.service;

import com.example.demo.exception.AddressException;
import com.example.demo.model.Address;
import com.example.demo.model.AddressCountryOnly;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AddressDAO {

    Address create(Address address);

    List<Address> readAll();

    Optional<Address> read(int id);

    Address update(Address address, int id) throws AddressException;

    void delete(int id) throws AddressException;

    public int distinctCountries();

    public List<String> getCountry(String country);

    public List<String> getCountryEndsE();

    public Address updatePatch(AddressCountryOnly addressCountryOnly, int id) throws AddressException;

    public Address updatePatchMap(Map<String, String> map, int id) throws AddressException;

    public List<Address> firstNAdresses(int val);

    public int countOfCountries();

    public List<Address> findNAdressNamedQuery(int n);

    public List<Address> findAdressById(int id);

    public int countOfCountriesNamedQuery();

    public int countOfCities();
}
