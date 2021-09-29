package com.genisis.test.features.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
public class AddContactToEnterpriseDTO {
    @NotNull(message = "contact ID may not be null")
    @NotEmpty(message = "contact ID must be not empty")
    private String contactID;

    @NotNull(message = "enterprise ID may not be null")
    @NotEmpty(message = "enterprise ID must be not empty")
    private String enterpriseID;

}
