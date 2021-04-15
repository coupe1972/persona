package com.ar.persona.request;

import com.ar.persona.SexoEnum;
import com.ar.persona.TipoDocumentoEnum;


public class PersonaRequest {

	private TipoDocumentoEnum tipoDocumento;
	
	private int numeroDocumento;

	private String pais;

	private SexoEnum sexo;
	
	private String direccion;
	
	private String telefono;
	
	private String email;
	
	private int edad;
	
	private String nacionalidad;
	
	public PersonaRequest(TipoDocumentoEnum tipoDocumento, int numeroDocumento, String pais, SexoEnum sexo,
			String direccion, String telefono, String email, int edad, String nacionalidad) {
		super();
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.pais = pais;
		this.sexo = sexo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
	}



	public PersonaRequest() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TipoDocumentoEnum getTipoDocumento() {
		return tipoDocumento;
	}



	public void setTipoDocumento(TipoDocumentoEnum tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}



	public int getNumeroDocumento() {
		return numeroDocumento;
	}



	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}



	public String getPais() {
		return pais;
	}



	public void setPais(String pais) {
		this.pais = pais;
	}



	public SexoEnum getSexo() {
		return sexo;
	}



	public void setSexo(SexoEnum sexo) {
		this.sexo = sexo;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public int getEdad() {
		return edad;
	}



	public void setEdad(int edad) {
		this.edad = edad;
	}



	public String getNacionalidad() {
		return nacionalidad;
	}



	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}


}
