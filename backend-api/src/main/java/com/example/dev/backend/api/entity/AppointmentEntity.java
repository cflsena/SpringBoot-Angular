package com.example.dev.backend.api.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.dev.backend.api.enums.AppointmentTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "APPOINTMENT")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class AppointmentEntity implements Serializable {

	private static final long serialVersionUID = -1413307456393431283L;
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@NotBlank
	@Size(min=3, max=50)
	private String description;
	
	@NotNull
	private LocalDate payDate;
	
	private LocalDate dueDate;
	
	@NotNull
	private BigDecimal cost;
	
	private String observation;
	
	@Enumerated(EnumType.STRING)
	private AppointmentTypeEnum appointmentType;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	@ManyToOne
	@JoinColumn(name="person_id")
	private PersonEntity person;
}
