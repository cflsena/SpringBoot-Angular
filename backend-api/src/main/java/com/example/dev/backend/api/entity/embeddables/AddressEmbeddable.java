package com.example.dev.backend.api.entity.embeddables;

import javax.persistence.Embeddable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class AddressEmbeddable {
	
	private String street;
	private String numberAddress;
	private String complement;
	private String neighborhood;
	private String zipCode;
	private String city;
	private String state;
}
