package com.rm.cms.service.impl;

import com.rm.cms.dto.VehicleTypeDto;
import com.rm.cms.repository.VehicleTypeRepository;
import com.rm.cms.service.VehicleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleTypeIServiceImpl implements VehicleTypeService {

    private final VehicleTypeRepository vehicleTypeRepository;

    @Autowired
    public VehicleTypeIServiceImpl(VehicleTypeRepository vehicleTypeRepository) {
        this.vehicleTypeRepository = vehicleTypeRepository;
    }

    @Override
    public List<VehicleTypeDto> findAll() {
        return null;
    }

    @Override
    public List<VehicleTypeDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<VehicleTypeDto> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public VehicleTypeDto save(VehicleTypeDto vehicleTypeDto) {
        return null;
    }

    @Override
    public VehicleTypeDto update(VehicleTypeDto vehicleTypeDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
