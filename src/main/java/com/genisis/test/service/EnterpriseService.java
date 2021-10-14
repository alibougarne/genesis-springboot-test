package com.genisis.test.service;

import com.genisis.test.model.Enterprise;
import com.genisis.test.repository.EnterpriseRepository;
import com.genisis.test.dto.enterprise.EnterpriseDTO;
import com.genisis.test.dto.enterprise.UpdateEnterpriseDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.genisis.test.utils.UuidUtils.checkUUID;

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
     * @param updateEnterpriseDTO enterpriseDTO input
     * @return the updated enterprise
     * @throws Exception enterprise not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public Enterprise updateEnterprise(String enterpriseID, UpdateEnterpriseDTO updateEnterpriseDTO) throws Exception {
        // check uuid validity
        UUID uuid = checkUUID(enterpriseID);
        Optional<Enterprise> enterpriseOptional = enterpriseRepository.findById(uuid);
        if (enterpriseOptional.isPresent()) {
            Enterprise enterprise = enterpriseOptional.get();
            enterprise.preUpdate();
            if (updateEnterpriseDTO.getName() != null) enterprise.setName(updateEnterpriseDTO.getName());
            if (updateEnterpriseDTO.getAddress() != null) enterprise.setAddress(updateEnterpriseDTO.getAddress());
            if (updateEnterpriseDTO.getTvaNumber() != null) enterprise.setTvaNumber(updateEnterpriseDTO.getTvaNumber());
            enterprise = enterpriseRepository.save(enterprise);
            return enterprise;
        } else throw new Exception("enterprise with ID: " + enterpriseID + " not found");

    }

    /**
     * find all enterprises.
     *
     * @return enterprise list
     * @throws Exception enterprises not found
     * @author Ali BOUGARNE
     * @version 1.0
     * @since 0.0.1
     */
    public List<Enterprise> getAllEnterprises() throws Exception {
        List<Enterprise> enterprises = enterpriseRepository.findAll();
        if(enterprises.isEmpty()) throw new Exception("Enterprises not found");
        return enterprises;

    }
}
