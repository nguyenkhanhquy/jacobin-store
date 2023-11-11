package com.jacobin.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private int userId;
	private String phone;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String address;
//	private Role role;
}
