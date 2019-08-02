package com.example.spring.utils;

public interface FetchTableDtoConverter <DTO extends HasId, FETCHOPTIONS> extends BasicTableDtoConverter<DTO>{

	 void updateDto(DTO dto, FETCHOPTIONS fetchOptions);
}
