/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnNuovaPartita"
    private Button btnNuovaPartita; // Value injected by FXMLLoader
    
    @FXML // fx:id="hboxTentativi"
    private HBox hboxTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="btnProva"
    private Button btnProva; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativi"
    private TextField txtTentativi; // Value injected by FXMLLoader

    @FXML // fx:id="txtTentativo"
    private TextField txtTentativo; // Value injected by FXMLLoader

    @FXML
    void doNuovaPartita(ActionEvent event) {
    	// comunico al modello che inizia una nuova partita
    	this.model.nuovaPartita();
    	//gestione interfaccia
    	txtTentativi.setText(Integer.toString(model.getTMAX()));
    	hboxTentativi.setDisable(false);
    	txtRisultato.clear();    	
    }

    @FXML
    void doTentativo(ActionEvent event) {
    	String ts = txtTentativo.getText();
    	int tentativo;
    	int risultato;    	
    	//controllo 1 -> input numerico
    	try {
    		tentativo = Integer.parseInt(ts);
    	} catch (NumberFormatException e) {
    		txtRisultato.setText("Devi inserire un tentativo numerico tra 1 e 100!");
    		return;
    	}
    	try{
    		risultato = this.model.tentativi(tentativo);
    		if(risultato == 0){
        		txtRisultato.appendText("Hai vinto con "+ model.getTentativiFatti()+ " tentativi!");
        	}else if(risultato == -1){
        		txtRisultato.appendText("Tentativo troppo basso!\n");
        	}else{
        		txtRisultato.appendText("Tentativo troppo alto!\n");
        	}
    	}catch(IllegalStateException ie){
    		txtRisultato.setText(ie.getMessage());
    	}catch(InvalidParameterException ie){
    		txtRisultato.setText(ie.getMessage());
    	}
    	txtTentativi.setText(Integer.toString(model.getTMAX() - model.getTentativiFatti()));
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativo != null : "fx:id=\"txtTentativo\" was not injected: check your FXML file 'Scene.fxml'.";
    }    
    public void setModel(Model model){
    	this.model = model;
    }
}
