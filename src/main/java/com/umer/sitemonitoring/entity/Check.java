package com.umer.sitemonitoring.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Check {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	private String url;

}
