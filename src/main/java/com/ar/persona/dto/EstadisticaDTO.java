package com.ar.persona.dto;

public class EstadisticaDTO {

	private Integer cantidad_mujeres;
	private Integer cantidad_hombres;
	private Integer cantidad_argentinos;
	
	public EstadisticaDTO(Integer cantidad_mujeres, Integer cantidad_hombres, Integer cantidad_argentinos) {
		super();
		this.cantidad_mujeres = cantidad_mujeres;
		this.cantidad_hombres = cantidad_hombres;
		this.cantidad_argentinos = cantidad_argentinos;
	}

	public EstadisticaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCantidad_mujeres() {
		return cantidad_mujeres;
	}

	public void setCantidad_mujeres(Integer cantidad_mujeres) {
		this.cantidad_mujeres = cantidad_mujeres;
	}

	public Integer getCantidad_hombres() {
		return cantidad_hombres;
	}

	public void setCantidad_hombres(Integer cantidad_hombres) {
		this.cantidad_hombres = cantidad_hombres;
	}

	public Integer getCantidad_argentinos() {
		return cantidad_argentinos;
	}

	public void setCantidad_argentinos(Integer cantidad_argentinos) {
		this.cantidad_argentinos = cantidad_argentinos;
	}
	
	
}
