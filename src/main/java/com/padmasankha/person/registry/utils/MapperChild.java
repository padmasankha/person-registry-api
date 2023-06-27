package com.padmasankha.person.registry.utils;

import com.padmasankha.person.registry.domain.Child;
import com.padmasankha.person.registry.dto.ChildDTO;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class MapperChild {
    private static final Logger logger = Logger.getLogger(MapperChild.class.getName());

    public static List<Child> mapToChild(List<ChildDTO> childDTOList) {
        logger.info("Mapping ChildDTO objects to Child objects");
        List<Child> childList = childDTOList.stream()
                .map(childDTO -> new Child(childDTO.name(), childDTO.birthday()))
                .collect(Collectors.toList());
        logger.info("ChildDTO to Child mapping completed");
        return childList;
    }

    public static List<ChildDTO> mapToChildDTO(List<Child> childList) {
        logger.info("Mapping Child objects to ChildDTO objects");

        List<ChildDTO> childDTOList = childList.stream()
                .map(child -> new ChildDTO(child.name(), child.birthday()))
                .collect(Collectors.toList());

        logger.info("Child to ChildDTO mapping completed");

        return childDTOList;
    }
}
