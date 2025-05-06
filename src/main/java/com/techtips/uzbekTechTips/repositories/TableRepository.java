package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.TableDoc;

public interface TableRepository extends JpaRepository<TableDoc, Long>{
    TableDoc findByDataTypeId(Long id);
}
