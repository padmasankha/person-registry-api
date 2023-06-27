package com.padmasankha.person.registry.repository;

import com.padmasankha.person.registry.domain.Person;

import java.util.Optional;

public interface IDataSource {

    void save(Person person);
    Optional<Person> getBySSN(String ssn);

    Boolean isSSNRegistered(String ssn);

}
