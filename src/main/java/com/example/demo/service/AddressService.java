package com.example.demo.service;

import com.example.demo.exception.AddressException;
import com.example.demo.model.Address;
import com.example.demo.model.AddressCountryOnly;
import com.example.demo.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static com.example.demo.model.PropertyType.*;
import static com.example.demo.model.PropertyType.PRIVATE;

@Service
public class AddressService implements AddressDAO {

    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address create(Address address) {
        switch (address.getPropertyType()) {
            case PRIVATE:
                address.setPropertyType(PRIVATE);
                break;
            case PUBLIC:
                address.setPropertyType(PUBLIC);
                break;
            case STATE:
                address.setPropertyType(STATE);
                break;
            case MUNICIPAL:
                address.setPropertyType(MUNICIPAL);
                break;
            default:
                address.setPropertyType(PRIVATE);
        }
        return addressRepository.save(address);
    }
    /*Adress adress1 = new Adress();
        adress1.setId(adress.getId());
        adress1.setCountry(adress.getCountry());
        adress1.setCity(adress.getCity());
        adress1.setStreet(adress.getStreet());
        adress1.setPostalCode(adress.getPostalCode());
        switch (adress.getPropertyType().toUpperCase()) {
            case "PRIVATE": adress1.setPropertyType(PropertyType.PRIVATE); break;
            case "PUBLIC": adress1.setPropertyType(PropertyType.PUBLIC); break;
            case "STATE": adress1.setPropertyType(PropertyType.STATE); break;
            case "MUNICIPAL": adress1.setPropertyType(PropertyType.MUNICIPAL); break;
            default:adress1.setPropertyType(PropertyType.PRIVATE);*/

    @Override
    public List<Address> readAll() {
        return (List<Address>) addressRepository.findAll();
    }

    @Override
    public Optional<Address> read(int id) {
        return addressRepository.findById(id);
    }

    @Override
    public List<String> getCountry(String country) {
        return addressRepository.getCountryJPQL(country);
    }


    @Override
    public int distinctCountries() {
        return addressRepository.distinctCountries();
    }

    @Override
    public List<String> getCountryEndsE() {
        return addressRepository.getCountryEndsE();
    }

    @Override
    public Address update(Address address, int id) throws AddressException {
//        Optional<Adress> adressOld = adressRepository.findById(id);
//        if (adressOld.isPresent()) {
//            Adress a = adressOld.get();
//            a.setCountry(adress.getCountry());
//            a.setCity(adress.getCity());
//            a.setStreet(adress.getStreet());
//            a.setPostalCode(adress.getPostalCode());
//            a.setPropertyType(adress.getPropertyType());
//            return adressRepository.save(a);
//        } else throw new AdressException("No such adress: " + id);
        addressRepository.findById(id).map(
                a -> {
                    a.setCountry(address.getCountry());
                    a.setCity(address.getCity());
                    a.setStreet(address.getStreet());
                    a.setPostalCode(address.getPostalCode());
                    a.setPropertyType(address.getPropertyType());
                    return addressRepository.save(a);
                }).orElseThrow(() -> new AddressException("No such adress: " + id));

        return addressRepository.findById(id).get();
    }

    @Override
    public Address updatePatch(AddressCountryOnly addressCountryOnly, int id) throws AddressException {
        Optional<Address> adressOld = addressRepository.findById(id);
        if (adressOld.isPresent()) {
            Address addressNew = adressOld.get();
            addressNew.setCountry(addressCountryOnly.getCountry());
            return addressRepository.save(addressNew);
        } else throw new AddressException("No such adress: " + id);
    }

    @Override
    public Address updatePatchMap(Map<String, String> map, int id) throws AddressException {
        Optional<Address> adressOld = addressRepository.findById(id);
        if (adressOld.isPresent()) {
            Address addressNew = adressOld.get();
            for (String country : map.values()) {
                addressNew.setCountry(country);
            }
            return addressRepository.save(addressNew);
        } else throw new AddressException("No such adress: " + id);
    }

    @Override
    public void delete(int id) throws AddressException {
        final Optional<Address> adress = addressRepository.findById(id);
        if (adress.isPresent()) {
            addressRepository.deleteById(id);
        } else throw new AddressException("No such adress: " + id);
    }

    @Override
    public List<Address> firstNAdresses(int val) {
        return addressRepository.firstNAdresses(val);
    }

    @Override
    public List<Address> findNAdressNamedQuery(int n) {
        return addressRepository.findNAdressNamedQuery(n);
    }

    @Override
    public int countOfCountries() {
        return addressRepository.countOftCountries();
    }

    @Override
    public int countOfCountriesNamedQuery() {
        return addressRepository.countOfCountriesNamedQuery();
    }

    @Override
    public List<Address> findAdressById(int id) {
        return addressRepository.findAdressById(id);
    }

    @Override
    public int countOfCities() {
        return addressRepository.countOfCities();
    }
}

