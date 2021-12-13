package com.rm.cms.service;

import com.rm.cms.dto.ModelDto;

import java.util.List;

public interface ModelService {

    List<ModelDto> findAllModelsByManufacturerName(String manufacturerName);

    List<ModelDto> findAllModelsByManufacturerCountry(String country);

    List<ModelDto> findAllModelsByManufacturerId(Integer manufacturerId, int page, int size);

    List<ModelDto> findAll();

    List<ModelDto> findByModelFilters(String transmission, String driveUnit);

    ModelDto save(ModelDto modelDto);

    ModelDto update(Integer manufacturerId, ModelDto modelDto);

    void deleteById(Long modelId);
}
