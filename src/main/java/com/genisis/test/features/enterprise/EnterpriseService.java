package com.genisis.test.features.enterprise;

import com.genisis.test.features.enterprise.dto.EnterpriseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnterpriseService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    /**
     * Save a new enterprise.
     *
     * @param enterpriseDTO enterpriseDTO input
     * @return the saved contact
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


}
