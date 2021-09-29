package com.genisis.test.features.contact;

import com.genisis.test.features.contact.dto.ContactDTO;
import com.genisis.test.features.enterprise.Enterprise;
import com.genisis.test.features.enterprise.EnterpriseRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    /**
     * Save new contact.
     *
     * @param contactDTO contactDTO input
     * @return the saved contact
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Contact saveContact(ContactDTO contactDTO) throws Exception {

        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setAddress(contactDTO.getAddress());
        contact.setTvaNumber(contactDTO.getTvaNumber());
        System.out.println();
        // enterprises
        Set<Enterprise> enterprises = new HashSet<Enterprise>();
        // check if is a freelancer
        if (contactDTO.getEnterprises() != null && !contactDTO.getEnterprises().isEmpty()) {
            for (UUID enterpriseID : contactDTO.getEnterprises()) {
                Enterprise enterprise = new Enterprise();
                boolean exists = enterpriseRepository.existsById(enterpriseID);
                if (exists) {
                    enterprise.setId(enterpriseID);
                    enterprises.add(enterprise);
                } else
                    throw new Exception("Enterprise with ID: " + enterpriseID + " not found");
            }
            contact.setEnterprises(enterprises);
        }
        // save contact
        contact = contactRepository.save(contact);
        return contact;
    }
}
