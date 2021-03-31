/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.lab04;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Model;
import it.polito.tdp.lab04.model.Studente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

   private Model model;

    

        @FXML
        private ComboBox<String> boxCorsi;

        @FXML
        private Button btnIscrittiCorso;

        @FXML
        private TextField txtStudente;

        @FXML
        private Button btnCompleta;

        @FXML
        private TextField txtNome;

        @FXML
        private TextField txtCognome;

        @FXML
        private Button btnCorsi;

        @FXML
        private TextArea txtRisultato;

        @FXML
        private Button btnReset;
        
        public void setModel(Model m) {
        	this.model=m;
        	LinkedList<String> s = new LinkedList<String>();
        	List<Corso> c = model.getTuttiCorsi();
        	s.add("");
        	for(Corso cc : c) {
        		s.add(cc.getNome());
        	}
        	this.boxCorsi.getItems().addAll(s);
        	
        }

        @FXML
        void cercaCorsi(ActionEvent event) {
        	this.txtRisultato.clear();
        	Integer matricola = null;
        	try {
        		matricola = Integer.parseInt(this.txtStudente.getText());
        	}catch(NumberFormatException ne) {
        		this.txtStudente.clear();
        		this.txtRisultato.setText("Devi inserire un numero");
        		return;
        	}
        	if(this.boxCorsi.getValue()==null) {
        	List<Corso> c = model.getCorsi(matricola);
        	if(c.size()==0) {
        		this.txtRisultato.setText("Studente con matricola \" + matricola +\" NON trovato");
        	}else {
        		for(Corso cc : c)
        			this.txtRisultato.appendText(cc.toString());
        	}
        	}else {
        		if(this.boxCorsi.getValue().length()==0) {
        			this.txtRisultato.setText("Devi selezionare un corso!");
        		}else {
        			boolean a = model.frequenta(this.boxCorsi.getValue(), Integer.parseInt(txtStudente.getText()));
        			if(a) {
        				this.txtRisultato.setText("Studente iscritto al corso");
        			}else {
        				this.txtRisultato.setText("Studente non iscritto al corso");
        			}
        		}
        	}
        	
        }

        @FXML
        void cercaIscrittiCorso(ActionEvent event) {
        	this.txtRisultato.clear();
if(this.boxCorsi.getValue()==null || this.boxCorsi.getValue().length()==0) {
	this.txtRisultato.setText("Devi selezionare un corso!");
}else {
	List<Studente> s = this.model.getIscrittiCorso(this.boxCorsi.getValue());
	if(s.size()==0) {
		this.txtRisultato.setText("Nessuno studente iscritto al corso " + this.boxCorsi.getValue());
	}else {
		for(Studente ss : s) {
			this.txtRisultato.appendText(ss.toString());
		}
	}
}
        }

        @FXML
        void completa(ActionEvent event) {
        	this.txtRisultato.clear();
        	Integer matricola = null;
        	try {
        		matricola = Integer.parseInt(this.txtStudente.getText());
        	}catch(NumberFormatException ne) {
        		this.txtStudente.clear();
        		this.txtRisultato.setText("Devi inserire un numero");
        		return;
        	}
           Studente s = this.model.studente(matricola);
           if(s==null) {
        	   this.txtRisultato.setText("Studente con matricola " + matricola +" NON trovato");
           }else {
        	   this.txtNome.setText(s.getNome());
        	   this.txtCognome.setText(s.getCognome());
           }
           
        }

        @FXML
        void resert(ActionEvent event) {

        }
        @FXML // ResourceBundle that was given to the FXMLLoader
        private ResourceBundle resources;

        @FXML // URL location of the FXML file that was given to the FXMLLoader
        private URL location;

        @FXML // This method is called by the FXMLLoader when initialization is complete
        void initialize() {

        }

    }

