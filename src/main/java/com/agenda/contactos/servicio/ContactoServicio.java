package com.agenda.contactos.servicio;

import com.agenda.contactos.modelo.Contacto;



public interface ContactoServicio {

	public Contacto obtenerEstudiantePorId(Integer id);
	
	public Contacto actualizarEstudiante(Contacto contacto);
}
