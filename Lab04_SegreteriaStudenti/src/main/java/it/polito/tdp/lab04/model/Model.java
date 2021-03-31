package it.polito.tdp.lab04.model;

import java.util.List;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	CorsoDAO dao = new CorsoDAO();
	StudenteDAO sdao = new StudenteDAO();
	
	
	public List<Corso> getTuttiCorsi(){
		return dao.getTuttiICorsi();
	}
	
	public Studente studente(Integer matricola) {
		return sdao.getStudente(matricola);
	}
	public List<Studente> getIscrittiCorso(String nomeCorso){
		return sdao.iscrittiCorso(nomeCorso);
	}
	
	public List<Corso> getCorsi(Integer matricola){
		return dao.getCorsi(matricola);
	}
	public boolean frequenta(String nomeCorso, Integer matricola) {
		return dao.studenteFrequenta(matricola, nomeCorso);
	}
}
