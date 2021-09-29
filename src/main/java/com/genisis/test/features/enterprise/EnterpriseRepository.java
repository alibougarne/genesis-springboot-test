package com.genisis.test.features.enterprise;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID> {
    boolean existsById(UUID enterpriseID);
}
