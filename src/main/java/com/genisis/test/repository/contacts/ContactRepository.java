package com.genisis.test.repository.contacts;

import com.genisis.test.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ContactRepository extends JpaRepository<Contact, UUID> {
}
