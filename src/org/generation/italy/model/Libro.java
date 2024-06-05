package org.generation.italy.model;

public class Libro {
		
	public Integer id;
	public String titolo;
	public Integer idAutore;
	public Integer anno;
	public Integer idGenere;
	public Integer idCasaEd;
	public String isbn;
	public Integer qnt;
	@Override
	public String toString() {
		return "Libro [id=" + id + ", titolo=" + titolo + ", idAutore=" + idAutore + ", anno=" + anno + ", idGenere="
				+ idGenere + ", idCasaEd=" + idCasaEd + ", isbn=" + isbn + ", qnt=" + qnt + "]";
	}	
}
	

	