package com.rm.cms.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Setter
@Getter
@Builder
public class ManufacturerDto {

    private Integer id;

    @NotNull
    private String brand;

    @NotNull
    private String country;

    public ManufacturerDto(Integer id, String brand, String country) {
        this.id = id;
        this.brand = brand;
        this.country = country;
    }
}
