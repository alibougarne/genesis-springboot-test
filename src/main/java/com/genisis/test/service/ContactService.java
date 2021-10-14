package com.genisis.test.service;

import com.genisis.test.model.Contact;
import com.genisis.test.dto.contact.ContactDTO;
import com.genisis.test.dto.contact.UpdateContactDTO;
import com.genisis.test.model.Enterprise;
import com.genisis.test.repository.EnterpriseRepository;
import com.genisis.test.dto.enterprise.AddContactToEnterpriseDTO;
import com.genisis.test.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.genisis.test.utils.UuidUtils.checkUUID;

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
     * @param updateContactDTO contactDTO input
     * @return the saved contact
     * @throws Exception contact not found
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Contact updateContact(String contactID, UpdateContactDTO updateContactDTO) throws Exception {
        UUID uuid = checkUUID(contactID);
        Optional<Contact> contact = contactRepository.findById(uuid);
        if (contact.isPresent()) {
            Contact contactToUpdate = contact.get();
            contactToUpdate.preUpdate();
            if (updateContactDTO.getLastName() != null) contactToUpdate.setLastName(updateContactDTO.getLastName());
            if (updateContactDTO.getFirstName() != null) contactToUpdate.setFirstName(updateContactDTO.getFirstName());
            if (updateContactDTO.getTvaNumber() != null) contactToUpdate.setTvaNumber(updateContactDTO.getTvaNumber());
            if (updateContactDTO.getAddress() != null) contactToUpdate.setAddress(updateContactDTO.getAddress());

            // enterprises
            Set<Enterprise> enterprises = new HashSet<Enterprise>();
            // check if is a freelancer
            if (updateContactDTO.getEnterprises() != null && !updateContactDTO.getEnterprises().isEmpty()) {
                contactToUpdate.setTvaNumber(null);
                for (String enterpriseID : updateContactDTO.getEnterprises()) {
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

    /**
     * delete contact.
     *
     * @param contactID contactID input
     * @return the saved contact
     * @throws Exception contact not found
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public boolean deleteContact(String contactID) throws Exception {
        UUID uuid = checkUUID(contactID);
        boolean exists = contactRepository.existsById(uuid);
        if (exists) contactRepository.deleteById(uuid);
        return exists;
    }




    /**
     * add one contact to an enterprise.
     *
     * @param addContactToEnterpriseDTO contactToEnterpriseDTO input
     * @return the updated enterprise
     * @throws Exception enterprise not found
     * @throws Exception contact not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Contact addContactToEnterprise(AddContactToEnterpriseDTO addContactToEnterpriseDTO) throws Exception {
        // check uuid validity
        UUID enterpriseUUID = checkUUID(addContactToEnterpriseDTO.getEnterpriseID());
        UUID contactUUID = checkUUID(addContactToEnterpriseDTO.getContactID());
        // check contact and enterprise existence
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(enterpriseUUID);
        Optional<Contact> contactOptional = contactRepository.findById(contactUUID);
        if (contactOptional.isPresent() && enterpriseOptional.isPresent()) {
            Contact contact = contactOptional.get();
            Enterprise enterprise = enterpriseOptional.get();
            Set<Enterprise> enterprises = new HashSet<>();
            if (!contact.getEnterprises().isEmpty()) {
                if (contact.getEnterprises().contains(enterprise)) {
                    throw new Exception("this contact is already added to this enterprise");
                } else {
                    enterprises.addAll(contact.getEnterprises());
                }
            }
            enterprises.add(enterprise);
            contact.setEnterprises(enterprises);
            // here we ensure that this contact is no longer a freelancer
            contact.setTvaNumber(null);
            contact = contactRepository.save(contact);
            return contact;
        }
        throw new Exception("please enter a valid enterprise/contact");
    }

    /**
     * add one contact to an enterprise.
     *
     * @return contact list
     * @throws Exception contacts not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public List<Contact> getAllContacts() throws Exception {
        List<Contact> contacts = contactRepository.findAll();
        if (contacts.isEmpty()) throw new Exception("contacts not found");
        return contacts;
    }
}
