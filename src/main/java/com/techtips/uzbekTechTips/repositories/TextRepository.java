package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;


import com.techtips.uzbekTechTips.model.Text;

public interface TextRepository extends JpaRepository<Text, Long>{
    Text findByDataTypeId(Long id);
}
