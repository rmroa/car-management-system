package com.rm.cms.service.impl;

import com.rm.cms.dto.ModelDto;
import com.rm.cms.entity.Manufacturer;
import com.rm.cms.entity.Model;
import com.rm.cms.exception.ManufacturerNotFoundException;
import com.rm.cms.exception.ModelNotFoundException;
import com.rm.cms.mapper.ModelMapper;
import com.rm.cms.repository.ManufacturerRepository;
import com.rm.cms.repository.ModelRepository;
import com.rm.cms.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.List;
import java.util.Set;

import static com.rm.cms.exception.ExceptionMessages.MANUFACTURER_NOT_FOUND;
import static com.rm.cms.exception.ExceptionMessages.MODEL_NOT_FOUND;
import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;

    private final ManufacturerRepository manufacturerRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ModelServiceImpl(ModelRepository modelRepository, ManufacturerRepository manufacturerRepository, ModelMapper modelMapper) {
        this.modelRepository = modelRepository;
        this.manufacturerRepository = manufacturerRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Make a request to receive all models
     *
     * @return a list of objects model, if present, otherwise an empty list
     */
    @Transactional
    @Override
    public List<ModelDto> findAll() {
        return modelRepository.findAll()
                .stream()
                .map(modelMapper::entityToDto)
                .collect(toList());
    }

    /**
     * Make a request to receive all models by manufacturer brand
     *
     * @param manufacturerName the parameter by which we search for models
     * @return a list of objects model
     */
    @Transactional
    @Override
    public List<ModelDto> findAllModelsByManufacturerName(String manufacturerName) {
        return modelRepository.findAllModelsByManufacturerBrandIgnoreCaseIsLike(manufacturerName)
                .stream()
                .map(modelMapper::entityToDto)
                .collect(toList());
    }

    /**
     * Make a request to receive all models by manufacturer country
     *
     * @param country the parameter by which we search for models
     * @return a list of objects model
     */
    @Transactional
    @Override
    public List<ModelDto> findAllModelsByManufacturerCountry(String country) {
        return modelRepository.findAllModelsByManufacturerCountryIsLike(country)
                .stream()
                .map(modelMapper::entityToDto)
                .collect(toList());
    }

    /**
     * Find all models by manufacturerId with pagination
     *
     * @param page zero-based page index, must not be negative.
     * @param size the size of the page to be returned, must be greater than 0.
     * @return a list of objects model
     */
    @Transactional
    @Override
    public List<ModelDto> findAllModelsByManufacturerId(Integer manufacturerId, int page, int size) {
        manufacturerRepository.findById(manufacturerId).orElseThrow(() -> new ManufacturerNotFoundException(MANUFACTURER_NOT_FOUND + manufacturerId));
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by(DESC, "productionYear"));
        return modelRepository.findByManufacturerId(manufacturerId, pageRequest)
                .stream()
                .map(modelMapper::entityToDto)
                .collect(toList());
    }

    /**
     * Find all models by filters
     *
     * @param transmission the parameter by which we search for models.
     * @param driveUnit    the parameter by which we search for models.
     * @return a list of objects model
     */
    @Transactional
    @Override
    public List<ModelDto> findByModelFilters(String transmission, String driveUnit) {
        return modelRepository.findByModelFilter(transmission, driveUnit)
                .stream()
                .map(modelMapper::entityToDto)
                .collect(toList());
    }

    /**
     * Saving object model
     *
     * @param modelDto object model to save
     * @return saved model with HttpStatus.CREATED
     */
    @Transactional
    @Override
    public ModelDto save(ModelDto modelDto) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ModelDto>> validationResult = validator.validate(modelDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        Model model = modelRepository.save(modelMapper.dtoToEntity(modelDto));
        return modelMapper.entityToDto(model);
    }

    /**
     * Update object model
     *
     * @param manufacturerId identifier manufacturer
     * @param modelDto       object model to update
     * @return updated object model
     */
    @Transactional
    @Override
    public ModelDto update(Integer manufacturerId, ModelDto modelDto) {
        Manufacturer manufacturer = manufacturerRepository.findById(manufacturerId)
                .orElseThrow(() -> new ManufacturerNotFoundException(MANUFACTURER_NOT_FOUND + manufacturerId));
        modelRepository.findById(modelDto.getId()).orElseThrow(() -> new ModelNotFoundException(MODEL_NOT_FOUND + modelDto.getId()));
        Model model = modelMapper.dtoToEntity(modelDto);
        model.setManufacturer(manufacturer);
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<ModelDto>> validationResult = validator.validate(modelDto);
        if (!validationResult.isEmpty()) {
            throw new ConstraintViolationException(validationResult);
        }
        Model saveModel = modelRepository.save(model);
        return modelMapper.entityToDto(saveModel);
    }

    /**
     * Delete object model by id
     *
     * @param modelId identifier model
     */
    @Transactional
    @Override
    public void deleteById(Long modelId) {
        modelRepository.findById(modelId)
                .orElseThrow(() -> new ModelNotFoundException(MODEL_NOT_FOUND + modelId));
        modelRepository.deleteById(modelId);
    }
}
