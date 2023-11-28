package com.jacobin.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name =  "ordertrack")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderTrack implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "order_track_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderTrackId;
	
	@Column(name = "status")
	private String status;
}
