package com.padmasankha.person.registry.dto;

import java.util.List;

public record PersonDTO(
        String ssn,
        String name,
        String spouseName,
        List<ChildDTO> children
) {
}
