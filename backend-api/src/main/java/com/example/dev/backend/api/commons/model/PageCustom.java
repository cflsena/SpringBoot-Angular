package com.example.dev.backend.api.commons.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PageCustom extends PaginatorCustom {

	private static final long serialVersionUID = -5413407534459235161L;

	private List<?> listObject;
}
