package com.genisis.test.controller.v1;


import com.genisis.test.service.EnterpriseService;
import com.genisis.test.dto.enterprise.EnterpriseDTO;
import com.genisis.test.dto.enterprise.UpdateEnterpriseDTO;
import com.genisis.test.model.Enterprise;
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
@RequestMapping(value = "/api/v1/enterprises")
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
            @Valid @RequestBody UpdateEnterpriseDTO updateEnterpriseDTO
    ) throws Exception {
        Enterprise enterprise = enterpriseService.updateEnterprise(enterpriseID, updateEnterpriseDTO);
        return new ResponseEntity<>(enterprise, HttpStatus.CREATED);
    }
}
