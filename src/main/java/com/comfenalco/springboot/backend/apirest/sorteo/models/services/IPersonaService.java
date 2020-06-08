package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import java.util.List;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Persona;


public interface IPersonaService {
	
	public List<Persona> findAll();
	
	public Persona findById(int id);
	
	public Persona save(Persona persona);
	
	public void delete(int id);
	
	public List<Integer> totalPersonas();
	
	public List<Persona> personasSinPremio();
	
	public List<Persona> personaConPremio();
}
