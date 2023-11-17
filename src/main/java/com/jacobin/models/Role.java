package com.jacobin.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "role")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @Column(name = "role_id")
    private int roleId;
	
	@Column(name = "role_name")
	private String roleName;
}
