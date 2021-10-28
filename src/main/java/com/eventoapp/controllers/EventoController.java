package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eventoapp.repository.EventoRepository;
import com.eventoapp.models.Evento;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	//formulario de cadastro de evento
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";		
	}
	
	//salvar dados do cadastro de evento no banco
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(Evento evento) {
		eventoRepository.save(evento);
		return "redirect:/cadastrarEvento";		
	}
	
	
}
