package com.itmanager.repository;

import com.itmanager.model.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AssetRepository extends JpaRepository<Asset, Long> {
    long countByStatus(String status);

    List<Asset> findByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCaseOrderByIdAsc(String name, String serialNumber);

    List<Asset> findAllByOrderByIdAsc();
}