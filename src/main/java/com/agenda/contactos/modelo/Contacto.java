package com.agenda.contactos.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
public class Contacto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementable

	private Integer id;

	//para que no este en blanco
	@NotBlank(message = "Debe ingresar su nombre")
	private String nombre;

	@NotEmpty(message = "Debe ingresar el email")
	@Email
	private String email;

	@NotBlank(message = "Debe ingresar su celular")
	private String celular;

	@DateTimeFormat(iso = ISO.DATE)//INDICAMOS EL FORMATO DE FECHA
	@Past//fecha de nac que hayan nacido en el pasado
	@NotNull(message = "Debe ingresar su fecha de nacimiento")
	private LocalDate fechaN;

	private LocalDateTime fechaR;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public LocalDate getFechaN() {
		return fechaN;
	}

	public void setFechaN(LocalDate fechaN) {
		this.fechaN = fechaN;
	}

	public LocalDateTime getFechaR() {
		return fechaR;
	}

	public void setFechaR(LocalDateTime fechaR) {
		this.fechaR = fechaR;
	}
	
	@PrePersist//a la fecR con este metodo lo voy a persistir
	//cuando cree un nuevo obj le va a signar la fehca de hoy
	public void asignarFechaRegistro() {
		fechaR = LocalDateTime.now();//aquie sta la fecha de hoy
	}

	public Contacto(Integer id, String nombre, String email, String celular, LocalDate fechaN, LocalDateTime fechaR) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.email = email;
		this.celular = celular;
		this.fechaN = fechaN;
		this.fechaR = fechaR;
	}

	public Contacto() {
		super();
	}

}
