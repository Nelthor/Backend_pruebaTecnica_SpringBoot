package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import java.util.List;

import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Premio;


public interface IPremioService {
	
	public List<Premio> findAll();
	
	public Premio findById(int id);
	
	public Premio save(Premio premio);
	
	public void delete(int id);
	
	
	public int cantidad(int id);
	
	public int cantidadDePremios();
	
}
