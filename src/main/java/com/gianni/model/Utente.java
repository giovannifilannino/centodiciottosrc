package com.gianni.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="utenti")
public class Utente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name ="nome")
	private String nome;
	@Column(name ="cognome")
	private String cognome;
	@Column(name ="entrato")
	private Date entrato;
	@Column(name ="segni_particolari")
	private String segniParticolari;
	@Column(name ="hobby")
	private String hobby;
	@Column(name ="foto")
	private byte[] foto;
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getEntrato() {
		return entrato;
	}

	public void setEntrato(Date date) {
		this.entrato = date;
	}

	public String getSegniParticolari() {
		return segniParticolari;
	}

	public void setSegniParticolari(String segniParticolari) {
		this.segniParticolari = segniParticolari;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	
}
