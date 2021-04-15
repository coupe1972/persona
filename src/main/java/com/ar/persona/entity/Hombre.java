package com.ar.persona.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.ar.persona.SexoEnum;
import com.ar.persona.TipoDocumentoEnum;

@Entity
@DiscriminatorValue(value="Hombre")
public class Hombre extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Hombre(TipoDocumentoEnum tipoDocumento, int numeroDocumento, String pais, SexoEnum sexo, String direccion,
			String telefono, String email, int edad, String nacionalidad, Long idPersona, Long hijo) {
		super(tipoDocumento, numeroDocumento, pais, sexo, direccion, telefono, email, edad, nacionalidad);
		this.setIdPersona(idPersona);
		this.setHijo(hijo);
	}

	public Hombre() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
