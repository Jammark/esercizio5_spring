package com.esercizio5_spring.esercizio5_spring.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.esercizio5_spring.esercizio5_spring.model.Postazione;
import com.esercizio5_spring.esercizio5_spring.model.Prenotazione;
import com.esercizio5_spring.esercizio5_spring.model.User;

public class PrenotazioniService {

	@Autowired
	private UserService uServ;

	@Autowired
	private PostazioniService pServ;

	private List<Prenotazione> prenotazioni = new ArrayList<Prenotazione>();

	public List<Prenotazione> prenotazioni() {
		return this.prenotazioni;
	}

	public void save(Prenotazione p) {
		if (isPostazioneOccupata(p) || isUserImpegnato(p) || !isValid(p)) {
			throw new IllegalArgumentException("Prenotazione non valida");
		} else {
			this.prenotazioni.add(p);
		}
	}

	public Prenotazione prenota(Long userId, Long postazioneId, LocalDate data) {
		User u = uServ.findById(userId).orElseThrow(() -> new IllegalArgumentException("Id utente non valido"));
		Postazione p = pServ.findById(postazioneId)
				.orElseThrow(() -> new IllegalArgumentException("Id postazione non valido"));
		Prenotazione pr = new Prenotazione(data, u, p);
		this.save(pr);
		return pr;
	}

	public boolean isPostazioneOccupata(Prenotazione p) {
		for (Prenotazione p1 : prenotazioni) {
			if (p1.getPostazione().equals(p.getPostazione()) && p1.getData().isEqual(p.getData())) {
				return true;
			}
		}
		return false;
	}

	public boolean isUserImpegnato(Prenotazione p) {
		for (Prenotazione p1 : prenotazioni) {
			if (p1.getUser().equals(p.getUser()) && p1.getData().isEqual(p.getData())) {
				return true;
			}
		}
		return false;
	}

	public boolean isValid(Prenotazione p) {
		return p.getData().isAfter(LocalDate.now().plusDays(1));
	}

}
