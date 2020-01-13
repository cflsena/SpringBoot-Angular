package com.example.dev.backend.api.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name = "PERMISSION")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class PermissionEntity implements Serializable {

	private static final long serialVersionUID = -5763283542453766144L;

	@Id
	private Long id;
	private String description;

}
