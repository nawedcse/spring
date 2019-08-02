package com.example.spring.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.example.spring.dto.Status;
import com.example.spring.service.BasicTableDtoConverter;

@GeneratePojoBuilder(
	    intoPackage = "*.builder")
	@Entity
@Table(/*
		 * name = Constant.STATUS_TABLE_NAME, schema = Constant.SCHEMA_NAME
		 */)
public class StatusEntity implements BasicTableDtoConverter<Status>{

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateDto(Status dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEntity(Status dto) {
		// TODO Auto-generated method stub
		
	}

}
