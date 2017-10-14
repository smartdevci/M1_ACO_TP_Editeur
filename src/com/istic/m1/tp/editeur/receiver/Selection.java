package com.istic.m1.tp.editeur.receiver;

public class Selection {

	private int debut;
	private int fin_selection;
	private boolean isSelected;
	
	
	public Selection(int debut, int fin_selection, boolean isSelected) {
		super();
		this.debut = debut;
		this.fin_selection = fin_selection;
		this.isSelected = isSelected;
	}


	public int getDebut() {
		return debut;
	}


	public void setDebut(int debut) {
		this.debut = debut;
	}


	public int getFin_selection() {
		return fin_selection;
	}


	public void setFin_selection(int fin_selection) {
		this.fin_selection = fin_selection;
	}


	public boolean isSelected() {
		return isSelected;
	}


	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
	
	

}
