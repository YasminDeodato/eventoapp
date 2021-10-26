package com.eventoapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EventoController {
	
	//formulario de cadastro de evento
	@RequestMapping("/cadastrarEvento")
	public String form() {
		return "evento/formEvento";		
	}
}
