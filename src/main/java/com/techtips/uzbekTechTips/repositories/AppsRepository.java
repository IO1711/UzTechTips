package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.techtips.uzbekTechTips.model.Apps;

public interface AppsRepository extends JpaRepository<Apps, Long> {

    Apps findByAppName(String app);

    Apps findById(long id);
}
