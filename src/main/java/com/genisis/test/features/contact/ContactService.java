package com.genisis.test.features.contact;

import com.genisis.test.features.contact.dto.ContactDTO;
import com.genisis.test.features.enterprise.Enterprise;
import com.genisis.test.features.enterprise.EnterpriseRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
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
        // enterprises
        Set<Enterprise> enterprises = new HashSet<Enterprise>();
        // check if is a freelancer
        if (contactDTO.getEnterprises() != null && !contactDTO.getEnterprises().isEmpty()) {
            contact.setTvaNumber(null);
            for (String enterpriseID : contactDTO.getEnterprises()) {
                UUID enterpriseUUID = checkUUID(enterpriseID);
                Enterprise enterprise = new Enterprise();
                boolean exists = enterpriseRepository.existsById(enterpriseUUID);
                if (exists) {
                    enterprise.setId(enterpriseUUID);
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

    /**
     * update contact.
     *
     * @param contactID  contactID input
     * @param contactDTO contactDTO input
     * @return the saved contact
     * @throws Exception contact not found
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Contact updateContact(String contactID, ContactDTO contactDTO) throws Exception {
        UUID uuid = checkUUID(contactID);
        Optional<Contact> contact = contactRepository.findById(uuid);
        if (contact.isPresent()) {
            Contact contactToUpdate = contact.get();
            contactToUpdate.preUpdate();
            contactToUpdate.setLastName(contactDTO.getLastName());
            contactToUpdate.setFirstName(contactDTO.getFirstName());
            contactToUpdate.setTvaNumber(contactDTO.getTvaNumber());
            contactToUpdate.setAddress(contactDTO.getAddress());

            // enterprises
            Set<Enterprise> enterprises = new HashSet<Enterprise>();
            // check if is a freelancer
            if (contactDTO.getEnterprises() != null && !contactDTO.getEnterprises().isEmpty()) {
                contactToUpdate.setTvaNumber(null);
                for (String enterpriseID : contactDTO.getEnterprises()) {
                    UUID enterpriseUUID = checkUUID(enterpriseID);
                    Enterprise enterprise = new Enterprise();
                    boolean exists = enterpriseRepository.existsById(enterpriseUUID);
                    if (exists) {
                        enterprise.setId(enterpriseUUID);
                        enterprises.add(enterprise);
                    } else
                        throw new Exception("Enterprise with ID: " + enterpriseID + " not found");
                }
                contactToUpdate.setEnterprises(enterprises);
            }
            // update contact
            contactToUpdate = contactRepository.save(contactToUpdate);
            return contactToUpdate;
        } else {
            throw new Exception("contact with ID: " + contactID + " not found");
        }
    }

    public UUID checkUUID(String id) {
        if (id.matches("[0-9a-f]{8}-[0-9a-f]{4}-[1-5][0-9a-f]{3}-[89ab][0-9a-f]{3}-[0-9a-f]{12}")) {
            return UUID.fromString(id);
        } else {
            id = id.replaceAll("(.{8})(.{4})(.{4})(.{4})(.+)", "$1-$2-$3-$4-$5");
            UUID uuid = UUID.fromString(id);
            return uuid;
        }
    }
}
