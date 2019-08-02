package com.example.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring.entity.StatusEntity;

@Repository
public interface StatusRepository extends JpaRepository<StatusEntity, Long>, StatusRepositoryCustom {

}
