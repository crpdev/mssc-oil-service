package com.crpdev.msscoilservice.repository;

import com.crpdev.msscoilservice.domain.Oil;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OilRepository extends CrudRepository<Oil, UUID> {
}
