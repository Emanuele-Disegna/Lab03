/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.modello.Dictionary;
import it.polito.tdp.spellchecker.modello.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	Dictionary model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cmbLingua"
    private ComboBox<String> cmbLingua; // Value injected by FXMLLoader

    @FXML // fx:id="txtDaTradurre"
    private TextArea txtDaCorreggere; // Value injected by FXMLLoader

    @FXML // fx:id="txtElenco"
    private TextArea txtElenco; // Value injected by FXMLLoader

    @FXML // fx:id="txtErrori"
    private Label txtErrori; // Value injected by FXMLLoader

    @FXML // fx:id="txtTempo"
    private Label txtTempo; // Value injected by FXMLLoader

    @FXML
    void handleCheck(ActionEvent event) {
    	
    	//Carichiamo il dizionario della lingua corretta
    	String lingua = cmbLingua.getValue();
    	model.loadDictionary(lingua);
    	
    	String[] pS = txtDaCorreggere.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]", "")
    									.toLowerCase().split(" ");
    	
    	List<String> elenco = new ArrayList<String>();
    	
    	for(String s: pS)
    		elenco.add(s);
    		
    	//List<RichWord> paroleCorrette = model.spellCheckText(elenco);	
    	//List<RichWord> paroleCorrette = model.spellCheckTextLinear(elenco);	
    	List<RichWord> paroleCorrette = model.spellCheckTextDichotomic(elenco);	
    	
    	int numErrori = 0;
    	
    	for(RichWord i: paroleCorrette) {
    		
    		if(!i.isCorretta()) {
    			
    			txtElenco.appendText(i.toString()+"\n");
    			numErrori++;
    		}
    		
    	}
    	
    	txtErrori.setText("The text contains "+numErrori+" errors");
    	
    	txtTempo.setText("Spell check completed in "+model.getTempoDiEsecuzione()/1000+" milliseconds");
    	
    }

    @FXML
    void handleClear(ActionEvent event) {

    	//cmbLingua.getItems().clear();
    	txtDaCorreggere.clear();
    	txtElenco.clear();
    	txtErrori.setText("");
    	txtTempo.setText("");
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cmbLingua != null : "fx:id=\"cmbLingua\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDaCorreggere != null : "fx:id=\"txtDaTradurre\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtElenco != null : "fx:id=\"txtElenco\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtErrori != null : "fx:id=\"txtErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTempo != null : "fx:id=\"txtTempo\" was not injected: check your FXML file 'Scene.fxml'.";

        cmbLingua.getItems().clear();
        cmbLingua.getItems().add("Italian");
        cmbLingua.getItems().add("English");
        
    }

    public void setModel(Dictionary model) {
    	this.model=model;
    }
    
}


