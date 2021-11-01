package com.eventoapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.eventoapp.repository.ConvidadoRepository;
import com.eventoapp.repository.EventoRepository;
import com.eventoapp.models.Convidado;
import com.eventoapp.models.Evento;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository eventoRepository;
	
	@Autowired
	private ConvidadoRepository convidadoRepository;
	
	//formulario de cadastro de evento
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.GET)
	public String form() {
		return "evento/formEvento";		
	}
	
	//salvar dados do cadastro de evento no banco
	@RequestMapping(value="/cadastrarEvento", method=RequestMethod.POST)
	public String form(@Validated Evento evento, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEvento";
		}
		eventoRepository.save(evento);
		attributes.addFlashAttribute("mensagem", "Evento cadastrado com sucesso!");
		return "redirect:/cadastrarEvento";		
	}
	
	//buscar eventos já cadastrados no banco
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView modelView = new ModelAndView("index");
		Iterable<Evento> eventos = eventoRepository.findAll();
		modelView.addObject("eventos", eventos);
		return modelView;		
	}
	
	//detalhamento do evento
	@RequestMapping(value="/{codigo}", method=RequestMethod.GET)
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = eventoRepository.findByCodigo(codigo);
		ModelAndView modelView = new ModelAndView("evento/detalhesEvento");
		modelView.addObject("evento", evento);
		
		Iterable<Convidado> convidados = convidadoRepository.findByEvento(evento);
		modelView.addObject("convidados", convidados);
		return modelView;
	}
	
	//salvar Convidado
	@RequestMapping(value="/{codigo}", method=RequestMethod.POST)
	public String detalhesEventoPost(@PathVariable("codigo") long codigo, 
			@Validated Convidado convidado, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
			return "redirect:/{codigo}";
		}
		
		Evento evento = eventoRepository.findByCodigo(codigo);
		convidado.setEvento(evento);
		convidadoRepository.save(convidado);
		attributes.addFlashAttribute("mensagem", "Convidado adicionado com sucesso!");
		return "redirect:/{codigo}";
	}
	
}
