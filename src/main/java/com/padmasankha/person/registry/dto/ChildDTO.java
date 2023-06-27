package com.padmasankha.person.registry.dto;

import java.time.LocalDate;

public record ChildDTO(
        String name,
        LocalDate birthday
) {

    public ChildDTO {
        LocalDate today = LocalDate.now();
        if (birthday != null && birthday.isAfter(today)) {
            throw new IllegalArgumentException("Error, Birthday cannot be set as future date.");
        }
    }
}
