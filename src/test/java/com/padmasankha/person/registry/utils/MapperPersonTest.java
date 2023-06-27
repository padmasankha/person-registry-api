package com.padmasankha.person.registry.utils;

import com.padmasankha.person.registry.domain.Child;
import com.padmasankha.person.registry.domain.Person;
import com.padmasankha.person.registry.dto.ChildDTO;
import com.padmasankha.person.registry.dto.PersonDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@DisplayName("Mapper Person Suite")
class MapperPersonTest {

    @Test
    @DisplayName("Test case for convert the PersonDTO objects to Person object")
    void mapToPerson() {
        List<ChildDTO> inputChildren = new ArrayList<>();
        inputChildren.add(new ChildDTO("Alice", new Date()));
        PersonDTO inputPersonDTO = new PersonDTO("123456789", "John Doe", "Jane Doe", inputChildren);

        Person outputPerson = MapperPerson.mapToPerson(inputPersonDTO);

        Assertions.assertEquals(inputPersonDTO.ssn(), outputPerson.ssn());
        Assertions.assertEquals(inputPersonDTO.name(), outputPerson.name());
        Assertions.assertEquals(inputPersonDTO.spouseName(), outputPerson.spouseName());
        Assertions.assertEquals(inputPersonDTO.children().get(0).name(), outputPerson.children().get(0).name());
        Assertions.assertEquals(inputPersonDTO.children().get(0).birthday(), outputPerson.children().get(0).birthday());
    }

    @Test
    @DisplayName("Test case for convert the PersonDTO objects to Person object without Children")
    void mapToPersonWithoutChildren() {

        PersonDTO inputPersonDTO = new PersonDTO("123456789", "John Doe", "Jane Doe", null);
        Person outputPerson = MapperPerson.mapToPerson(inputPersonDTO);

        Assertions.assertEquals(inputPersonDTO.ssn(), outputPerson.ssn());
        Assertions.assertEquals(inputPersonDTO.name(), outputPerson.name());
        Assertions.assertEquals(inputPersonDTO.spouseName(), outputPerson.spouseName());
        Assertions.assertNull(outputPerson.children());
    }

    @Test
    @DisplayName("Test case for convert the Person objects to PersonDTO object")
    void mapToPersonDTO() {
        List<Child> inputChildren = new ArrayList<>();
        inputChildren.add(new Child("Alice", new Date()));
        Person inputPerson = new Person("123456789", "John Doe", "Jane Doe", inputChildren);

        PersonDTO outputPersonDTO = MapperPerson.mapToPersonDTO(inputPerson);

        Assertions.assertEquals(inputPerson.ssn(), outputPersonDTO.ssn());
        Assertions.assertEquals(inputPerson.name(), outputPersonDTO.name());
        Assertions.assertEquals(inputPerson.spouseName(), outputPersonDTO.spouseName());
        Assertions.assertEquals(inputPerson.children().get(0).name(), outputPersonDTO.children().get(0).name());
        Assertions.assertEquals(inputPerson.children().get(0).birthday(), outputPersonDTO.children().get(0).birthday());
    }

    @Test
    @DisplayName("Test case for convert the Person objects to PersonDTO object without Children")
    void mapToPersonDTOWithoutChildren() {

        Person inputPerson = new Person("123456789", "John Doe", "Jane Doe", null);
        PersonDTO outputPersonDTO = MapperPerson.mapToPersonDTO(inputPerson);

        Assertions.assertEquals(inputPerson.ssn(), outputPersonDTO.ssn());
        Assertions.assertEquals(inputPerson.name(), outputPersonDTO.name());
        Assertions.assertEquals(inputPerson.spouseName(), outputPersonDTO.spouseName());
        Assertions.assertNull(outputPersonDTO.children());
    }



}