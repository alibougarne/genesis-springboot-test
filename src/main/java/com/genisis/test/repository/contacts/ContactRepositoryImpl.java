package com.genisis.test.repository.contacts;

import com.genisis.test.model.Contact;
import com.genisis.test.model.Contact_;
import com.genisis.test.model.Enterprise;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;
import java.util.List;

public class ContactRepositoryImpl implements ContactRepositoryCustom{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<Contact> fetchAllContacts(Long skip, Long take) throws Exception {
        // first we create the criteria builder
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        // Making The Query Object From The 'CriteriaBuilder' Instance
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Contact.class);
        Root<Contact> contactRoot = criteriaQuery.from(Contact.class);
        SetJoin<Contact, Enterprise> contactEnterpriseSetJoin = contactRoot.join(Contact_.enterprises);


        return null;
    }
}
