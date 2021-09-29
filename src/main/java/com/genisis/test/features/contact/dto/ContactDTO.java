package com.genisis.test.features.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Basic;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ContactDTO {
    @NotNull(message = "first name may not be null")
    @NotEmpty(message = "first name must be not empty")
    @Size(min = 2, message = "first name should have at least 2 characters")
    @Size(max = 100, message = "first name should have at max 100 characters")
    private String firstName;

    @NotNull(message = "last name may not be null")
    @NotEmpty(message = "last name must be not empty")
    @Size(min = 2, message = "last name should have at least 2 characters")
    @Size(max = 100, message = "last name should have at max 100 characters")
    private String lastName;

    @NotNull(message = "address may not be null")
    @NotEmpty(message = "address must be not empty")
    @Size(min = 10, message = "address should have at least 10 characters")
    @Size(max = 1000, message = "address should have at max 1000 characters")
    private String address;
    private Integer tvaNumber;
    private Set<UUID> enterprises;
}
