package com.genisis.test.features.enterprise.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Basic;
import javax.persistence.Column;

@Data
@AllArgsConstructor
public class EnterpriseDTO {
    private String name;
    private String address;
    private Integer tvaNumber;

}
