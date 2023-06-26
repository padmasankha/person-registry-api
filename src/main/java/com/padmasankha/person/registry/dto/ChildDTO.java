package com.padmasankha.person.registry.dto;

import java.util.Date;

public record ChildDTO(
        String name,
        Date birthday
) {

    public ChildDTO {
        Date today = new Date();
        if (birthday != null && birthday.after(today)) {
            throw new IllegalArgumentException("Error, Birthday cannot be set as future date.");
        }
    }
}
