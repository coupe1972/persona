package com.ar.persona.request;

import com.ar.persona.SexoEnum;
import com.ar.persona.TipoDocumentoEnum;

public class PersonaFullRequest extends PersonaRequest{

	private Long idPersona;

	public PersonaFullRequest(TipoDocumentoEnum tipoDocumento, int numeroDocumento, String pais, SexoEnum sexo,
			String direccion, String telefono, String email, int edad, String nacionalidad, Long idPersona) {
		super(tipoDocumento, numeroDocumento, pais, sexo, direccion, telefono, email, edad, nacionalidad);
		this.idPersona = idPersona;
	}

	public PersonaFullRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
	}
	
	
	
}
