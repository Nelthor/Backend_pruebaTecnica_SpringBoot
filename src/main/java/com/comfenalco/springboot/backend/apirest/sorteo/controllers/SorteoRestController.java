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
	public List<Persona> ganador() {
		
		
		List<Premio> premios=premioService.findAll();
		
		boolean bandera=false;
		
		for(int i=0; i<premios.size();i++) {
			
			Premio premio=premios.get(i);
			
			int totalDePremios= PersonaPremioService.cantidadDePremios(i);
			int premiosDisponibles=premio.getCantidad()-totalDePremios;
			
			if (premiosDisponibles>=0) {
				for(int x=0;x<premiosDisponibles;x++) {
					
					List<Persona> personasSinPremios=personaService.personasSinPremio();
					
					Random rand = new Random();
				    Persona randomElement = personasSinPremios.get(rand.nextInt(personasSinPremios.size()));
				    
				    PersonaPremio personaPremio= new PersonaPremio();
				    
				    personaPremio.setIdPersona(randomElement.getId());		
				    personaPremio.setIdPremio(premio.getCodigo());
				    PersonaPremioService.save(personaPremio);
				    bandera=true;
				}
			}
			
		}
		if (bandera=false) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,"No hay premios");
			
		}else {
			return personaService.personaConPremio();
		}
		
		
		/*
		int encontrado = 0;

		for (int i = 0; i < premioService.findPremios().size(); i++) {

			int nroAleatorio, premio;
			int n = premioService.findPremios().get(i);

			if (premioService.cantidad(n) > 0) {

				boolean bandera = false;
				while (!bandera) {
					nroAleatorio = (int) (Math.random() * personaService.totalPersonas().size() + 1);

					premio = (int) personaService.ganador(nroAleatorio);

					if (premio > 0) {
					} else {

						Persona personaActual = personaService.findById(nroAleatorio);
						
						
						bandera = true;

					}

				}
				encontrado = 1;
			} else {

			}

		}
		if (encontrado==0) {
			System.out.println("no hay regalos disponibles");
		}¨¨¨*/
	}

}
