package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.Data;
import java.util.List;

public interface DataRepository extends JpaRepository<Data, Long>{
    List<Data> findByTopicNameId(Long id);
}
