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
public class OrderTrack implements Serializable{

	private static final long serialVersionUID = 1L;

	private int orderTrackId;
	private String status;
}
