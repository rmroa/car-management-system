package com.rm.cms.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ManufacturerDto {

    private Integer id;

    @NotNull
    private String brand;

    @NotNull
    private String country;
}
