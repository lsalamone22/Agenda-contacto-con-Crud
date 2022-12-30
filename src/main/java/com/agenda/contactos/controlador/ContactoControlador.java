package com.agenda.contactos.controlador;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.agenda.contactos.modelo.Contacto;
import com.agenda.contactos.repositorio.ContactoRepositorio;
import com.agenda.contactos.servicio.ContactoServicio;

@Controller
public class ContactoControlador {
	
	@Autowired
	private ContactoRepositorio repositorio;
	
	@Autowired
	private ContactoServicio servicio;
	
	@GetMapping({"/",""})//cuando hagamos una peticion get y entremos a nuestro navegador a cualquier ruta
	public String verPaginaDeInicio(Model modelo) {
		List<Contacto> contacto= repositorio.findAll();
		modelo.addAttribute("contactos", contacto);
		
		return "/index";
	}
	
	 @GetMapping("/nuevo")//para el boton de agregar contacto
	 public String mostrarFormDeRegistroContacto(Model modelo) {
		 modelo.addAttribute("contacto", new Contacto());
		 //modelo es un nuevo obj, para que a este le podamos asignar cada atributo del cotnacto
		 return "/nuevo";//archivo nuevo para registrar un contacto
		 
	 }
	 
	 @PostMapping("/nuevitoo")
	 public String guardarContacto(@Valid Contacto contacto,BindingResult bindingResult, Model modelo) {//el obj redirect sirve para añadirle msj o atributos
		 if(bindingResult.hasErrors()) {
			 modelo.addAttribute("contacto", contacto);//del mismo obj voy a pasar los errores al formulario, no voy a crear una nueva instancia
			 return "/nuevo";
			 
		 }
				 
		 repositorio.save(contacto);
		 return "redirect:/";
	 }
	 
	 @GetMapping("/{id}/editar")
		public String mostrarFormularioDeEditarContacto(@PathVariable Integer id,Model modelo) {//path recibe un id
		 modelo.addAttribute("contacto", servicio.obtenerEstudiantePorId(id));//aqui voy a pasar el estudiante que encuntre por id
			 //a la hora de mostrar el form para editar contacto
			 //1. buscare el cotnacto y luego lo mando al form para editar
			return "/nuevo";
		}
	 
	 @PostMapping("/{id}/editar")
		public String actualizarContacto(@PathVariable Integer id,@ModelAttribute("contacto") Contacto contacto) {
		 Contacto contactoDB = servicio.obtenerEstudiantePorId(id);//obtenemos el estu por id
		 	
		 	contactoDB.setId(id);
			contactoDB.setNombre(contacto.getNombre());
			contactoDB.setCelular(contacto.getCelular());
			contactoDB.setEmail(contacto.getEmail());
			contactoDB.setFechaN(contacto.getFechaN());
			contactoDB.setFechaR(contacto.getFechaR());
			
			
			servicio.actualizarEstudiante(contactoDB);
			return "redirect:/";
		}
	 
	 @PostMapping("/{id}/eliminar")
		public String eliminarContacto(@PathVariable Integer id,RedirectAttributes redirect) {
			Contacto contacto = repositorio.getById(id);
			repositorio.delete(contacto);
			redirect.addFlashAttribute("msgExito", "El contacto ha sido eliminado correctamente");
			return "redirect:/";
		}

}
