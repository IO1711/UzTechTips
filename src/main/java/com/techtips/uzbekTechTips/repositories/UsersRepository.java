package com.techtips.uzbekTechTips.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techtips.uzbekTechTips.model.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{
    Users findByUsername(String username);
}
