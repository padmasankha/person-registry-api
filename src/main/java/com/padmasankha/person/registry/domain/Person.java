package com.padmasankha.person.registry.domain;

import java.util.List;


public record Person(
        String ssn,
        String name,
        String spouseName,
        List<Child> children
) {
}

