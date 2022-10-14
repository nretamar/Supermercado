package com.nicolasretamar.entrevista.models;

import javax.persistence.*;
@Entity
@Table(name = "users", uniqueConstraints = { @UniqueConstraint(columnNames = "dni") })
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	private Integer dni;
	
	private boolean vip;

	
	public User() {
		vip = false;
	}

	public User(Integer dni, boolean vip) {
		this.dni = dni;
		this.vip = vip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDni() {
		return dni;
	}

	public void setDni(Integer dni) {
		this.dni = dni;
	}

	public boolean isVip() {
		return vip;
	}

	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	
	
}
