package com.comfenalco.springboot.backend.apirest.sorteo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.comfenalco.springboot.backend.apirest.sorteo.models.dao.IPersonaDao;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Persona;
import com.comfenalco.springboot.backend.apirest.sorteo.models.services.IPersonaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/prueba")
public class PersonaRestController {
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired 
	private IPersonaDao personaDao;
	
	@GetMapping("/personas")
	public List<Persona> index(){
		return personaService.findAll();
	}
	@GetMapping("/personas/{id}")
	public Persona show(@PathVariable int id) {
		return personaService.findById(id);
	}
	
	@PostMapping("/personas")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona create(@RequestBody Persona persona) {
		Optional<Persona> personaAValidar = personaDao.findById(persona.getId());

		if (personaAValidar.isPresent()){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se puede Crear una persona con un mismo número de identificación");
		}else {
		return personaService.save(persona);
		}
	}
	
	@PutMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona update(@RequestBody Persona persona, @PathVariable int id) {
		
		Persona personaActual=personaService.findById(id);
		
		personaActual.setApellido(persona.getApellido());
		personaActual.setFechaNacimiento(persona.getFechaNacimiento());
		personaActual.setNombre(persona.getNombre());
		personaActual.setId(persona.getId());
		personaActual.setTipoDocumento(persona.getTipoDocumento());
		
		return personaService.save(personaActual);
		
	}
	
	@DeleteMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable int id) {
		personaService.delete(id);
	}
	
	

}
