package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.PersonaPremio;

public interface IPersonaPremioService {
	
	public int cantidadDePremios(int id);
	
	public PersonaPremio save(PersonaPremio personaPremio);
	
}
