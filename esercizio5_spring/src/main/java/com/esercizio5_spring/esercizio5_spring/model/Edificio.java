package com.esercizio5_spring.esercizio5_spring.model;

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
public class Edificio {

	private static final AtomicInteger atomic = new AtomicInteger(1);

	private Long id;

	private String città;
	private String indirizzo;
	private String nome;


	public Edificio(String città, String indirizzo, String nome) {
		super();
		this.città = città;
		this.indirizzo = indirizzo;
		this.nome = nome;
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
		Edificio other = (Edificio) obj;
		return Objects.equals(id, other.id);
	}

}
