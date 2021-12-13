package com.rm.cms.repository;

import com.rm.cms.entity.Model;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModelRepository extends JpaRepository<Model, Long>, ModelRepositoryCustom {

    List<Model> findAllModelsByManufacturerBrandIgnoreCaseIsLike(String manufacturerName);

    List<Model> findAllModelsByManufacturerCountryIsLike(String country);

    List<Model> findByManufacturerId(Integer manufacturerId, Pageable pageable);
}
