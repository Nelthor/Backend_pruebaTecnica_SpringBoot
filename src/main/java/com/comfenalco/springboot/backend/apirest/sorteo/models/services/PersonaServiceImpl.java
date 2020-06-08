package com.comfenalco.springboot.backend.apirest.sorteo.models.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.comfenalco.springboot.backend.apirest.sorteo.models.dao.IPersonaDao;
import com.comfenalco.springboot.backend.apirest.sorteo.models.entity.Persona;

@Service
public class PersonaServiceImpl implements IPersonaService {

	@Autowired
	private IPersonaDao personaDao;

	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {

		return (List<Persona>) personaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Persona findById(int id) {

		return personaDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Persona save(Persona persona) {
		
		return personaDao.save(persona);
		
	}

	@Override
	@Transactional
	public void delete(int id) {
		Optional<Persona> persona = personaDao.findById(id);

		if (persona.isPresent() && persona.get().getPremios().size() > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"No se puede eliminar una persona con un premio ganado");
		} else {
		personaDao.deleteById(id);
		}
	}

	@Override
	public List<Integer> totalPersonas() {
		// TODO Auto-generated method stub
		return personaDao.totalPersonas();
	}

	@Override
	public List<Persona> personasSinPremio() {
		// TODO Auto-generated method stub

		return personaDao.personasSinPremio();
	}

	@Override
	public List<Persona> personaConPremio() {
		// TODO Auto-generated method stub
		return personaDao.personasConPremio();
	}

}
