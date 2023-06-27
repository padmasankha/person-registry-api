package com.padmasankha.person.registry.service;

import com.padmasankha.person.registry.dto.ChildDTO;
import com.padmasankha.person.registry.dto.PersonDTO;
import com.padmasankha.person.registry.repository.InMemoryDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@DisplayName("Person Service Test Suite")
class PersonServiceTest {


    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final PersonService personService = new PersonService(new InMemoryDataSource());

    private List<ChildDTO> dataSetupForChildDTO() throws ParseException {
        List<ChildDTO> childDTOList = new ArrayList<>();
        ChildDTO childDTO1 = new ChildDTO("Child 1 Name", LocalDate.parse("2008-12-31", formatter));
        childDTOList.add(childDTO1);

        ChildDTO childDTO2 = new ChildDTO("Child 2 Name", LocalDate.parse("2000-12-31", formatter));
        childDTOList.add(childDTO2);

        ChildDTO childDTO3 = new ChildDTO("Child 3 Name", LocalDate.parse("2007-12-31", formatter));
        childDTOList.add(childDTO3);

        return childDTOList;
    }


    @Test
    @DisplayName("Test case for creating a person with all the parameters")
    public void createPersonWithSpouseAndChildren() throws Exception {
        List<ChildDTO> childDTOList = dataSetupForChildDTO();

        PersonDTO personDTO = new PersonDTO("1234", "Test 1 Name", "Test 1 Spouse Name", childDTOList);
        personService.savePerson(personDTO);
        PersonDTO createdPerson = personService.findBySsn("1234");

        Assertions.assertEquals(personDTO.ssn(), createdPerson.ssn());
        Assertions.assertEquals(personDTO.name(), createdPerson.name());
        Assertions.assertEquals(personDTO.spouseName(), createdPerson.spouseName());
        Assertions.assertEquals(personDTO.children().size(), createdPerson.children().size());
    }

    @Test
    @DisplayName("Test case for creating a person with already exists SSN")
    public void createPersonWithAlreadyExistSsn() throws Exception {
        PersonDTO personDTO = new PersonDTO("1234", "Test 1 Name", "Test 1 Spouse Name", null);
        personService.savePerson(personDTO);

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.savePerson(personDTO);
        });
        String expectedMessage = "Provided SSN already registered";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);

    }

    @Test
    @DisplayName("Test case for creating a person with invalid birthday")
    public void createPersonWithInvalidChildBirthday() throws Exception {

        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new ChildDTO("Child 1 Name", LocalDate.now().plusDays(1));
        });
        String expectedMessage = "Error, Birthday cannot be set as future date.";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test case for get a person by invalid SSN")
    public void getPerson() throws Exception {
        IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            personService.findBySsn("1234");
        });

        String expectedMessage = "Person not found for social security number :1234";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test case for get the eldest child")
    void findEldestChildBySsn() throws Exception {
        ChildDTO expectedEldestChild = new ChildDTO("Child 2 Name", LocalDate.parse("2000-12-31", formatter));
        List<ChildDTO> childDTOList = dataSetupForChildDTO();
        PersonDTO personDTO = new PersonDTO("1234", "Test 1 Name", "Test 1 Spouse Name", childDTOList);
        personService.savePerson(personDTO);
        Optional<ChildDTO> actualEldestChild = personService.findEldestChildBySsn("1234");
        Assertions.assertTrue(actualEldestChild.isPresent());
        Assertions.assertEquals(expectedEldestChild, actualEldestChild.get());
    }

    @Test
    @DisplayName("Test case for get the eldest child, when SSN invalid")
    void findEldestChildByInvalidSsn() throws Exception {

        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            personService.findEldestChildBySsn("1234");
        });

        String expectedMessage = "Person not found for ssn: 1234";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test case for get the eldest child, When there is no child")
    void findEldestChildBySsnWhenNoChild() throws Exception {

        PersonDTO personDTO = new PersonDTO("1234", "Test 1 Name", "Test 1 Spouse Name", null);
        personService.savePerson(personDTO);
        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            personService.findEldestChildBySsn("1234");
        });
        String expectedMessage = "No Children found for the provided SSN: 1234";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    @DisplayName("Test case for get the eldest child, When there is empty child list")
    void findEldestChildBySsnWhenEmptyChild() throws Exception {

        List<ChildDTO> children = new ArrayList<>();
        PersonDTO personDTO = new PersonDTO("1234", "Test 1 Name", "Test 1 Spouse Name", children);
        personService.savePerson(personDTO);
        NoSuchElementException exception = Assertions.assertThrows(NoSuchElementException.class, () -> {
            personService.findEldestChildBySsn("1234");
        });
        String expectedMessage = "No Children found for the provided SSN: 1234";
        String actualMessage = exception.getMessage();
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

}