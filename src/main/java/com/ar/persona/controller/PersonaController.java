package com.ar.persona.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ar.persona.request.PersonaFullRequest;
import com.ar.persona.request.PersonaRequest;
import com.ar.persona.response.PersonaResponse;
import com.ar.persona.service.PersonaService;


@RestController
public class PersonaController {

	@Autowired
	private PersonaService personaService;
		
	@RequestMapping(value= "persona/add", method = RequestMethod.POST)
	public PersonaResponse add(@RequestBody PersonaRequest request)
	{
		 return personaService.addPersona(request);
	}
	
	@RequestMapping(value= "persona/get/{idPersona}", method = RequestMethod.GET)
	public PersonaResponse get(@PathVariable("idPersona") long idPersona)
	{
		 return personaService.getPersona(idPersona);
	}
	
	@RequestMapping(value= "persona/update", method = RequestMethod.PUT)
	public PersonaResponse update(@RequestBody PersonaFullRequest request)
	{
		 return personaService.updatePersona(request);
	}
	
	@RequestMapping(value= "persona/delete/{idPersona}", method = RequestMethod.DELETE)
	public PersonaResponse delete(@PathVariable("idPersona") long idPersona)
	{
		 return personaService.deletePersona(idPersona);
	}
	
	@RequestMapping(value= "persona/estadisticas", method = RequestMethod.GET)
	public PersonaResponse getEstadisticas()
	{
		 return personaService.getEstadisticas();
	}
	
	@RequestMapping(value= "persona/{idPadre}/padre/{idHijo}", method = RequestMethod.POST)
	public PersonaResponse padreDe(@PathVariable("idPadre") Long idPadre, @PathVariable("idHijo") Long idHijo)
	{
		 return personaService.padreDe(idPadre, idHijo);
	}
	
	@RequestMapping(value= "persona/relaciones/{id1}/{id2}", method = RequestMethod.GET)
	public PersonaResponse relaciones(@PathVariable("id1") Long id1, @PathVariable("id2") Long id2)
	{
		 return personaService.relaciones(id1, id2);
	}
}
