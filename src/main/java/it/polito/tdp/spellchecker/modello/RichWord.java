package it.polito.tdp.spellchecker.modello;

public class RichWord {
	
	private String parola;
	private boolean corretta;
	
	public RichWord(String parola, boolean corretta) {
		this.parola = parola;
		this.corretta = corretta;
	}
	
	public String getParola() {
		return parola;
	}
	public boolean isCorretta() {
		return corretta;
	}

	@Override
	public String toString() {
		return parola;
	}
	
	
}
