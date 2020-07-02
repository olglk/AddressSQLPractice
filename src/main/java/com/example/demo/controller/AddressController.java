package com.example.demo.controller;

import com.example.demo.exception.AddressException;
import com.example.demo.model.Address;
import com.example.demo.model.AddressCountryOnly;
import com.example.demo.service.AddressService;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class AddressController {

    AddressService adressService;

    @PostMapping("/adresses")
    public Address create(@RequestBody Address address) {
        return adressService.create(address);
        //return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/adresses")
    public List<Address> readAll() {
        final List<Address> addresses = adressService.readAll();
        return addresses;

        /*return adresses != null && !adresses.isEmpty()
                ? new ResponseEntity<>(adresses, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
    }

    @GetMapping("/adresses/{id}")
    public Address readById(@PathVariable int id) throws AddressException {
        final Optional<Address> adress = adressService.read(id);
        return adress.orElseThrow(() -> new AddressException("Adress with the current ID was not found: " + id));
    }

    @GetMapping("/adresses/country/{country}")
    public List<String> getCountry(@PathVariable String country) throws AddressException {
        final List<String> listAdress = adressService.getCountry(country);
        //return listAdress.orElseThrow(() -> new AdressException("No data containing the following letter or word found"));
        if (listAdress.isEmpty()) {
            throw new AddressException("No data containing the following letter or word found");
        } else return listAdress;
    }

    @GetMapping("/adresses/unique")
    public int distinctCountries() {
        return adressService.distinctCountries();
    }

    @RequestMapping(value = "/adresses/endse", method = RequestMethod.GET)
    //@GetMapping("/adresses/endse")
    public List<String> getCountryEndsE() {
        return adressService.getCountryEndsE();
    }

    @RequestMapping(value = "/adresses/options", method = RequestMethod.OPTIONS)
    ResponseEntity<?> collectionOptions() {
        return ResponseEntity
                .ok()
                .allow(HttpMethod.GET, HttpMethod.POST, HttpMethod.OPTIONS)
                .build();
    }

    @PutMapping("/adresses/{id}")
    public Address update(@PathVariable int id, @RequestBody Address address) throws AddressException {
        return adressService.update(address, id);
    }

    @PatchMapping("/adresses/{id}")
    public Address updatePatch(@PathVariable int id, @RequestBody AddressCountryOnly adressCountry) throws AddressException {
        return adressService.updatePatch(adressCountry, id);
    }

    @PatchMapping("/adresses/map/{id}")
    public Address updatePatchMap(@RequestBody Map<String, String> map, @PathVariable int id) throws AddressException {
        return adressService.updatePatchMap(map, id);
    }

    @DeleteMapping("/adresses/{id}")
    public String delete(@PathVariable int id) throws AddressException {
        adressService.delete(id);
        return "Deleted: " + id;
    }

    // FUNCTION FIND FIRST N ADRESSES (IN)
    @GetMapping("/adresses/first/{val}")
    public List<Address> firstNAdresses(@PathVariable int val) {
        return adressService.firstNAdresses(val);
    }


    @GetMapping("/adresses/first/named/{id}")
    public List<Address> findNAdressNamedQuery(@PathVariable int id) {
        return adressService.findNAdressNamedQuery(id);
    }

    //FUNCTION COUNT OF COUNTRIES (OUT)
    @GetMapping("/adresses/countcountries")
    public int countOfCountries() {
        return adressService.countOfCountries();
    }

    @GetMapping("/adresses/countcountries/named")
    public int countOfCountriesNamedQuery() {
        return adressService.countOfCountriesNamedQuery();
    }

    //FUNCTION FIND ADRESS BY ID (IN)
    @GetMapping("/adresses/find/named/{id}")
    public List<Address> findAdressById(@PathVariable int id) {
        return adressService.findAdressById(id);
    }

    //FUNCTION COUNT OF CITIES
    @GetMapping("/adresses/countcities/named")
    public int countOfCities() {
        return adressService.countOfCities();
    }


}
