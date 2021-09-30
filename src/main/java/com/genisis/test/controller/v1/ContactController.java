package com.genisis.test.controller.v1;

import com.genisis.test.model.Contact;
import com.genisis.test.service.ContactService;
import com.genisis.test.dto.contact.ContactDTO;
import com.genisis.test.dto.contact.UpdateContactDTO;
import com.genisis.test.dto.enterprise.AddContactToEnterpriseDTO;

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
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/v1/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    // create a contact + validation dto
    @Operation(summary = "Create a new contact")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact created",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Error creating contact ",
                    content = @Content),
    })
    @PostMapping("/")
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactDTO contactDTO) throws Exception {
        Contact contact = contactService.saveContact(contactDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    // update a contact
    @Operation(summary = "Update a contact by contact ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Contact updated",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "400", description = "Error updating contact ",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "contact with ID: {contactID} not found",
                    content = @Content)
    })
    @PutMapping("/{contactID}")
    public ResponseEntity<?> updateContact(
            @PathVariable String contactID,
            @Valid @RequestBody UpdateContactDTO updateContactDTO
    ) throws Exception {
        Contact contact = contactService.updateContact(contactID, updateContactDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    // delete a contact
    @Operation(summary = "Delete a contact by contact ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Contact deleted",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Contact.class))}),
            @ApiResponse(responseCode = "404", description = "contact with ID: {contactID} not found",
                    content = @Content),
    })
    @DeleteMapping("{contactID}")
    public ResponseEntity<?> deleteContact(
            @PathVariable String contactID
    ) throws Exception {
        boolean isRemoved = contactService.deleteContact(contactID);
        HashMap<String, String> res = new HashMap<>();
        if (!isRemoved) {
            res.put("error", "contact with ID: " + contactID + " not found");
            return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        }
        res.put("success", "contact with ID: " + contactID + " removed successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // add a contact to an enterprise
    // this will add one contact to on enterprise only
    @Operation(summary = "Add a contact to an enterprise", description = "this will add one contact to on enterprise only")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Contact updated",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Contact.class)
                            )
                    }
            ),

            @ApiResponse(responseCode = "404", description = "this contact is already added to this enterprise",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "contact with ID: {contactID} not found",
                    content = @Content)
    })
    @PostMapping("/add-one-contact-to-enterprise")
    public ResponseEntity<?> addContactToEnterprise(@Valid @RequestBody AddContactToEnterpriseDTO addContactToEnterpriseDTO) throws Exception {
        Contact contact = contactService.addContactToEnterprise(addContactToEnterpriseDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }


}
