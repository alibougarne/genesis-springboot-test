package com.genisis.test.features.contact.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
public class ContactDTO {
    private String firstName;
    private String lastName;
    private String address;
    private Boolean  isFreelancer;
    private Integer tvaNumber;
    private Set<UUID> enterprises;
}
