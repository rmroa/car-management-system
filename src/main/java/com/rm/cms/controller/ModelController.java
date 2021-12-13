package com.rm.cms.controller;

import com.rm.cms.dto.ModelDto;
import com.rm.cms.exception.ApiRequestException;
import com.rm.cms.service.ModelService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.rm.cms.utils.AppConstants.DEFAULT_PAGE_NUMBER;
import static com.rm.cms.utils.AppConstants.DEFAULT_PAGE_SIZE;

@RestController
@RequestMapping("/auto/manufacturer")
public class ModelController {

    private final ModelService modelService;

    @Autowired
    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    /**
     * Make a request to receive all models.
     *
     * @return a list of objects model, if present, otherwise an empty list.
     */
    @ApiOperation("Search all models")
    @GetMapping("/models")
    public ResponseEntity<List<ModelDto>> findAll() {
        return new ResponseEntity<>(modelService.findAll(), HttpStatus.OK);
    }

    /**
     * Make a request to receive all models by manufacturer brand.
     *
     * @param manufacturerName the parameter by which we search for models.
     * @return a list of objects model.
     */
    @ApiOperation("Search models by manufacturer brand")
    @GetMapping
    public ResponseEntity<List<ModelDto>> findAllModelsByManufacturerName(@RequestParam(value = "brand") String manufacturerName) {
        return new ResponseEntity<>(modelService.findAllModelsByManufacturerName(manufacturerName), HttpStatus.OK);
    }

    /**
     * Make a request to receive all models by manufacturer country.
     *
     * @param country the parameter by which we search for models.
     * @return a list of objects model
     */
    @ApiOperation("Search models by manufacturer country")
    @GetMapping("/country")
    public ResponseEntity<List<ModelDto>> findAllModelsByManufacturerCountry(@RequestParam String country) {
        return new ResponseEntity<>(modelService.findAllModelsByManufacturerCountry(country), HttpStatus.OK);
    }

    /**
     * Find all models by manufacturerId with pagination
     *
     * @param page zero-based page index, must not be negative.
     * @param size the size of the page to be returned, must be greater than 0.
     * @return a list of objects model
     */
    @ApiOperation("Search models with pagination")
    @GetMapping(value = "/{manufacturerId}/models")
    public ResponseEntity<List<ModelDto>> findAllModelsByManufacturerId(@PathVariable Integer manufacturerId,
                                                                        @RequestParam(required = false, defaultValue = DEFAULT_PAGE_NUMBER) Integer page,
                                                                        @RequestParam(required = false, defaultValue = DEFAULT_PAGE_SIZE) Integer size) {
        return ResponseEntity.ok(modelService.findAllModelsByManufacturerId(manufacturerId, page, size));
    }

    /**
     * Find all models by filters
     *
     * @param transmission the parameter by which we search for models.
     * @param driveUnit    the parameter by which we search for models.
     * @return a list of objects model
     */
    @ApiOperation("Search models by filters")
    @GetMapping("/models/filters")
    public ResponseEntity<List<ModelDto>> findAllModelsByFilters(@RequestParam(value = "transmission") String transmission,
                                                                 @RequestParam(value = "driveUnit") String driveUnit) {
        return new ResponseEntity<>(modelService.findByModelFilters(transmission, driveUnit), HttpStatus.OK);
    }

    /**
     * Saving object model
     *
     * @param modelDto object model to save
     * @return saved model with HttpStatus.CREATED
     */
    @ApiOperation("Saving model")
    @PostMapping("/models")
    public ResponseEntity<ModelDto> saveModel(@RequestBody ModelDto modelDto) {
        try {
            return new ResponseEntity<>(modelService.save(modelDto), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    /**
     * Update object model
     *
     * @param manufacturerId identifier manufacturer
     * @param modelDto       object model to update
     * @return updated object model
     */
    @ApiOperation("Update model")
    @PutMapping("/{manufacturerId}/models")
    public ResponseEntity<ModelDto> updateModel(@PathVariable Integer manufacturerId,
                                                @RequestBody ModelDto modelDto) {
        try {
            return new ResponseEntity<>(modelService.update(manufacturerId, modelDto), HttpStatus.OK);
        } catch (RuntimeException e) {
            throw new ApiRequestException(e.getMessage());
        }
    }

    /**
     * Delete object model by id
     *
     * @param modelId identifier model
     * @return HttpStatus.NO_CONTENT on successful deletion
     */
    @ApiOperation("Deletion model by id")
    @DeleteMapping("/models/{modelId}")
    public ResponseEntity<ModelDto> deleteModel(@PathVariable Long modelId) {
        modelService.deleteById(modelId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
