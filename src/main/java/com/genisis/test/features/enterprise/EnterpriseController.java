package com.genisis.test.features.enterprise;


import com.genisis.test.features.enterprise.dto.ContactToEnterpriseDTO;
import com.genisis.test.features.enterprise.dto.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/enterprises")
public class EnterpriseController {

    @Autowired
    EnterpriseService enterpriseService;

    // create an enterprise + validation dto
    @PostMapping("/")
    public ResponseEntity<?> createContact(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception {
        Enterprise enterprise = enterpriseService.saveEnterprise(enterpriseDTO);
        return new ResponseEntity<>(enterprise, HttpStatus.CREATED);
    }

    // update an enterprise
    @PutMapping("/{enterpriseID}")
    public ResponseEntity<?> updateEnterprise(
            @PathVariable String enterpriseID,
            @Valid @RequestBody EnterpriseDTO enterpriseDTO
    ) throws Exception {
        Enterprise enterprise = enterpriseService.updateEnterprise(enterpriseID, enterpriseDTO);
        return new ResponseEntity<>(enterprise, HttpStatus.CREATED);
    }
}
