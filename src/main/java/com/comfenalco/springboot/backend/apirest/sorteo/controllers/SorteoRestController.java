package com.comfenalco.springboot.backend.apirest.sorteo.controllers;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.comfenalco.springboot.backend.apirest.sorteo.models.dao.IPersonaPremioDao;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Persona;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.PersonaPremio;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Premio;
import com.comfenalco.springboot.backend.apirest.sorteo.models.services.IPersonaPremioService;
import com.comfenalco.springboot.backend.apirest.sorteo.models.services.IPersonaService;
import com.comfenalco.springboot.backend.apirest.sorteo.models.services.IPremioService;
import com.comfenalco.springboot.backend.apirest.sorteo.models.services.PersonaPremioServiceImpl;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/sorteo")
public class SorteoRestController {

	@Autowired
	private IPremioService premioService;

	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private IPersonaPremioService PersonaPremioService;

	@GetMapping("/premios")
	public List<Premio> index() {

		return premioService.findAll();

	}
	
	
	@GetMapping("/ganadores")
	public List<Persona> sorteo() {
				
		List<Premio> premios=premioService.findAll();
		
		boolean bandera=false;
		
		for(int i=0; i<premios.size();i++) {
			
			Premio premio=premios.get(i);
			
			//int totalDePremios= PersonaPremioService.cantidadDePremios(i);
			int premiosDisponibles=premios.get(i).getCantidad();
			
			if (premiosDisponibles>0) {
				for(int x=0;x<premiosDisponibles;x++) {
					
					List<Persona> personasSinPremios=personaService.personasSinPremio();
					
					Random rand = new Random();
				    Persona randomElement = personasSinPremios.get(rand.nextInt(personasSinPremios.size()));
				    
				    PersonaPremio personaPremio= new PersonaPremio();
				    
				    personaPremio.setIdPersona(randomElement.getId());		
				    personaPremio.setIdPremio(premios.get(i).getCodigo());
				    PersonaPremioService.save(personaPremio);
				    bandera=true;
				}
				
			}
			
		}
		
		if (bandera=false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay premios");
			
		}else {
			for(int i=0; i<premios.size();i++) {
				premios.get(i).setCantidad(0);
			}
			return personaService.personaConPremio();
		}
		
		
		
	}

}
