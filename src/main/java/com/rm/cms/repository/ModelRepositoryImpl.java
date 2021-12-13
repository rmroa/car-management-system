package com.rm.cms.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.rm.cms.entity.Model;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

import static com.rm.cms.entity.QModel.model1;

@RequiredArgsConstructor
public class ModelRepositoryImpl implements ModelRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public List<Model> findByModelFilter(String transmission, String driveUnit) {
        return new JPAQuery<Model>(entityManager)
                .select(model1)
                .from(model1)
                .where(model1.modelTransmission.stringValue().containsIgnoreCase(transmission)
                        .and(model1.driveUnit.stringValue().containsIgnoreCase(driveUnit)))
                .fetch();
    }
}