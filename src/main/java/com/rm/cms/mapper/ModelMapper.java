package com.rm.cms.mapper;

import com.rm.cms.dto.ModelDto;
import com.rm.cms.entity.Model;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring", implementationName = "test")
@Component
public interface ModelMapper {

    /**
     * converting object Model to object ModelDto
     *
     * @param model convertible object
     * @return mapped object
     */
    ModelDto entityToDto(Model model);

    /**
     * converting object NewsDto to object News
     *
     * @param modelDto convertible object
     * @return mapped object
     */
    Model dtoToEntity(ModelDto modelDto);
}
