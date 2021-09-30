package com.genisis.test.repository;

import com.genisis.test.model.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EnterpriseRepository extends JpaRepository<Enterprise, UUID> {
    boolean existsById(UUID enterpriseID);
}
