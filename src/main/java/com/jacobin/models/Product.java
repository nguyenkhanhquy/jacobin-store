package com.jacobin.models;

import java.io.Serializable;
import java.util.Locale.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int productid;
	private Category category;
	private String title;
	private String size;
	private String description;
	private double price;
}
