package com.rm.cms.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "models")
public class Model {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id")
    private VehicleType vehicleType;

    @NotNull
    private String model;

    @Column(name = "production_year", nullable = false)
    private LocalDate productionYear;

    @Column(name = "transmission")
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ModelTransmission modelTransmission;

    @Column(name = "drive_unit")
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ModelDriveUnit driveUnit;

    @Column(name = "engine_type")
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private ModelEngineType engineType;

    @Column(name = "current_mileage")
    @NotNull
    private Long currentMileage;

    @NotNull
    private BigDecimal price;
}
