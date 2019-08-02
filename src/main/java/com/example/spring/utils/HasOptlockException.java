package com.example.spring.utils;

import java.text.MessageFormat;

public class HasOptlockException extends Exception {

	public HasOptlockException(Class<? extends HasId> dtoClass,
            @SuppressWarnings("rawtypes") Class<? extends BasicTableDtoConverter> entityClass) {
        super(MessageFormat.format(
                "dto ({0}) and entity ({1}) do not both implement HasOptlock interface, please fix either way",
                dtoClass, entityClass));
    }
}
