package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.ListDoc;


public interface ListDocRepository extends JpaRepository<ListDoc, Long>{
    ListDoc findByDataTypeId(Long id);
}
