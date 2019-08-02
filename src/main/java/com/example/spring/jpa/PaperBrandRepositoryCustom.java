/*
 *
 */
package com.example.spring.jpa;

import com.example.spring.entity.PaperBrandEntity;
import com.example.spring.fetch.PaperBrandFetchOption;
import com.example.spring.filter.PaperBrandFilter;
import com.example.spring.utils.FetchRepositoryCustom;

/**
 * The Interface PaperBrandRepositoryCustom.
 *
 * @author n.alam
 */
public interface PaperBrandRepositoryCustom extends FetchRepositoryCustom<PaperBrandEntity, PaperBrandFilter, PaperBrandFetchOption> {

}
