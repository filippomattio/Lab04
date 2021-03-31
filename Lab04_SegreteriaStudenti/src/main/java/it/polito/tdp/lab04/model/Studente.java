package it.polito.tdp.lab04.model;

public class Studente {
private Integer matricola;
private String cognome;
private String nome;
private String CDS;
public Integer getMatricola() {
	return matricola;
}
public void setMatricola(Integer matricola) {
	this.matricola = matricola;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCDS() {
	return CDS;
}
public void setCDS(String cDS) {
	CDS = cDS;
}
public Studente(Integer matricola, String cognome, String nome, String cDS) {
	super();
	this.matricola = matricola;
	this.cognome = cognome;
	this.nome = nome;
	CDS = cDS;
}
@Override
public String toString() {
	return  this.matricola + "\t" + this.cognome + "\t" + this.nome + "\t" + this.CDS + "\t\n";
}

}
