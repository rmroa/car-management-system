package com.rm.cms.service.impl;

import com.rm.cms.dto.ManufacturerDto;
import com.rm.cms.dto.ModelDto;
import com.rm.cms.entity.Manufacturer;
import com.rm.cms.entity.Model;
import com.rm.cms.mapper.ModelMapper;
import com.rm.cms.repository.ModelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ModelServiceImplTest {

    private static final Long MODEL_ID = 1L;
    private static final String FIRST_MODEL = "First model";
    private static final String SECOND_MODEL = "Second model";
    private static final String THIRD_MODEL = "Third model";
    private static final String FIRST_MODEL_DTO = "First model";
    private static final String SECOND_MODEL_DTO = "Second model";
    private static final String THIRD_MODEL_DTO = "Third model";
    private static final Long FIRST_MODEL_CURRENT_MILEAGE = 100L;
    private static final Long SECOND_MODEL_CURRENT_MILEAGE = 200L;
    private static final Long THIRD_MODEL_CURRENT_MILEAGE = 300L;
    private static final Long FIRST_MODEL_DTO_CURRENT_MILEAGE = 100L;
    private static final Long SECOND_MODEL_DTO_CURRENT_MILEAGE = 200L;
    private static final Long THIRD_MODEL_DTO_CURRENT_MILEAGE = 300L;
    private static final BigDecimal FIRST_MODEL_PRICE = new BigDecimal("100.40");
    private static final BigDecimal SECOND_MODEL_PRICE = new BigDecimal("200.40");
    private static final BigDecimal THIRD_MODEL_PRICE = new BigDecimal("300.40");
    private static final BigDecimal FIRST_MODEL_DTO_PRICE = new BigDecimal("100.40");
    private static final BigDecimal SECOND_MODEL_DTO_PRICE = new BigDecimal("200.40");
    private static final BigDecimal THIRD_MODEL_DTO_PRICE = new BigDecimal("300.40");
    public static final String BRAND = "bmw";
    public static final String BRAND_UPPER_CASE = "BMW";
    public static final String BRAND_LOWER_CASE = "bmw";
    public static final Integer MANUFACTURER_ID = 1;
    public static final String MANUFACTURER_BRAND = "BMW";
    public static final String MANUFACTURER_COUNTRY = "Germany";
    public static final Integer MANUFACTURER_DTO_ID = 1;
    public static final String MANUFACTURER_DTO_BRAND = "BMW";
    public static final String MANUFACTURER_DTO_COUNTRY = "Germany";

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ModelServiceImpl modelService;

    private Model model1;
    private Model model2;
    private Model model3;

    private Manufacturer manufacturer1;

    private ModelDto modelDto1;
    private ModelDto modelDto2;
    private ModelDto modelDto3;

    private ManufacturerDto manufacturerDto1;

    private List<Model> models;

    @BeforeEach
    void setup() {
        model1 = new Model(FIRST_MODEL, FIRST_MODEL_CURRENT_MILEAGE, FIRST_MODEL_PRICE);
        model2 = new Model(SECOND_MODEL, SECOND_MODEL_CURRENT_MILEAGE, SECOND_MODEL_PRICE);
        model3 = new Model(THIRD_MODEL, THIRD_MODEL_CURRENT_MILEAGE, THIRD_MODEL_PRICE);

        manufacturer1 = new Manufacturer(MANUFACTURER_ID, MANUFACTURER_BRAND, MANUFACTURER_COUNTRY);


        modelDto1 = new ModelDto(FIRST_MODEL_DTO, FIRST_MODEL_DTO_CURRENT_MILEAGE, FIRST_MODEL_DTO_PRICE);
        modelDto2 = new ModelDto(SECOND_MODEL_DTO, SECOND_MODEL_DTO_CURRENT_MILEAGE, SECOND_MODEL_DTO_PRICE);
        modelDto3 = new ModelDto(THIRD_MODEL_DTO, THIRD_MODEL_DTO_CURRENT_MILEAGE, THIRD_MODEL_DTO_PRICE);

        manufacturerDto1 = new ManufacturerDto(MANUFACTURER_DTO_ID, MANUFACTURER_DTO_BRAND, MANUFACTURER_DTO_COUNTRY);

        models = Arrays.asList(model1, model2, model3);
    }

    @Test
    void findAllModels() {
        when(modelRepository.findAll()).thenReturn(models);
        when(modelMapper.entityToDto(model1)).thenReturn(modelDto1);
        when(modelMapper.entityToDto(model2)).thenReturn(modelDto2);
        when(modelMapper.entityToDto(model3)).thenReturn(modelDto3);

        List<ModelDto> list = modelService.findAll();

        assertThat(list).hasSize(3).extracting(ModelDto::getModel)
                .containsOnly(FIRST_MODEL_DTO, SECOND_MODEL_DTO, THIRD_MODEL_DTO);
        assertThat(list).hasSize(3).extracting(ModelDto::getCurrentMileage)
                .containsOnly(FIRST_MODEL_DTO_CURRENT_MILEAGE, SECOND_MODEL_DTO_CURRENT_MILEAGE, THIRD_MODEL_DTO_CURRENT_MILEAGE);
    }

    @Test
    void findAllModelsByManufacturerName() {
        when(modelRepository.findAllModelsByManufacturerBrandIgnoreCaseIsLike(BRAND)).thenReturn(models);
        when(modelMapper.entityToDto(model1)).thenReturn(modelDto1);
        when(modelMapper.entityToDto(model2)).thenReturn(modelDto2);
        when(modelMapper.entityToDto(model3)).thenReturn(modelDto3);

        List<ModelDto> listByBrand = modelService.findAllModelsByManufacturerName(BRAND);
        assertThat(listByBrand).hasSize(3).extracting(ModelDto::getModel)
                .containsOnly(FIRST_MODEL_DTO, SECOND_MODEL_DTO, THIRD_MODEL_DTO);
    }

    @Test
    void checkIgnoreCaseParameter() {
        when(modelRepository.findAllModelsByManufacturerBrandIgnoreCaseIsLike(BRAND_UPPER_CASE)).thenReturn(models);
        when(modelRepository.findAllModelsByManufacturerBrandIgnoreCaseIsLike(BRAND_LOWER_CASE)).thenReturn(models);

        List<ModelDto> listByUpperCase = modelService.findAllModelsByManufacturerName(BRAND_UPPER_CASE);
        List<ModelDto> listByLoweCase = modelService.findAllModelsByManufacturerName(BRAND_LOWER_CASE);

        assertThat(listByUpperCase).isEqualTo(listByLoweCase);
    }
}