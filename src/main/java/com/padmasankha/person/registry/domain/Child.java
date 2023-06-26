package com.padmasankha.person.registry.domain;

import java.util.Date;

public record Child(
        String name,
        Date birthday
) {
    public Child {
        Date today = new Date();
        if (birthday != null && birthday.after(today)) {
            throw new IllegalArgumentException("Error, Birthday cannot be set as future date.");
        }
    }


}
