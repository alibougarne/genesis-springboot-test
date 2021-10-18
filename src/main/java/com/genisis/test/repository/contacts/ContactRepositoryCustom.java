package com.genisis.test.repository.contacts;

import com.genisis.test.model.Contact;

import java.util.List;

public interface ContactRepositoryCustom {
    /**
     * Fetch all contact list.
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 1.0.0
     * @param skip the pagination skip parameter
     * @param take the pagination take parameter
     * @return a list of Contact
     * @throws Exception the exception
     */
    List<Contact> fetchAllContacts(Long skip, Long take) throws Exception;
}
