package com.genisis.test.dto.contact;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
public class UpdateContactDTO {
    @Size(min = 2, message = "first name should have at least 2 characters")
    @Size(max = 100, message = "first name should have at max 100 characters")
    private String firstName;

    @Size(min = 2, message = "last name should have at least 2 characters")
    @Size(max = 100, message = "last name should have at max 100 characters")
    private String lastName;

    @Size(min = 10, message = "address should have at least 10 characters")
    @Size(max = 1000, message = "address should have at max 1000 characters")
    private String address;
    private Integer tvaNumber;
    private Set<String> enterprises;
}
