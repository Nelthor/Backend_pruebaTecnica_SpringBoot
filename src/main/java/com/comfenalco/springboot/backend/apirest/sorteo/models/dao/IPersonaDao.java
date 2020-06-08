package com.comfenalco.springboot.backend.apirest.sorteo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona, Integer>{
	
	@Query("select u.id from Persona u")
	List<Integer> totalPersonas();
	
	@Query("select u from Persona u where u.id not in (select y.idPersona from PersonaPremio y)")
	List<Persona> personasSinPremio();
	
	@Query("select u from Persona u where u.id in (select y.idPersona from PersonaPremio y where y.idPremio is not null)")
	List<Persona> personasConPremio();
	
}
