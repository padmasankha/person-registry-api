package com.padmasankha.person.registry.domain;

import java.time.LocalDate;

public record Child(
        String name,
        LocalDate birthday
) {
    public Child {
        LocalDate today = LocalDate.now();
        if (birthday != null && birthday.isAfter(today)) {
            throw new IllegalArgumentException("Error, Birthday cannot be set as future date.");
        }
    }


}
