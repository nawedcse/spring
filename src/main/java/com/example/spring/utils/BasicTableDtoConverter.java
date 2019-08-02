package com.example.spring.utils;

public interface BasicTableDtoConverter<DTO extends HasId> extends HasId {
	
	void updateDto(DTO dto);
	
	 void updateEntity(DTO dto);
}
