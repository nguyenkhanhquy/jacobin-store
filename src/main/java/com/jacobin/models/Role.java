package com.jacobin.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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

	@OneToMany(mappedBy = "role")
	private List<User> user;
}
