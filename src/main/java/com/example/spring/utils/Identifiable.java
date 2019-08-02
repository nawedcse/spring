package com.example.spring.utils;

import java.io.Serializable;

@FunctionalInterface
public interface Identifiable extends Serializable {
	Long getId();
}
