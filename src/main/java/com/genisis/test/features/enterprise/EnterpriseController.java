package com.genisis.test.features.enterprise;


import com.genisis.test.features.contact.Contact;
import com.genisis.test.features.enterprise.dto.ContactToEnterpriseDTO;
import com.genisis.test.features.enterprise.dto.EnterpriseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Create a new enterprise")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "enterprise created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Enterprise.class))}),
            @ApiResponse(responseCode = "400", description = "Error creating enterprise",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<?> createEnterprise(@Valid @RequestBody EnterpriseDTO enterpriseDTO) throws Exception {
        Enterprise enterprise = enterpriseService.saveEnterprise(enterpriseDTO);
        return new ResponseEntity<>(enterprise, HttpStatus.CREATED);
    }

    // update an enterprise
    @Operation(summary = "Update an enterprise by enterprise ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Enterprise updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Enterprise.class))}),
            @ApiResponse(responseCode = "400", description = "Error updating enterprise ",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "enterprise with ID: {enterpriseID} not found",
                    content = @Content)
    })
    @PutMapping("/{enterpriseID}")
    public ResponseEntity<?> updateEnterprise(
            @PathVariable String enterpriseID,
            @Valid @RequestBody EnterpriseDTO enterpriseDTO
    ) throws Exception {
        Enterprise enterprise = enterpriseService.updateEnterprise(enterpriseID, enterpriseDTO);
        return new ResponseEntity<>(enterprise, HttpStatus.CREATED);
    }
}
