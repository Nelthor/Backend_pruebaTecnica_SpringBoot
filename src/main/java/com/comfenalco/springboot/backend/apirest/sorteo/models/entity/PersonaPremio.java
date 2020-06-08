package com.comfenalco.springboot.backend.apirest.sorteo.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "personas_premios")
public class PersonaPremio {

	@Id
	@Column(name = "id_persona")
	private int idPersona;
	@Column(name = "id_premios")
	private int idPremio;

	public int getIdPersona() {
		return idPersona;
	}

	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}

	public int getIdPremio() {
		return idPremio;
	}

	public void setIdPremio(int idPremio) {
		this.idPremio = idPremio;
	}

}
