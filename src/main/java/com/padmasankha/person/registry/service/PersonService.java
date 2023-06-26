package com.padmasankha.person.registry.service;

import com.padmasankha.person.registry.domain.Child;
import com.padmasankha.person.registry.domain.Person;
import com.padmasankha.person.registry.dto.PersonDTO;
import com.padmasankha.person.registry.repository.IDataSource;
import com.padmasankha.person.registry.utils.MapperPerson;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class PersonService {

    private static final Logger logger = Logger.getLogger(PersonService.class.getName());

    private final IDataSource iDataSource;

    public PersonService(IDataSource iDataSource) {
        this.iDataSource = iDataSource;
    }

    public void savePerson(PersonDTO personDTO) throws Exception {

        logger.info("Validating SSN: " + personDTO.ssn());
        if (iDataSource.isSSNRegistered(personDTO.ssn())) {
            logger.info("Failed validating SSN: " + personDTO.ssn());
            throw new IllegalArgumentException("Provided SSN already registered");
        }
        Person person = MapperPerson.mapToPerson(personDTO);
        iDataSource.save(person);
        logger.info("Person created successfully: " + personDTO.ssn());
    }

    public PersonDTO findBySsn(String socialSecurityNumber) throws Exception {
        logger.info("Finding person by social security number: " + socialSecurityNumber);

        Person person = iDataSource.getBySSN(socialSecurityNumber).orElseThrow(() ->
                new IllegalArgumentException("Person not found for social security number"));

        logger.info("Person found by social security number: " + socialSecurityNumber);
        return MapperPerson.mapToPersonDTO(person);
    }

    public List<PersonDTO> getAll() throws Exception{

        logger.info("Retrieving all persons");

        List<Person> personList = iDataSource.getAll()
                .orElseThrow(()->new IllegalArgumentException("Not found any registered person in the database"));

        List<PersonDTO> personDTOList = personList.stream()
                .map(MapperPerson::mapToPersonDTO)
                .collect(Collectors.toList());

        logger.info("All persons retrieved successfully");

        return personDTOList;
    }

    public Optional<Child> findEldestChildBySsn(String ssn) throws Exception {

        Person person = iDataSource.getBySSN(ssn).orElseThrow(
                ()-> new IllegalArgumentException("Person not found for ssn: " + ssn));
        if(person.children()==null)
            throw new IllegalArgumentException("No Children found for the provided SSN: " + ssn);

        List<Child> children = person.children();

        Collections.sort(children, Comparator.comparing(Child::birthday));
        Child eldestChild = children.get(0);
        return Optional.ofNullable(eldestChild);
    }
}
