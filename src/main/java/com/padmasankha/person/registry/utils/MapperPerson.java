package com.padmasankha.person.registry.utils;

import com.padmasankha.person.registry.domain.Child;
import com.padmasankha.person.registry.domain.Person;
import com.padmasankha.person.registry.dto.ChildDTO;
import com.padmasankha.person.registry.dto.PersonDTO;

import java.util.List;
import java.util.logging.Logger;

public class MapperPerson {
    private static final Logger logger = Logger.getLogger(MapperPerson.class.getName());
    public static Person mapToPerson(PersonDTO personDTO) {
        logger.info("Mapping PersonDTO to Person for SSN" + personDTO.ssn());

        List<Child> childList = null;
        if (personDTO.children() != null) {
            childList = MapperChild.mapToChild(personDTO.children());
        }
        Person person = new Person(personDTO.ssn(),personDTO.name(),personDTO.spouseName(),
                childList);

        logger.info("PersonDTO to Person mapping completed for SSN" + personDTO.ssn());
        return person;
    }
    public static PersonDTO mapToPersonDTO(Person person) {
        logger.info("Mapping Person to PersonDTO for SSN" + person.ssn());

        List<ChildDTO> childDtoList = null;
        if (person.children() != null) {
            childDtoList = MapperChild.mapToChildDTO(person.children());
        }

        PersonDTO personDTO = new PersonDTO(
                person.ssn(),
                person.name(),
                person.spouseName(),
                childDtoList
        );

        logger.info("Person to PersonDTO mapping completed for SSN" + person.ssn());
        return personDTO;
    }
}
