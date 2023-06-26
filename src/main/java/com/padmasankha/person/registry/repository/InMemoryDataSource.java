package com.padmasankha.person.registry.repository;

import com.padmasankha.person.registry.domain.Person;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryDataSource implements IDataSource {

    private Map<String, Person> personMap = new HashMap<>();

    @Override
    public void save(Person person) {
        personMap.put(person.ssn(), person);
    }

    @Override
    public Optional<Person> getBySSN(String ssn) {
        return Optional.ofNullable(personMap.get(ssn));
    }

    @Override
    public Boolean isSSNRegistered(String ssn) {
        return personMap.containsKey(ssn);
    }

    @Override
    public Optional<List<Person>> getAll() {
        return Optional.ofNullable(personMap.values().stream().toList());
    }

}
