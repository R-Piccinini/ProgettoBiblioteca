package org.generation.italy.model;

public class Utente {
	
	public Integer id;
	public String nome;
	public String cognome;
	public String email;
	public String telefono;
	public String CF;
	@Override
	public String toString() {
		return "Utente [id=" + id + ", nome=" + nome + ", cognome=" + cognome + ", email=" + email + ", telefono="
				+ telefono + ", CF=" + CF + "]";
	}


}
