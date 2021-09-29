package com.genisis.test.features.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ContactDTO {
    @NotEmpty(message = "firstName must be not empty")
    @Size(min = 2, message = "user name should have at least 2 characters")
    private String firstName;

    @NotEmpty(message = "lastName must be not empty")
    @Size(min = 2, message = "user last name should have at least 2 characters")
    private String lastName;

    @NotEmpty(message = "address must be not empty")
    @Size(min = 2, message = "user address should have at least 2 characters")
    private String address;
    private Boolean  isFreelancer;
    private Integer tvaNumber;
    private Set<UUID> enterprises;
}
