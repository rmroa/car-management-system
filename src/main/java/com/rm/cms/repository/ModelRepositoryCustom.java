package com.rm.cms.repository;

import com.rm.cms.entity.Model;

import java.util.List;

public interface ModelRepositoryCustom {

    List<Model> findByModelFilter(String transmission, String driveUnit);
}
