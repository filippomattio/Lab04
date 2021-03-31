package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class CorsoDAO {
	
	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				System.out.println(codins + " " + numeroCrediti + " " + nome + " " + periodoDidattico);
				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);

				// Crea un nuovo JAVA Bean Corso
				// Aggiungi il nuovo oggetto Corso alla lista corsi
			}

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	
	
	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public void getCorso(Corso corso) {
		// TODO
	}

	/*
	 * Ottengo tutti i corsi a cui è iscritto uno studente
	 */
	public List<Corso> getCorsi(Integer matricola) {
		final String sql = "SELECT c.codins, c.nome, c.crediti, c.pd "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins=i.codins AND i.matricola = ? "
				+ "GROUP BY c.codins, c.nome, c.crediti, c.pd";

		List<Corso> corsi = new LinkedList<Corso>();

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
            st.setInt(1, matricola);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				String codins = rs.getString("codins");
				int numeroCrediti = rs.getInt("crediti");
				String nome = rs.getString("nome");
				int periodoDidattico = rs.getInt("pd");

				Corso c = new Corso(codins, numeroCrediti, nome, periodoDidattico);
				corsi.add(c);

				
			}
			rs.close();
			st.close();

			conn.close();
			
			return corsi;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		// TODO
		
	}

	
	public boolean studenteFrequenta(Integer studente, String corso) {
		final String sql = "SELECT i.matricola, c.nome "
				+ "FROM iscrizione i, corso c "
				+ "WHERE  c.codins=i.codins AND c.nome =? AND i.matricola=?";

		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, corso);
            st.setInt(2, studente);
			ResultSet rs = st.executeQuery();
			Integer matr=0;
			while (rs.next()) {

				 matr = rs.getInt("matricola");
				

				
			}
			rs.close();
			st.close();

			conn.close();
			if(matr==0) {
				return false;
			}else {
				return true;
			}
			
		
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
		
	}

}
