package com.esercizio5_spring.esercizio5_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esercizio5_spring.esercizio5_spring.model.Edificio;
import com.esercizio5_spring.esercizio5_spring.model.Postazione;
import com.esercizio5_spring.esercizio5_spring.model.TipoPostazione;

@Service
public class PostazioniService {

	@Autowired
	private List<Edificio> edifici;

	@Autowired
	private List<Postazione> postazioni;

	public PostazioniService() {
		super();
		this.postazioni.stream().limit(2).forEach(p -> p.setEdificio(edifici.get(0)));
		this.postazioni.stream().skip(2).forEach(p -> p.setEdificio(edifici.get(1)));
	}

	public List<Postazione> postazioni() {
		return this.postazioni;
	}

	public List<Postazione> postazioni(TipoPostazione tipo, String città) {
		return this.postazioni.stream().filter(p -> p.getTipo().equals(tipo))
				.filter(p -> p.getEdificio().getCittà().equals(città)).toList();
	}

	public Optional<Postazione> findById(Long id) {
		for (Postazione p : postazioni) {
			if (p.getId().equals(id)) {
				return Optional.ofNullable(p);
			}
		}
		return Optional.ofNullable(null);
	}
}
