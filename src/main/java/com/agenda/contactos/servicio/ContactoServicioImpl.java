package com.agenda.contactos.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.contactos.modelo.Contacto;
import com.agenda.contactos.repositorio.ContactoRepositorio;
@Service // para que esto se guarde en la fabrica de Spring
public class ContactoServicioImpl implements ContactoServicio{
	
	@Autowired
	private ContactoRepositorio repositorio;

	@Override
	public Contacto obtenerEstudiantePorId(Integer id) {
		return repositorio.findById(id).get();
		
	}

	@Override
	public Contacto actualizarEstudiante(Contacto contacto) {
		return repositorio.save(contacto);
		// voya buscar une contacto a la hora de actualizarlo y esos datos actualizados
		// los voy a guardar
	}

}
