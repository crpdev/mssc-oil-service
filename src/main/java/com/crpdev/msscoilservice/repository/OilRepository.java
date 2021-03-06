package com.crpdev.msscoilservice.repository;

import com.crpdev.factory.oil.model.OilType;
import com.crpdev.msscoilservice.domain.Oil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OilRepository extends JpaRepository<Oil, UUID> {

    Page<Oil> findAllByOilName(String oilName, Pageable pageable);

    Page<Oil> findAllByOilType(OilType oilType, Pageable pageable);

    Page<Oil> findAllByOilNameAndOilType(String oilName, OilType oilType, Pageable pageable);

    Oil findByProductCode(String productCode);
}
