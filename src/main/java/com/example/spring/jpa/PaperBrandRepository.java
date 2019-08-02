/*
 *
 */
package com.example.spring.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The Interface PaperBrandRepository.
 *
 * @author n.alam
 */
@Repository
public interface PaperBrandRepository extends JpaRepository<com.example.spring.entity.PaperBrandEntity, Long>, PaperBrandRepositoryCustom {

}
