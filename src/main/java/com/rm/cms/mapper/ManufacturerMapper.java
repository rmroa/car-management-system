package com.rm.cms.mapper;

import com.rm.cms.dto.ManufacturerDto;
import com.rm.cms.entity.Manufacturer;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface ManufacturerMapper {

    /**
     * converting object Manufacturer to object ManufacturerDto
     *
     * @param manufacturer convertible object
     * @return mapped object
     */
    ManufacturerDto entityToDto(Manufacturer manufacturer);

    /**
     * converting object ManufacturerDto to object Manufacturer
     *
     * @param manufacturerDto convertible object
     * @return mapped object
     */
    Manufacturer dtoToEntity(ManufacturerDto manufacturerDto);
}
