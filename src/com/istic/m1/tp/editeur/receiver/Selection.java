package com.istic.m1.tp.editeur.receiver;

public class Selection {

	private int debut;
	private int fin;


	public Selection() {
		super();
		this.setDebut(0);
		this.setFin(0);  //fin de la selection, cette position est exclu

	}


	@Override
	public String toString() {
		return "Selection [debut=" + debut + ", fin_selection=" + fin + "]";
	}


	public int getDebut() {
		return debut;
	}


	public void setDebut(int debut) {
		this.debut = debut;
	}


	public int getFin() {
		return fin;
	}


	public void setFin(int fin_selection) {
		this.fin = fin_selection;
	}



}
