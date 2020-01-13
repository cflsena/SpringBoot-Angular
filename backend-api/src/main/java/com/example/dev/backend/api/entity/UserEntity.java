package com.example.dev.backend.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "USERS")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class UserEntity implements Serializable {
	
	private static final long serialVersionUID = -115920255967468757L;
	
	@Id
	private Long id;
	private String name;
	private String email;
	private String password;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="USERS_PERMISSION", joinColumns = @JoinColumn(name="user_id"), inverseJoinColumns = 
	@JoinColumn(name = "permission_id"))
	private List<PermissionEntity> permissions;
}
