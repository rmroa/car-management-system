package com.rm.cms.controller;

import com.rm.cms.dto.ManufacturerDto;
import com.rm.cms.service.ManufacturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auto/manufacturer")
public class ManufacturerController {

    private final ManufacturerService manufacturerService;

    @Autowired
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    /**
     * Search for manufacturer by id
     *
     * @param id the parameter by which we search for manufacturer
     * @return an object manufacturer if any, otherwise HttpStatus.NOT_FOUND
     */
    @GetMapping("/{id}")
    public ResponseEntity<Optional<ManufacturerDto>> findByManufacturerId(@PathVariable Integer id) {
        Optional<ManufacturerDto> manufacturerById = manufacturerService.findById(id);
        return manufacturerById.map(manufacturerDto -> new ResponseEntity<>(manufacturerById, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
