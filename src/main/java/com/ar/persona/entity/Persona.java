package com.ar.persona.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.ar.persona.SexoEnum;
import com.ar.persona.TipoDocumentoEnum;


@Entity
@DiscriminatorColumn(name="tipo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Persona implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_PERSONA")
	private Long idPersona;
	
	@Column(name = "TIPO_DOCUMENTO")
	@Enumerated(EnumType.STRING)
	private TipoDocumentoEnum tipoDocumento;
	
	@Column(name = "NRO_DOCUMENTO")
	private int numeroDocumento;

	@Column(name = "PAIS")
	private String pais;

	@Column(name = "SEXO")
	@Enumerated(EnumType.STRING)
	private SexoEnum sexo;
	
	@Column(name = "DIRECCION")
	private String direccion;
	
	@Column(name = "TELEFONO")
	private String telefono;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "EDAD")
	private int edad;
	
	@Column(name = "NACIONALIDAD")
	private String nacionalidad;
	
	@Column(name = "PADRE")
	private Long padre;
	
	@Column(name = "MADRE")
	private Long madre;
	
	@Column(name = "HERMANO")
	private Long hermano;
	
	@Column(name = "HERMANA")
	private Long hermana;

	@Column(name = "hijo")
	public Long hijo;

	public Persona(TipoDocumentoEnum tipoDocumento, int numeroDocumento, String pais, SexoEnum sexo, String direccion,
			String telefono, String email, int edad, String nacionalidad) {
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


	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getIdPersona() {
		return idPersona;
	}


	public void setIdPersona(Long idPersona) {
		this.idPersona = idPersona;
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

	public Long getPadre() {
		return padre;
	}

	public void setPadre(Long padre) {
		this.padre = padre;
	}

	public Long getMadre() {
		return madre;
	}

	public void setMadre(Long madre) {
		this.madre = madre;
	}
	
	
	
	public Long getHermano() {
		return hermano;
	}


	public void setHermano(Long hermano) {
		this.hermano = hermano;
	}


	public Long getHermana() {
		return hermana;
	}


	public void setHermana(Long hermana) {
		this.hermana = hermana;
	}


	public Long getHijo() {
		return hijo;
	}


	public void setHijo(Long hijo) {
		this.hijo = hijo;
	}


	public Hombre getHombre() {
		return new Hombre(tipoDocumento,  numeroDocumento,  pais,  sexo,  direccion,
				 telefono,  email,  edad,  nacionalidad, idPersona, hijo);
	}
	
	public Mujer getMujer() {
		return new Mujer(tipoDocumento,  numeroDocumento,  pais,  sexo,  direccion,
				 telefono,  email,  edad,  nacionalidad, idPersona, hijo);
	}
	
}
