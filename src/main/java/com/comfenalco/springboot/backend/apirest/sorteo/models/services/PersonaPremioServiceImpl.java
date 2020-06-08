package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.comfenalco.springboot.backend.apirest.sorteo.models.dao.IPersonaPremioDao;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.PersonaPremio;

@Service
public class PersonaPremioServiceImpl implements IPersonaPremioService {
	
	@Autowired
	private IPersonaPremioDao personaPremioDao;
	
	
	@Override
	public int cantidadDePremios(int id) {
		// TODO Auto-generated method stub
		return personaPremioDao.cantidadDePremios(id).orElse(0);
	}

	@Override
	public PersonaPremio save(PersonaPremio personaPremio) {
		// TODO Auto-generated method stub
		return personaPremioDao.save(personaPremio);
	}

}
