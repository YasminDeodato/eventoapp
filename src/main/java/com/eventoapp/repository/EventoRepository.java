package com.eventoapp.repository;

import org.springframework.data.repository.CrudRepository;
import com.eventoapp.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
	public Evento findByCodigo(long Codigo);
}
