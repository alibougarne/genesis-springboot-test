package com.genisis.test.features.enterprise;

import com.genisis.test.features.contact.ContactRepository;
import com.genisis.test.features.contact.ContactService;
import com.genisis.test.features.enterprise.dto.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class EnterpriseService {

    private final EnterpriseRepository enterpriseRepository;

    public EnterpriseService(EnterpriseRepository enterpriseRepository) {
        this.enterpriseRepository = enterpriseRepository;
    }

    /**
     * Save a new enterprise.
     *
     * @param enterpriseDTO enterpriseDTO input
     * @return the saved enterprise
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Enterprise saveEnterprise(EnterpriseDTO enterpriseDTO) {
        Enterprise enterprise = new Enterprise();
        enterprise.setName(enterpriseDTO.getName());
        enterprise.setAddress(enterpriseDTO.getAddress());
        enterprise.setTvaNumber(enterpriseDTO.getTvaNumber());
        enterprise = enterpriseRepository.save(enterprise);
        return enterprise;
    }

    /**
     * update contact.
     *
     * @param enterpriseID  enterpriseID input
     * @param enterpriseDTO enterpriseDTO input
     * @return the updated enterprise
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Enterprise updateEnterprise(String enterpriseID, EnterpriseDTO enterpriseDTO) throws Exception {
        // check uuid validity
        UUID uuid = ContactService.checkUUID(enterpriseID);
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(uuid);
        if (enterpriseOptional.isPresent()) {
            Enterprise enterprise = new Enterprise();
            enterprise.setName(enterpriseDTO.getName());
            enterprise.setAddress(enterpriseDTO.getAddress());
            enterprise.setTvaNumber(enterpriseDTO.getTvaNumber());
            enterprise = enterpriseRepository.save(enterprise);
            return enterprise;
        } else throw new Exception("enterprise with ID: " + enterpriseID + " not found");

    }


}
