package com.comfenalco.springboot.backend.apirest.sorteo.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.PersonaPremio;
import java.util.Optional;


public interface IPersonaPremioDao extends CrudRepository<PersonaPremio, Integer>{
	
	@Query("SELECT count(u) FROM PersonaPremio u where u.idPremio=?1")
	Optional<Integer> cantidadDePremios(int id);
	
	
	
}
