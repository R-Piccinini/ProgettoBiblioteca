package org.generation.italy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import org.generation.italy.model.Autore;
import org.generation.italy.model.CasaEd;
import org.generation.italy.model.Genere;
import org.generation.italy.model.Libro;
import org.generation.italy.model.Utente;

public class Main {

	public static void main(String[] args) {
		String scelta, scelta2;
		Scanner sc = new Scanner(System.in);
		String url = "jdbc:mysql://localhost:3306/biblioteca";

		System.out.println("Tentativo di connessione al db..."); // inizio programma
		try (Connection conn = DriverManager.getConnection(url, "root", "")) { // connessione
			System.out.println("connessione riuscita");

			do {
				ArrayList<Utente> elencoUtenti = new ArrayList<Utente>(); // preparazione array e SQL
				ArrayList<Libro> elencoLibri = new ArrayList<Libro>();
				ArrayList<Autore> elencoAutori = new ArrayList<Autore>();
				ArrayList<Genere> elencoGeneri = new ArrayList<Genere>();
				ArrayList<CasaEd> elencoCasaEd = new ArrayList<CasaEd>();
				String sqlUtenti = "SELECT * from utenti";
				String sqlLibri = "SELECT * from libri";
				String sqlAutori = "SELECT * from autori";
				String sqlGeneri = "SELECT * from generi";
				String sqlCasaEd = "SELECT * from case_editrici";
				// String sqlPrestiti="SELECT * from prestiti";

				try (PreparedStatement ps = conn.prepareStatement(sqlUtenti)) {
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {

							Utente u = new Utente();
							u.id = rs.getInt("id");
							u.nome = rs.getString("nome");
							u.cognome = rs.getString("cognome");
							u.email = rs.getString("email");
							u.telefono = rs.getString("telefono");
							u.CF = rs.getString("CF");
							elencoUtenti.add(u);

						}
					}
				}

				try (PreparedStatement ps = conn.prepareStatement(sqlLibri)) {
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							Libro l = new Libro();
							l.id = rs.getInt("id");
							l.titolo = rs.getString("titolo");
							l.idAutore = rs.getInt("id_autore");
							l.anno = rs.getInt("anno");
							l.idGenere = rs.getInt("id_genere");
							l.idCasaEd = rs.getInt("id_casaed");
							l.isbn = rs.getString("ISBN");
							l.qnt = rs.getInt("qnt");
							elencoLibri.add(l);

						}
					}
				}

				try (PreparedStatement ps = conn.prepareStatement(sqlAutori)) {
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							Autore a = new Autore();
							a.id = rs.getInt("id");
							a.autore = rs.getString("autore");
							elencoAutori.add(a);

						}

					}
				}

				try (PreparedStatement ps = conn.prepareStatement(sqlGeneri)) {
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							Genere g = new Genere();
							g.id = rs.getInt("id");
							g.genere = rs.getString("genere");
							elencoGeneri.add(g);

						}

					}
				}

				try (PreparedStatement ps = conn.prepareStatement(sqlCasaEd)) {
					try (ResultSet rs = ps.executeQuery()) {
						while (rs.next()) {
							CasaEd ce = new CasaEd();
							ce.id = rs.getInt("id");
							ce.casaEditrice = rs.getString("casa_editrice");
							elencoCasaEd.add(ce);
						}

					}
				}

				// scelta programmi
				System.out.println();
				System.out.println("Seleziona Programma: (1-Gestione Utenti),(2-Gestione Magazzino),"
						+ "(3-Gestione Libri),(4-Ricerca e Filtraggio),(5-Reportistica),(6-Esci)");
				scelta = sc.nextLine();
				while (!(scelta.equals("1") || scelta.equals("2") || scelta.equals("3") || scelta.equals("4")
						|| scelta.equals("5") || scelta.equals("6"))) {// check scelta
					System.out.println("Scelta non valida");
					System.out.println("Seleziona Programma: (1-Gestione Utenti),(2-Gestione Magazzino),"
							+ "(3-Gestione Libri),(4-Ricerca e Filtraggio),(5-Reportistica),(6-Esci)");
					scelta = sc.nextLine();
				}
				switch (scelta) {
				case ("1"):// gestione utenti
					System.out.println();
					System.out.println("(1-Iscrizione),(2-Ricerca Utente),(B-Torna indietro)");
					scelta2 = sc.nextLine().toLowerCase();
					while (!(scelta2.equals("1") || scelta2.equals("2") || scelta2.equals("b"))) {
						System.out.println("(1-Iscrizione),(2-Ricerca Utente),(B-Torna indietro)");
						scelta2 = sc.nextLine();
					}
					switch (scelta2) {
					case ("1"):
						System.out.println("prova");
						// signup
						Utente u = new Utente();
						boolean trovato = false;
						System.out.println("Nome: ");
						u.nome = sc.nextLine();
						System.out.println("Cognome: ");
						u.cognome = sc.nextLine();
						System.out.println("E-mail: ");
						u.email = sc.nextLine();
						System.out.println("Telefono: ");
						u.telefono = sc.nextLine();
						System.out.println("Codice Fiscale: ");
						u.CF = sc.nextLine().toUpperCase();

						// check utente già registrato
						for (Utente ut : elencoUtenti) {
							if (ut.CF.equals(u.CF)) {
								trovato = true;
							}
						}
						if (trovato) {
							System.out.println("Utente già registrato");
							break;
						} else {
							// inserimento database
							String sql = "INSERT INTO utenti(nome,cognome,email,telefono,CF)" + "VALUES (?,?,?,?,?)";
							try (PreparedStatement ps = conn.prepareStatement(sql)) {
								ps.setString(1, u.nome);
								ps.setString(2, u.cognome);
								ps.setString(3, u.email);
								ps.setString(4, u.telefono);
								ps.setString(5, u.CF);
								ps.executeUpdate();
								System.out.println("Utente correttamente inserito.");
							}
						}
						break;
					case ("2"):
						// Ricerca utente
						System.out.println("(1-Codice fiscale),(2-Numero tessera)");
						String scelta3 = sc.nextLine();
						while (!(scelta3.equals("1") || scelta3.equals("2"))) {
							System.out.println("(1-Codice fiscale),(2-Numero tessera)");
							scelta3 = sc.nextLine();
						}
						switch (scelta3) {
						case ("1"):
							System.out.println("Inserisci CF: ");
							Utente ricerca = new Utente();
							ricerca.CF = sc.nextLine();
							for (Utente ute : elencoUtenti) {
								if (ute.CF.equals(ricerca.CF)) {
									System.out.println(ute.toString());
								}
							}
							break;
						case ("2"):
							System.out.println("Inserisci numero tessera: ");
							Utente ricerca2 = new Utente();
							ricerca2.id = sc.nextInt();
							sc.nextLine();
							for (Utente ute : elencoUtenti) {
								if (ute.id.equals(ricerca2.id)) {
									System.out.println(ute.toString());
								}
							}
							break;
						case ("b"):
							break;
						}
					}
					break;

				case ("2"):
					// gestione Magazzino
					break;
				case ("3"):
					// gestione libri
					System.out.println();
					System.out.println(
							"(1-Prestito/Resistuzione),(2-Inserimento),(3-Modifica),(4-Cancellazione),(B-Torna indietro)");
					scelta2 = sc.nextLine().toLowerCase();
					while (!(scelta2.equals("1") || scelta2.equals("2") || scelta2.equals("3") || scelta2.equals("4")
							|| scelta2.equals("b"))) {
						System.out.println(
								"(1-Prestito/Resistuzione),(2-Inserimento),(3-Modifica),(4-Cancellazione),(B-Torna indietro)");
						scelta2 = sc.nextLine();
					}
					switch (scelta2) {
					case ("1"):
						//
						break;
					case ("2"):
						// inserimento
						Libro l = new Libro();
						Autore a = new Autore();
						Genere g = new Genere();
						CasaEd ce = new CasaEd();
						boolean trovato = false;
						System.out.print("titolo: ");// inserimento titolo
						l.titolo = sc.nextLine();
						System.out.print("autore: ");// inserimento autore
						a.autore = sc.nextLine();
						for (Autore aut : elencoAutori) {
							if (aut.autore.equals(a.autore)) {
								a.id = aut.id;
								trovato = true;
							}
						}
						if (trovato) {
							l.idAutore = a.id;
							trovato = false;
						} else {
							String sql = "INSERT INTO autori(autore)" + "VALUES(?)";
							try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
								ps.setString(1, a.autore);
								ps.executeUpdate();
								ResultSet rs2 = ps.getGeneratedKeys();
								if (rs2.next())
									l.idAutore = rs2.getInt(1);
							} catch (Exception e) {
								System.err.println("Si è verificato un errore: " + e.getMessage());
							}
						}
						System.out.println("anno :");// inserimento anno
						l.anno = sc.nextInt();
						sc.nextLine();

						System.out.println("genere :");// inserimento genere
						g.genere = sc.nextLine();
						for (Genere gen : elencoGeneri) {
							if (gen.genere.equals(g.genere)) {
								g.id = gen.id;
								trovato = true;
							}
						}
						if (trovato) {
							l.idGenere = g.id;
							trovato = false;
						} else {
							String sql = "INSERT INTO generi(genere)" + "VALUES(?)";
							try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
								ps.setString(1, g.genere);
								ps.executeUpdate();
								ResultSet rs2 = ps.getGeneratedKeys();
								if (rs2.next())
									l.idGenere = rs2.getInt(1);
							} catch (Exception e) {
								System.err.println("Si è verificato un errore: " + e.getMessage());
							}
						}

						System.out.println("casa editrice :");// inserimento CE
						ce.casaEditrice = sc.nextLine();
						for (CasaEd cEd : elencoCasaEd) {
							if (cEd.casaEditrice.equals(ce.casaEditrice)) {
								ce.id = cEd.id;
								trovato = true;
							}
						}
						if (trovato) {
							l.idCasaEd = ce.id;
							trovato = false;
						} else {
							String sql = "INSERT INTO case_editrici(casa_editrice)" + "VALUES(?)";
							try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
								ps.setString(1, ce.casaEditrice);
								ps.executeUpdate();
								ResultSet rs2 = ps.getGeneratedKeys();
								if (rs2.next())
									l.idCasaEd = rs2.getInt(1);
							} catch (Exception e) {
								System.err.println("Si è verificato un errore: " + e.getMessage());
							}
						}
						System.out.println("ISBN: ");// inserimento isbn
						l.isbn = sc.nextLine();
						System.out.println("quantità: ");// inserimento qnt
						l.qnt = sc.nextInt();
						sc.nextLine();

						System.out.println("Tentativo di esecuzione INSERT");

						String sql = "INSERT INTO libri(titolo,id_autore,anno,id_genere,id_casaed,ISBN,qnt) "
								+ "VALUES (?,?,?,?,?,?,?)";

						try (PreparedStatement ps = conn.prepareStatement(sql)) { // try inserimento

							// imposto i valori dei parametri
							ps.setString(1, l.titolo);
							ps.setInt(2, l.idAutore);
							ps.setInt(3, l.anno);
							ps.setInt(4, l.idGenere);
							ps.setInt(5, l.idCasaEd);
							ps.setString(6, l.isbn);
							ps.setInt(7, l.qnt);

							int righeInteressate = ps.executeUpdate();
							System.out.println("Righe inserite: " + righeInteressate);

						} catch (Exception e) {
							System.err.println("Si è verificato un errore: " + e.getMessage());
						}

						break;

					case ("3"):
						// modifica
						Libro l2 = new Libro();
						Autore a2 = new Autore();
						Genere g2 = new Genere();
						CasaEd ce2 = new CasaEd();
						System.out.println("Inserire id libro da modificare: ");
						l2.id = sc.nextInt();
						sc.nextLine();
						boolean trovato2 = false;

						for (Libro lib : elencoLibri) {
							if (lib.id == l2.id) {
								trovato2 = true;

							}
						}
						if (trovato2) {
							String sql3 = "SELECT libri.id,libri.titolo,autori.autore,libri.anno,generi.genere,case_editrici.casa_editrice,libri.ISBN,libri.qnt "
									+ "FROM libri INNER JOIN autori INNER JOIN generi INNER JOIN "
									+ "case_editrici ON autori.id=libri.id_autore AND generi.id = libri.id_genere AND case_editrici.id = libri.id_casaed WHERE libri.id = ?";
							try (PreparedStatement ps=conn.prepareStatement(sql3)){
								System.out.println(sql3);
							}
							System.out.print("titolo: ");// inserimento titolo
							l2.titolo = sc.nextLine();
							System.out.print("autore: ");// inserimento autore
							a2.autore = sc.nextLine();
							for (Autore aut : elencoAutori) {
								if (aut.autore.equals(a2.autore)) {
									a2.id = aut.id;
									trovato2 = true;
								}
							}
							if (trovato2) {
								l2.idAutore = a2.id;
								trovato2 = false;
							} else {
								String sql2 = "INSERT INTO autori(autore)" + "VALUES(?)";
								try (PreparedStatement ps = conn.prepareStatement(sql2,
										Statement.RETURN_GENERATED_KEYS)) {
									ps.setString(1, a2.autore);
									ps.executeUpdate();
									ResultSet rs2 = ps.getGeneratedKeys();
									if (rs2.next())
										l2.idAutore = rs2.getInt(1);
								} catch (Exception e) {
									System.err.println("Si è verificato un errore: " + e.getMessage());
								}
							}
							System.out.println("anno :");// inserimento anno
							l2.anno = sc.nextInt();
							sc.nextLine();

							System.out.println("genere :");// inserimento genere
							g2.genere = sc.nextLine();
							for (Genere gen : elencoGeneri) {
								if (gen.genere.equals(g2.genere)) {
									g2.id = gen.id;
									trovato2 = true;
								}
							}
							if (trovato2) {
								l2.idGenere = g2.id;
								trovato2 = false;
							} else {
								String sql2 = "INSERT INTO generi(genere)" + "VALUES(?)";
								try (PreparedStatement ps = conn.prepareStatement(sql2,
										Statement.RETURN_GENERATED_KEYS)) {
									ps.setString(1, g2.genere);
									ps.executeUpdate();
									ResultSet rs2 = ps.getGeneratedKeys();
									if (rs2.next())
										l2.idGenere = rs2.getInt(1);
								} catch (Exception e) {
									System.err.println("Si è verificato un errore: " + e.getMessage());
								}
							}

							System.out.println("casa editrice :");// inserimento CE
							ce2.casaEditrice = sc.nextLine();
							for (CasaEd cEd : elencoCasaEd) {
								if (cEd.casaEditrice.equals(ce2.casaEditrice)) {
									ce2.id = cEd.id;
									trovato2 = true;
								}
							}
							if (trovato2) {
								l2.idCasaEd = ce2.id;
								trovato2 = false;
							} else {
								String sql2 = "INSERT INTO case_editrici(casa_editrice)" + "VALUES(?)";
								try (PreparedStatement ps = conn.prepareStatement(sql2,
										Statement.RETURN_GENERATED_KEYS)) {
									ps.setString(1, ce2.casaEditrice);
									ps.executeUpdate();
									ResultSet rs2 = ps.getGeneratedKeys();
									if (rs2.next())
										l2.idCasaEd = rs2.getInt(1);
								} catch (Exception e) {
									System.err.println("Si è verificato un errore: " + e.getMessage());
								}
							}
							System.out.println("ISBN: ");// inserimento isbn
							l2.isbn = sc.nextLine();

							System.out.println("Tentativo di esecuzione UPDATE");

							String sql2 = "UPDATE libri SET titolo = ?,id_autore = ?,anno = ?,id_genere= ?,id_casaed = ?,ISBN = ? WHERE libri.id = ?";

							try (PreparedStatement ps = conn.prepareStatement(sql2)) { // provo a creare l'istruzione
																						// sql

								// imposto i valori dei parametri
								ps.setString(1, l2.titolo);
								ps.setInt(2, l2.idAutore);
								ps.setInt(3, l2.anno);
								ps.setInt(4, l2.idGenere);
								ps.setInt(5, l2.idCasaEd);
								ps.setString(6, l2.isbn);
								ps.setInt(7, l2.id);

								int righeInteressate = ps.executeUpdate(); // eseguo l'istruzione
								System.out.println("Righe inserite: " + righeInteressate);

							} catch (Exception e) { // catch che gestisce tutti i tipi di eccezione
								// si è verificato un problema. L'oggetto e (di tipo Exception) contiene
								// informazioni sull'errore verificatosi
								System.err.println("Si è verificato un errore: " + e.getMessage());
							}

						} else
							System.out.println("ID non trovato");

						break;
					case ("4"):
						break;
					case ("b"):
						break;
					}
					break;
				case ("4"):
					break;
				case ("5"):
					break;
				case ("6"):
					break;

				}
			} while (!(scelta.equals("6")));
			System.out.println("Arrivederci"); // fine ciclo e chiusura programma
		} catch (Exception e) { // catch che gestisce tutti i tipi di eccezione
			// si è verificato un problema. L'oggetto e (di tipo Exception) contiene
			// informazioni sull'errore verificatosi
			System.err.println("Si è verificato un errore: " + e.getMessage());
		}
		sc.close();

	}

}
