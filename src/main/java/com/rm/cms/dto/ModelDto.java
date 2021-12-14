package com.rm.cms.dto;

import com.rm.cms.entity.ModelDriveUnit;
import com.rm.cms.entity.ModelEngineType;
import com.rm.cms.entity.ModelTransmission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ModelDto {

    private Long id;

    private ManufacturerDto manufacturer;

    private VehicleTypeDto vehicleType;

    @NotNull
    private String model;

    @NotNull
    private LocalDate productionYear;

    @NotNull
    private ModelTransmission modelTransmission;

    @NotNull
    private ModelDriveUnit driveUnit;

    @NotNull
    private ModelEngineType engineType;

    @NotNull
    private Long currentMileage;

    @NotNull
    private BigDecimal price;

    public ModelDto(String model, Long currentMileage, BigDecimal price) {
        this.model = model;
        this.currentMileage = currentMileage;
        this.price = price;
    }
}
