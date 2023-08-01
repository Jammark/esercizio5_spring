package com.esercizio5_spring.esercizio5_spring.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {

	private static final AtomicInteger atomic = new AtomicInteger(1);

	private Long id;

	private LocalDate data;

	private User user;

	private Postazione postazione;

	public Prenotazione(LocalDate data, User user, Postazione postazione) {
		super();
		this.data = data;
		this.user = user;
		this.postazione = postazione;
		this.id = (long) atomic.incrementAndGet();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Prenotazione other = (Prenotazione) obj;
		return Objects.equals(id, other.id);
	}

}
