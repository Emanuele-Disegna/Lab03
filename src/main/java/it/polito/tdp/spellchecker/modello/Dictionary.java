package it.polito.tdp.spellchecker.modello;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
	
	long inizio, tempoTotale=0;
	Set<String> diz;
	List<String> dizLista;
	
	public Dictionary() {
		this.diz = new HashSet<String>();
		this.dizLista = new ArrayList<String>();
	}

	public void loadDictionary (String lingua) {
		
		try {
			//FileReader fr = new FileReader("src/main/resources/"+lingua+".txt");
			FileReader fr = new FileReader("C:\\Users\\admin\\git\\Lab03\\src\\main\\resources\\"+lingua+".txt");
			BufferedReader bf = new BufferedReader(fr);
			
			String parola;
			
			while((parola=bf.readLine())!=null) {
				diz.add(parola);
				dizLista.add(parola);
			}
			
			fr.close();
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}
	
	public List<RichWord> spellCheckText(List<String> inputTextList) {
		
		inizio = System.nanoTime();
		
		ArrayList<RichWord> ret = new ArrayList<RichWord>();
		
		for(String s: inputTextList) {
			
			if(diz.contains(s)) {
				ret.add(new RichWord(s, true));
			} else {
				ret.add(new RichWord(s, false));
			}
			
		}
		
		tempoTotale = System.nanoTime()-inizio;
		
		return ret;
	}

	public List<RichWord> spellCheckTextLinear(List<String> inputTextList) {
		
		inizio = System.nanoTime();
		
		ArrayList<RichWord> ret = new ArrayList<RichWord>();
		
		for(String i: inputTextList) {
			boolean b = false;
			for(String j: diz) {
				if(i.equals(j)) {
					ret.add(new RichWord(i,true));
					b=true;
					break;
				}
			}
			
			if(!b)
				ret.add(new RichWord(i,false));
			
		}
		
		tempoTotale = System.nanoTime()-inizio;
		
		return ret;
	}
	
	public List<RichWord> spellCheckTextDichotomic(List<String> inputTextList) {
		
		inizio = System.nanoTime();
		
		ArrayList<RichWord> ret = new ArrayList<RichWord>();
		ArrayList<String> dizL = new ArrayList<String>(dizLista);
		boolean trovata = false, finito = false;
		
		for(String s: inputTextList) {
			
			while(!finito || !trovata) {
				
				if(dizLista.get(dizL.size()/2).equals(s)) {
					
					trovata=true;
					ret.add(new RichWord(s,true));
					
				} else if (dizLista.get(dizL.size()/2).compareTo(s)>0) {
					
					dizL.subList(dizL.size()/2, dizL.size()).clear();
					
				} else if (dizLista.get(dizL.size()/2).compareTo(s)<0) {
					
					dizL.subList(0, dizL.size()/2).clear();
					
				} else if (dizL.size()==0) {
					
					finito=true;
					
				}
				
			}
			
			if(!trovata)
				ret.add(new RichWord(s,false));
		}
		
		tempoTotale = System.nanoTime()-inizio;
		
		return ret;
	}
	
	public long getTempoDiEsecuzione() {
		return tempoTotale;
	}
}
