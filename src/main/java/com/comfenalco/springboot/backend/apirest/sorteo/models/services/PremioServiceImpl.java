package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comfenalco.springboot.backend.apirest.sorteo.models.dao.IPremioDao;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Premio;

@Service
public class PremioServiceImpl implements IPremioService {
	
	@Autowired
	private IPremioDao premioDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Premio> findAll() {

		return (List<Premio>) premioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Premio findById(int id) {
		
		return premioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Premio save(Premio premio) {

		return premioDao.save(premio);
	}

	@Override
	@Transactional
	public void delete(int id) {
		
		premioDao.deleteById(id);
	}
/*
	@Override
	public List<Integer> findPremios() {
		// TODO Auto-generated method stub
		return premioDao.findPremios();
	}
*/


	@Override
	public int cantidad(int id) {
		// TODO Auto-generated method stub
		return premioDao.cantidad(id);
	}

	@Override
	public int cantidadDePremios() {
		// TODO Auto-generated method stub
		return premioDao.cantidadDePremios();
	}

	

	
}
