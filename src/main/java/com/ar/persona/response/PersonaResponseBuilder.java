package com.ar.persona.response;

import com.ar.persona.business.TypeResponse;

public class PersonaResponseBuilder {

	private TypeResponse typeResponse;
	private Object data;
	private String detailError;

	public PersonaResponseBuilder(TypeResponse typeResponse, Object data, String detailError) {
		super();
		this.typeResponse = typeResponse;
		this.data = data;
		this.detailError = detailError;
	}

	public PersonaResponseBuilder() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonaResponseBuilder setTypeResponse(TypeResponse typeResponse) {
		this.typeResponse = typeResponse;
		return this;
	}

	public PersonaResponseBuilder setData(Object data) {
		this.data = data;
		return this;
	}

	public PersonaResponseBuilder setDetailError(String detailError) {
		this.detailError = detailError;
		return this;
	}
	
	public PersonaResponse build() {
		return new PersonaResponse(typeResponse, data, detailError);
	}
}
