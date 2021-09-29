package com.genisis.test.features.contact;

import com.genisis.test.features.contact.dto.ContactDTO;
import com.genisis.test.features.enterprise.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class ContactService {
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Fetch all users list.
     *
     * @param contactDTO contactDTO input
     * @return the saved contact
     * @throws Exception users not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Contact saveContact(ContactDTO contactDTO) throws Exception {

        Contact contact = new Contact();
        contact.setFirstName(contactDTO.getFirstName());
        contact.setLastName(contactDTO.getLastName());
        contact.setIsFreelancer(contactDTO.getIsFreelancer());
        contact.setTvaNumber(contactDTO.getTvaNumber());
        // enterprises
        Set<Enterprise> enterprises = new HashSet<Enterprise>();
        for (UUID enterpriseID: contactDTO.getEnterprises()){
            Enterprise enterprise= new Enterprise();
            enterprise.setId(enterpriseID);
            enterprises.add(enterprise);
        }
        contact.setEnterprises(enterprises);
        // save contact
        contact = contactRepository.save(contact);
        return contact;
    }
}
