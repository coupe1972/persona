package com.ar.persona.response;

import com.ar.persona.business.TypeResponse;

public class PersonaResponse {

	private TypeResponse typeResponse;
	private Object data;
	private String detailError;

	public PersonaResponse(TypeResponse typeResponse, Object data, String detailError) {
		super();
		this.typeResponse = typeResponse;
		this.data = data;
		this.detailError = detailError;
	}

	public PersonaResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TypeResponse getTypeResponse() {
		return typeResponse;
	}

	public void setTypeResponse(TypeResponse typeResponse) {
		this.typeResponse = typeResponse;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getDetailError() {
		return detailError;
	}

	public void setDetailError(String detailError) {
		this.detailError = detailError;
	}

}
