package com.esercizio5_spring.esercizio5_spring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.esercizio5_spring.esercizio5_spring.model.Edificio;
import com.esercizio5_spring.esercizio5_spring.model.Postazione;
import com.esercizio5_spring.esercizio5_spring.model.TipoPostazione;
import com.esercizio5_spring.esercizio5_spring.model.User;
import com.github.javafaker.Faker;

@Configuration
public class AppConfig {

	@Bean
	public Faker getFaker() {
		return new Faker();
	}

	@Bean
	@Scope("prototype")
	public Edificio getEdificio() {
		return new Edificio(getFaker().address().city(), getFaker().address().fullAddress(),
				"Edificio-" + getFaker().address().buildingNumber());
	}

	@Bean
	@Scope("prototype")
	public Postazione getPostazione() {
		int index = new Random().nextInt(3);
		return new Postazione(getFaker().lorem().characters(), TipoPostazione.values()[index],
				new Random().nextInt(1, 20));
	}

	@Bean
	public List<Edificio> getEdifici() {
		return new ArrayList<>(Arrays.asList(getEdificio(), getEdificio(), getEdificio()));
	}

	@Bean
	public List<Postazione> getPostazioni() {
		return new ArrayList<Postazione>(
				Arrays.asList(getPostazione(), getPostazione(), getPostazione(), getPostazione()));
	}

	@Bean
	public List<User> getUsers() {
		return new ArrayList<>(Arrays.asList(getUser(), getUser(), getUser(), getUser()));
	}

	@Bean
	@Scope("prototype")
	public User getUser() {
		return new User(getFaker().name().username(), getFaker().name().fullName(),
				getFaker().internet().emailAddress());
	}

	@Bean
	public String getTestoItaliano() {
		return "regole di prenotazione: \n" + "1 un utente può cercare la postazione per tipo e città"
				+ " 2 un utente non può prenotare se la postazione è gia prenotata per un dato giorno o se ha già prenotato una postazione per la stessa data.";
	}

	@Bean
	public String getTestInglese() {
		return "booking rules: \n" + "1 user can searc post by type and city"
				+ " 2 user cannot book if has already booked a post for the same day or the post i booked by another user.";
	}
}
