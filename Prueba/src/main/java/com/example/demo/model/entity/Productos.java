package com.example.demo.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="productos")

public class Productos implements Serializable {
	/**
	 * 
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String sKU;
	private String fERT;
	private String modelo;
	private String nSerie;
	@Column(name="fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;
		

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getsKU() {
		return sKU;
	}

	public void setsKU(String sKU) {
		this.sKU = sKU;
	}

	public String getfERT() {
		return fERT;
	}

	public void setfERT(String fERT) {
		this.fERT = fERT;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getnSerie() {
		return nSerie;
	}

	public void setnSerie(String nSerie) {
		this.nSerie = nSerie;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	
	private static final long serialVersionUID = 1L;
	
	
}
