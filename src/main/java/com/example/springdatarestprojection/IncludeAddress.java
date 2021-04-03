package com.example.springdatarestprojection;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "includeAddress", types = { Person.class })
interface IncludeAddress {

    String getName();

    Address getAddress();
}
