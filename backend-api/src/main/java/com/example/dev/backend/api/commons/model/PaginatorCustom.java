package com.example.dev.backend.api.commons.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PaginatorCustom implements Serializable {
	
	private static final long serialVersionUID = -7877119690587514532L;

	private Integer pageIndex;
	private Integer pageSize;
	private Integer length;
}
