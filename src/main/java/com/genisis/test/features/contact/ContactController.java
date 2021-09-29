package com.genisis.test.features.contact;

import com.genisis.test.features.contact.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    // create a contact + validation dto
    @PostMapping("/")
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactDTO contactDTO) throws Exception {
        Contact contact = contactService.saveContact(contactDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    // update a contact
    @PutMapping("/{contactID}")
    public ResponseEntity<?> updateContact(
            @PathVariable String contactID,
            @Valid @RequestBody ContactDTO contactDTO
    ) throws Exception {
        Contact contact = contactService.updateContact(contactID, contactDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }

    // delete a contact
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

}
