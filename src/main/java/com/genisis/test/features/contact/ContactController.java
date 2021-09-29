package com.genisis.test.features.contact;

import com.genisis.test.features.contact.dto.ContactDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/contacts")
public class ContactController {
    @Autowired
    ContactService contactService;

    @PostMapping("/")
    public ResponseEntity<?> createContact(@Valid @RequestBody ContactDTO contactDTO) throws Exception {
        System.out.println(contactDTO.getAddress());
        Contact contact = contactService.saveContact(contactDTO);
        return new ResponseEntity<>(contact, HttpStatus.CREATED);
    }
}
