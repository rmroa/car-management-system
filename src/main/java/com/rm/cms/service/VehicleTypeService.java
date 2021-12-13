package com.rm.cms.service;

import com.rm.cms.dto.VehicleTypeDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface VehicleTypeService {

    List<VehicleTypeDto> findAll();

    List<VehicleTypeDto> findAll(Pageable pageable);

    Optional<VehicleTypeDto> findById(Integer id);

    VehicleTypeDto save(VehicleTypeDto vehicleTypeDto);

    VehicleTypeDto update(VehicleTypeDto vehicleTypeDto);

    void deleteById(Integer id);
}
