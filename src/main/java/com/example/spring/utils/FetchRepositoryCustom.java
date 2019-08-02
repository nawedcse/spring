package com.example.spring.utils;

import java.util.List;

import com.example.spring.fetch.FetchAllOptions;
import com.example.spring.jpa.BasicRepositoryCustom;

public interface FetchRepositoryCustom<ENTITY, FILTER, FETCHOPTIONS extends FetchAllOptions>
		extends BasicRepositoryCustom<ENTITY, FILTER> {

	List<ENTITY> findAll(FILTER filter, FETCHOPTIONS fetchOptions);

	ENTITY findOne(FILTER filter, FETCHOPTIONS fetchOptions);

}
