package com.esercizio5_spring.esercizio5_spring.model;

import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter

@NoArgsConstructor
public class Postazione {

	private static final AtomicInteger atomic = new AtomicInteger(1);

	private Long id;

	private UUID codice;

	private String descrizione;

	private TipoPostazione tipo;

	private int maxOccupanti;

	private Edificio edificio;

	public Postazione(String descrizione, TipoPostazione tipo, int maxOccupanti) {
		super();
		this.descrizione = descrizione;
		this.tipo = tipo;
		this.maxOccupanti = maxOccupanti;
		this.codice = UUID.randomUUID();
		this.id = (long) atomic.incrementAndGet();
	}

	@Override
	public String toString() {
		return "Postazione [id=" + id + ", codice=" + codice + ", descrizione=" + descrizione + ", tipo=" + tipo
				+ ", maxOccupanti=" + maxOccupanti + ", edificio=" + edificio.getId() + "]";
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
		Postazione other = (Postazione) obj;
		return Objects.equals(id, other.id);
	}

}
