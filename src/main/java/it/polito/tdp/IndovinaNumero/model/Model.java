package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	private int segreto;
	private final int TMAX = 8;
	private final int NMAX = 100;
	private int tentativiFatti;
	private boolean inGioco;
	private Set<Integer> tentativi;
	
	public Model(){
		this.inGioco = false;
		this.tentativiFatti = 0;
	}
	public void nuovaPartita(){
		//gestione di una nuova partita
    	this.segreto = (int)((Math.random() * NMAX) +1);
    	this.tentativiFatti = 0;
    	this.inGioco = true;
    	this.tentativi = new HashSet<Integer>();
	}
	public int tentativi(int tentativo){
		if(!inGioco){
			throw new IllegalStateException("La partita è già terminata\n");
		}
		
		if(!tentativoValido(tentativo)) {
    		throw new InvalidParameterException("Devi inserire un numero che non hai ancora utilizzato tra tra 1 e "+ NMAX+" \n");
    	}
		this.tentativiFatti ++;
		this.tentativi.add(tentativo);
		if(this.tentativiFatti == TMAX){
			this.inGioco = false;
		}
		if(tentativo == this.segreto){
			this.inGioco = false;
			return 0;
		}
		if(tentativo < this.segreto){
			return -1;
		}else{
			return 1;
		}
	}
	private boolean tentativoValido(int tentativo) {
		if(tentativo < 1 || tentativo > NMAX){
			return false;
		}else{
			if(this.tentativi.contains(tentativo)){
				return false;
			}
			return true;
		}
		
	}
	public int getSegreto() {
		return segreto;
	}
	public int getTMAX() {
		return TMAX;
	}
	public int getTentativiFatti() {
		return tentativiFatti;
	}
	
}
