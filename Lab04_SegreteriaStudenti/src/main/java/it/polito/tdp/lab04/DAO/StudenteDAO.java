package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {
	public Studente getStudente(Integer matr) {

		final String sql = "SELECT * FROM studente s WHERE s.matricola = ?";

		Studente s = null;

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, matr);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				if(rs.getString("nome")==null || rs.getString("nome").length()==0) {
					return s;
				}else {

				Integer matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");
				s = new Studente(matricola, cognome, nome, CDS);

				}

			}
			rs.close();
			st.close();

			conn.close();
			
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		}
	}
	public List<Studente> iscrittiCorso(String nomeCorso){
		final String sql = "SELECT s.matricola, s.nome, s.cognome, s.CDS FROM iscrizione i, studente s, corso c WHERE i.matricola=s.matricola AND c.nome=? AND c.codins=i.codins GROUP BY s.matricola, s.nome, s.cognome, s.CDS";
        List<Studente> s = new LinkedList<Studente>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, nomeCorso);
			ResultSet rs = st.executeQuery();
			

			while (rs.next()) {
				

				Integer matricola = rs.getInt("matricola");
				String nome = rs.getString("nome");
				String cognome = rs.getString("cognome");
				String CDS = rs.getString("CDS");
				Studente ss = new Studente(matricola, cognome, nome, CDS);
				s.add(ss);
				}
			rs.close();
			st.close();

			conn.close();
			
			return s;
			

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db", e);
		
	}
	}	
	
	
}
