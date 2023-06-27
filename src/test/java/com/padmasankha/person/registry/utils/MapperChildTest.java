package com.padmasankha.person.registry.utils;

import com.padmasankha.person.registry.domain.Child;
import com.padmasankha.person.registry.dto.ChildDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@DisplayName("Mapper Child Test Suite")
class MapperChildTest {

    @Test
    @DisplayName("Test case for convert the ChildDTO objects to Child object")
    void mapToChild() {
        List<ChildDTO> inputChildList = new ArrayList<>();
        inputChildList.add(new ChildDTO("Alice", LocalDate.now()));
        inputChildList.add(new ChildDTO("Bob", LocalDate.now()));

        List<Child> outputChildList = MapperChild.mapToChild(inputChildList);

        Assertions.assertEquals(inputChildList.size(), outputChildList.size());

        for (int i = 0; i < inputChildList.size(); i++) {
            ChildDTO inputChildDTO = inputChildList.get(i);
            Child outputChild = outputChildList.get(i);
            Assertions.assertEquals(inputChildDTO.name(), outputChild.name());
            Assertions.assertEquals(inputChildDTO.birthday(), outputChild.birthday());
        }
    }

    @Test
    @DisplayName("Test case for convert the Child objects to ChildDTO object")
    void mapToChildDTO() {

        List<Child> inputChildList = new ArrayList<>();
        inputChildList.add(new Child("Alice", LocalDate.now()));
        inputChildList.add(new Child("Bob", LocalDate.now()));

        List<ChildDTO> outputChildList = MapperChild.mapToChildDTO(inputChildList);

        Assertions.assertEquals(inputChildList.size(), outputChildList.size());
        for (int i = 0; i < inputChildList.size(); i++) {
            Child inputChild = inputChildList.get(i);
            ChildDTO outputChild = outputChildList.get(i);
            Assertions.assertEquals(inputChild.name(), outputChild.name());
            Assertions.assertEquals(inputChild.birthday(), outputChild.birthday());
        }
    }
}