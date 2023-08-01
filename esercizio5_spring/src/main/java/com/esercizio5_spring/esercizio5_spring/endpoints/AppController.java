package com.esercizio5_spring.esercizio5_spring.endpoints;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.esercizio5_spring.esercizio5_spring.Esercizio5SpringApplication;
import com.esercizio5_spring.esercizio5_spring.model.Postazione;
import com.esercizio5_spring.esercizio5_spring.model.Prenotazione;
import com.esercizio5_spring.esercizio5_spring.model.TipoPostazione;
import com.esercizio5_spring.esercizio5_spring.model.User;
import com.esercizio5_spring.esercizio5_spring.service.PostazioniService;
import com.esercizio5_spring.esercizio5_spring.service.PrenotazioniService;
import com.esercizio5_spring.esercizio5_spring.service.UserService;

@RestController
@RequestMapping("/gestione_prenotazioni")
public class AppController {

	@Autowired
	private PrenotazioniService pService;
	@Autowired
	private PostazioniService posService;
	@Autowired
	private UserService uService;

	@GetMapping("/users")
	@ResponseStatus(HttpStatus.OK)
	public List<User> getUtenti() {
		return this.uService.findAll();
	}

	@GetMapping("/postazioni")
	@ResponseStatus(HttpStatus.OK)
	public List<Postazione> getPostazioni() {
		return this.posService.postazioni();
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/postazioni")
	public ResponseEntity getPostazioni(@RequestParam String tipo, @RequestParam String città) {
		try {
			List<Postazione> lista = this.posService.postazioni(TipoPostazione.valueOf(tipo), città);
			return new ResponseEntity<List<Postazione>>(lista, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/prenotazioni")
	public ResponseEntity<?> prenota(@RequestParam Long userId, @RequestParam Long postId, @RequestParam Integer days) {
		try {
			Prenotazione pr = this.pService.prenota(userId, postId, LocalDate.now().plusDays(days));
			return new ResponseEntity<Prenotazione>(pr, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/istruzioni/{lang}")
	public ResponseEntity<?> getIstruzioni(@PathVariable String lang) {

		try (AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(
				Esercizio5SpringApplication.class)) {
			switch (lang) {
			case "it":

				return new ResponseEntity<String>((String) ctx.getBean("getTestoItaliano"), HttpStatus.OK);
			case "en":
				return new ResponseEntity<String>((String) ctx.getBean("getTestoInglese"), HttpStatus.OK);

			default:
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		}
	}

}
