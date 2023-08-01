package com.esercizio5_spring.esercizio5_spring.model;

import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Setter
@Getter
@NoArgsConstructor

@ToString
public class User {

	private static final AtomicInteger atomic = new AtomicInteger(1);

	private Long id;

	private String username;
	private String nomeCompleto;
	private String email;

	private Set<Prenotazione> prenotazioni;

	public User(String username, String nomeCompleto, String email) {
		super();
		this.username = username;
		this.nomeCompleto = nomeCompleto;
		this.email = email;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
