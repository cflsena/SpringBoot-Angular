package com.example.dev.backend.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.dev.backend.api.commons.BooleanConverter;
import com.example.dev.backend.api.entity.embeddables.AddressEmbeddable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "PERSON")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PersonEntity implements Serializable{
	
	private static final long serialVersionUID = 6679261883100826031L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(min=3, max=50)
	@Column(name = "name")
	private String name;
	
	@Embedded
	private AddressEmbeddable address;
	
	@NotNull
	@Convert(converter = BooleanConverter.class)
	@Column(name = "active", columnDefinition = "char")
	private Boolean active;
}
