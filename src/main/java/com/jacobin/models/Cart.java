package com.jacobin.models;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Cart implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<LineItem> items;
}
