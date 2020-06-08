package com.comfenalco.springboot.backend.apirest.sorteo.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Premio;

public interface IPremioDao extends CrudRepository<Premio, Integer>{
	
	@Query("select u.codigo from Premio u")
	List<Premio> findPremios();
	
	@Query("select u.cantidad from Premio u where u.codigo=?1")
	int cantidad(int id);
	
	@Query("select SUM(u.cantidad) from Premio u")
	int cantidadDePremios();
	
	
}
