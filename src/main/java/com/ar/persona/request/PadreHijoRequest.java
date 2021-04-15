package com.ar.persona.request;

public class PadreHijoRequest {

	private Long idPadre;
	private Long idHijo;
	
	public PadreHijoRequest(Long idPadre, Long idHijo) {
		super();
		this.idPadre = idPadre;
		this.idHijo = idHijo;
	}
	public PadreHijoRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Long idPadre) {
		this.idPadre = idPadre;
	}
	public Long getIdHijo() {
		return idHijo;
	}
	public void setIdHijo(Long idHijo) {
		this.idHijo = idHijo;
	}
	
	
}
