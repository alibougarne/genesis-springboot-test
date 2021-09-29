package com.genisis.test.features.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class EnterpriseDTO {
    @NotNull(message = "first name may not be null")
    @NotEmpty(message = "first name must be not empty")
    @Size(min = 2, message = "first name should have at least 2 characters")
    @Size(max = 100, message = "first name should have at max 100 characters")
    private String name;

    @NotNull(message = "address may not be null")
    @NotEmpty(message = "address must be not empty")
    @Size(min = 10, message = "address should have at least 10 characters")
    @Size(max = 1000, message = "address should have at max 1000 characters")
    private String address;

    private Integer tvaNumber;

}
