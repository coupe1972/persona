package com.ar.persona.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.persona.SexoEnum;
import com.ar.persona.business.TypeResponse;
import com.ar.persona.dto.DiferentesTiposPersonaDTO;
import com.ar.persona.dto.EstadisticaDTO;
import com.ar.persona.entity.Hombre;
import com.ar.persona.entity.Mujer;
import com.ar.persona.entity.Persona;
import com.ar.persona.repository.HombreRepository;
import com.ar.persona.repository.MujerRepository;
import com.ar.persona.repository.PersonaRepository;
import com.ar.persona.request.PersonaFullRequest;
import com.ar.persona.request.PersonaRequest;
import com.ar.persona.response.PersonaResponse;
import com.ar.persona.response.PersonaResponseBuilder;

@Service
public class PersonaService {

	private static Log log = LogFactory.getLog(PersonaService.class);

	@Autowired
	private PersonaRepository personaRepository;
	
	@Autowired
	private HombreRepository hombreRepository;
	
	@Autowired
	private MujerRepository mujerRepository;

	private PersonaResponseBuilder builder = new PersonaResponseBuilder();

	public synchronized PersonaResponse addPersona(PersonaRequest personaRequest) {
		
		Persona persona = new Persona(personaRequest.getTipoDocumento(), personaRequest.getNumeroDocumento(),
				personaRequest.getPais(), personaRequest.getSexo(), personaRequest.getDireccion(),
				personaRequest.getTelefono(), personaRequest.getEmail(), personaRequest.getEdad(),
				personaRequest.getNacionalidad());

		Persona pers = null;
		try {
			PersonaResponse resp = validarPersona(persona);

			if (resp != null) {
				return resp;
			}

			if (personaRequest.getSexo().equals(SexoEnum.MASCULINO)) {
				pers = hombreRepository.save(persona.getHombre());
			}else {
				pers = mujerRepository.save(persona.getMujer());
			}
			
		} catch (Exception e) {
			log.debug("ERROR in addPersona: " + e.getMessage());

			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError(e.getCause().toString())
					.build();
		}

		return builder.
				setTypeResponse(TypeResponse.SUCCESS).
				setData(pers).
				setDetailError(null).
				build();
	}

	private PersonaResponse validarPersona(Persona persona) {
		Persona pers = personaRepository.findByTipoDocumentoAndNumeroDocumentoAndPaisAndSexo(persona.getTipoDocumento(),
				persona.getNumeroDocumento(), persona.getPais(), persona.getSexo());

		PersonaResponse resp = null;
		if (pers != null) {
			resp = builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("La Persona ya existe")
					.build();
			
		}else if ( (persona.getDireccion() == null || persona.getDireccion().isEmpty()) &&
				   (persona.getEmail() == null || persona.getEmail().isEmpty()) &&
				   (persona.getTelefono() == null || persona.getTelefono().isEmpty()) ){
			
			resp = builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("La Persona debe poseer al menos un dato de contacto")
					.build();
			
		}else if(persona.getEdad() < 18) {
			resp = builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("La Persona debe tener al menos 18 años")
					.build();
		}

		return resp;
	}
	
	public synchronized PersonaResponse getEstadisticas() {
		List<DiferentesTiposPersonaDTO> list= personaRepository.getCantidadMujeresHombres();
		List<EstadisticaDTO> estadisticas= new ArrayList<EstadisticaDTO>();
		EstadisticaDTO estadisticaDTO= new EstadisticaDTO();;
		
		for (DiferentesTiposPersonaDTO dif: list) {
			if (dif.getSexo().equals("MASCULINO")) {
				estadisticaDTO.setCantidad_hombres(dif.getCantidad());
				
			}else if(dif.getSexo().equals("FEMENINO")){
				estadisticaDTO.setCantidad_mujeres(dif.getCantidad());
			}
		}
		
		Integer cantidadArgentinos= personaRepository.countByPaisIgnoreCase("Argentina");
		estadisticaDTO.setCantidad_argentinos(cantidadArgentinos);
		
		estadisticas.add(estadisticaDTO);
			
		return builder.
				setTypeResponse(TypeResponse.SUCCESS).
				setData(estadisticas).
				setDetailError(null)
				.build();
	}

	public synchronized PersonaResponse getPersona(Long idPersona) {
		Optional<Persona> opt = null;
		try {
			opt = personaRepository.findById(idPersona);
		} catch (Exception e) {
			log.debug("ERROR in getPersona: " + e.getMessage());

			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError(e.getCause().toString())
					.build();
		}

		if (opt.isPresent()) {
			return builder.
					setTypeResponse(TypeResponse.SUCCESS).
					setData(opt.get()).
					setDetailError(null).
					build();
		} else {
			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("Persona inexistente")
					.build();
		}
	}

	public synchronized PersonaResponse updatePersona(PersonaFullRequest personaRequest) {
		Persona pers = null;
		try {
			Optional<Persona> opt = personaRepository.findById(personaRequest.getIdPersona());

			if (opt.isPresent()) {
				Persona persona = opt.get();

				persona.setDireccion(personaRequest.getDireccion());
				persona.setEdad(personaRequest.getEdad());
				persona.setEmail(personaRequest.getEmail());
				persona.setNacionalidad(personaRequest.getNacionalidad());
				persona.setNumeroDocumento(personaRequest.getNumeroDocumento());
				persona.setPais(personaRequest.getPais());
				persona.setSexo(personaRequest.getSexo());
				persona.setTelefono(personaRequest.getTelefono());
				persona.setTipoDocumento(personaRequest.getTipoDocumento());

				pers = personaRepository.save(persona);

				return builder.
						setTypeResponse(TypeResponse.SUCCESS).
						setData(pers).
						setDetailError(null).
						build();

			} else {
				return builder.
						setTypeResponse(TypeResponse.ERROR).
						setData(null).
						setDetailError("Persona inexistente")
						.build();
			}

		} catch (Exception e) {
			log.debug("ERROR in updatePersona: " + e.getMessage());

			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError(e.getCause().toString())
					.build();
		}
	}

	public synchronized PersonaResponse deletePersona(Long idPersona) {
		Optional<Persona> opt = null;
		try {
			opt = personaRepository.findById(idPersona);

			if (opt.isPresent()) {
				personaRepository.delete(opt.get());

				return builder.
						setTypeResponse(TypeResponse.SUCCESS).
						setData(opt.get()).
						setDetailError(null).
						build();
			} else {
				return builder.
						setTypeResponse(TypeResponse.ERROR).
						setData(null).
						setDetailError("Persona inexistente")
						.build();
			}

		} catch (Exception e) {
			log.debug("ERROR in deletePersona: " + e.getMessage());

			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError(e.getCause().toString())
					.build();
		}
	}
	
	public synchronized PersonaResponse padreDe(Long idPadre, Long idHijo) {
		 
		Optional<Persona> optPadre= personaRepository.findById(idPadre);
		Optional<Persona> optHijo= personaRepository.findById(idHijo);
		
		if (optPadre.isPresent() && optPadre.get().getSexo().name().equals("MASCULINO")) {
			Hombre padre= (Hombre)optPadre.get();
			
			if(optHijo.isPresent()) {
				Persona persona= optHijo.get();
				
				padre.setHijo(persona.getIdPersona());
				
				personaRepository.save(padre);
				
				return builder.
						setTypeResponse(TypeResponse.SUCCESS).
						setData(idPadre + "es padre de "+ idHijo).
						setDetailError(null).
						build();
			}
		}else {
			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("EL Padre no existe o es una Mujer")
					.build();
		}
		
		return builder.
				setTypeResponse(TypeResponse.ERROR).
				setData(null).
				setDetailError("EL Padre o el Hijo no existen")
				.build();
	}
	
	
	public synchronized PersonaResponse relaciones(Long id1, Long id2) {
		Optional<Persona> opt1= personaRepository.findById(id1);
		Optional<Persona> opt2= personaRepository.findById(id2);
		
		if(opt1.isPresent() ) {
			Persona p1= opt1.get();
			
			if(opt2.isPresent() ) {
				Persona p2= opt2.get();
			
				if ( (p1.getHermano() != null) &&   p1.getHermano() == p2.getIdPersona() ||
				 	 (p1.getHermana() != null) &&   p1.getHermana() == p2.getIdPersona()	) {
					return builder.
							setTypeResponse(TypeResponse.SUCCESS).
							setData("Herman@").
							setDetailError(null).
							build();
				
				}else if (p1.getPadre() != null){
					
					Optional<Persona> optPadre= personaRepository.findById(p1.getPadre());
					if(optPadre.isPresent() ) {
						Persona padre= optPadre.get();
						
						if (padre.getHermano() != null) {
							Optional<Persona> optHermano=  personaRepository.findById(padre.getHermano());
							if(optHermano.isPresent() ) {
								Hombre hermano= optHermano.get().getHombre();
								
								if (hermano.getIdPersona() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Tio").
											setDetailError(null).
											build();
								}
								
								if (hermano.getHijo() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Primo").
											setDetailError(null).
											build();
								}
							}
						}else if (padre.getHermana() != null){
							Optional<Persona> optHermana=  personaRepository.findById(padre.getHermana());
							
							if(optHermana.isPresent() ) {
								Mujer hermana= optHermana.get().getMujer();
								
								if (hermana.getIdPersona() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Tia").
											setDetailError(null).
											build();
								}
								
								if (hermana.getHijo() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Prima").
											setDetailError(null).
											build();
								}
							}
					    }
					}
					
					return builder.
							setTypeResponse(TypeResponse.ERROR).
							setData(null).
							setDetailError("No existe ningún parentesco")
							.build();
					
				} else if (p1.getMadre() != null) {
					Optional<Persona> optMadre= personaRepository.findById(p1.getMadre());
					if(optMadre.isPresent() ) {
						Persona madre= optMadre.get();
						
						if (madre.getHermano() != null) {
							Optional<Persona> optHermano=  personaRepository.findById(madre.getHermano());
							if(optHermano.isPresent() ) {
								Hombre hermano= optHermano.get().getHombre();
								
								if (hermano.getIdPersona() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Tio").
											setDetailError(null).
											build();
								}
								
								if (hermano.getHijo() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Primo").
											setDetailError(null).
											build();
								}
							}
						}else if (madre.getHermana() != null){
							Optional<Persona> optHermana=  personaRepository.findById(madre.getHermana());
							
							if(optHermana.isPresent() ) {
								Mujer hermana= optHermana.get().getMujer();
								
								if (hermana.getIdPersona() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Tia").
											setDetailError(null).
											build();
								}
								
								if (hermana.getHijo() == p2.getIdPersona()) {
									return builder.
											setTypeResponse(TypeResponse.SUCCESS).
											setData("Prima").
											setDetailError(null).
											build();
								}
							}
					    }
					}
					
					return builder.
							setTypeResponse(TypeResponse.ERROR).
							setData(null).
							setDetailError("No existe ningún parentesco")
							.build();
				}
				return builder.
						setTypeResponse(TypeResponse.ERROR).
						setData(null).
						setDetailError("No existe ningún parentesco")
						.build();
			}else {
				return builder.
						setTypeResponse(TypeResponse.ERROR).
						setData(null).
						setDetailError("La Persona id2 No existe")
						.build();
			}
		}else {
			return builder.
					setTypeResponse(TypeResponse.ERROR).
					setData(null).
					setDetailError("La Persona id1 No existe")
					.build();
		}
	}
						
		
	

}
