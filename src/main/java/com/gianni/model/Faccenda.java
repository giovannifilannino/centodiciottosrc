package com.gianni.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="faccende")
public class Faccenda {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_faccenda")
	private int idFaccenda;
	@Column(name="id_utente")
	private int idUtente;
	@Column(name="giorno_pulizia")
	private int giornoSettimana;
	@Column(name="pulizia_pari")
	private String puliziaPari;
	@Column(name="pulizia_dispari")
	private String puliziaDispari;

	public int getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(int idUtente) {
		this.idUtente = idUtente;
	}

	public int getGiornoSettimana() {
		return giornoSettimana;
	}

	public void setGiornoSettimana(int giornoSettimana) {
		this.giornoSettimana = giornoSettimana;
	}

	public String getPuliziaPari() {
		return puliziaPari;
	}

	public void setPuliziaPari(String puliziaPari) {
		this.puliziaPari = puliziaPari;
	}

	public String getPuliziaDispari() {
		return puliziaDispari;
	}

	public void setPuliziaDispari(String puliziaDispari) {
		this.puliziaDispari = puliziaDispari;
	}

}
