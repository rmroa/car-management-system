package com.rm.cms.service.impl;

import com.rm.cms.dto.ManufacturerDto;
import com.rm.cms.mapper.ManufacturerMapper;
import com.rm.cms.repository.ManufacturerRepository;
import com.rm.cms.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {

    private final ManufacturerRepository manufacturerRepository;

    private final ManufacturerMapper manufacturerMapper;

    @Autowired
    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository, ManufacturerMapper manufacturerMapper) {
        this.manufacturerRepository = manufacturerRepository;
        this.manufacturerMapper = manufacturerMapper;
    }

    @Override
    public List<ManufacturerDto> findAll() {
        return null;
    }

    @Override
    public List<ManufacturerDto> findAll(Pageable pageable) {
        return null;
    }

    /**
     * Search for manufacturer by id
     *
     * @param id the parameter by which we search for manufacturer
     * @return an object manufacturer
     */
    @Transactional
    @Override
    public Optional<ManufacturerDto> findById(Integer id) {
        return manufacturerRepository.findById(id).map(manufacturerMapper::entityToDto);
    }

    @Override
    public ManufacturerDto save(ManufacturerDto manufacturerDto) {
        return null;
    }

    @Override
    public ManufacturerDto update(ManufacturerDto manufacturerDto) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
