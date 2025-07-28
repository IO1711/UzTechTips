package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.ImageWassabi;


public interface ImageWassabiRepository extends JpaRepository<ImageWassabi, Long>{
    ImageWassabi findByDataTypeId(Long id);   
}
