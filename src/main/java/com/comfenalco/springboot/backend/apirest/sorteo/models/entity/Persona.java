package com.comfenalco.springboot.backend.apirest.sorteo.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "personas")
public class Persona implements Serializable {
	
	
	
	@Column(name = "tipo_documento")
	private String tipoDocumento;
	@Id
	@Column(name = "nro_documento")
	private int id;
	
	private String nombre;
	private String apellido;

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date fechaNacimiento;

	@ManyToMany
	@JoinTable(name="personas_premios",
			joinColumns = @JoinColumn(name="id_persona"),
			inverseJoinColumns = @JoinColumn(name="id_premios"))
	private Set<Premio> premios;

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	

	public Set<Premio> getPremios() {
		return premios;
	}

	public void setPremios(Set<Premio> premios) {
		this.premios = premios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	private static final long serialVersionUID = 1L;
}
