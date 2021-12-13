package com.rm.cms.service;

import com.rm.cms.dto.ManufacturerDto;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {

    List<ManufacturerDto> findAll();

    List<ManufacturerDto> findAll(Pageable pageable);

    Optional<ManufacturerDto> findById(Integer id);

    ManufacturerDto save(ManufacturerDto manufacturerDto);

    ManufacturerDto update(ManufacturerDto manufacturerDto);

    void deleteById(Integer id);
}
