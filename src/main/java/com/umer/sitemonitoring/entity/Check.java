package com.umer.sitemonitoring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.URL;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Check {

	private static final String NAME_CANNOT_BE_EMPTY = "Name cannot be empty.";
	private static final String URL_CANNOT_BE_EMPTY = "URL cannot be empty.";
	private static final String INVALID_URL = "Invalid url.";

	@Id
	@GeneratedValue
	private int id;

	@Size(min = 1, message = NAME_CANNOT_BE_EMPTY)
	private String name;

	@Size(min = 1, message = URL_CANNOT_BE_EMPTY)
	@URL(message = INVALID_URL)
	private String url;

}
