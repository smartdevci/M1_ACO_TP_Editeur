package com.istic.m1.tp.editeur.commandeConcrete;

import com.istic.m1.tp.editeur.commande.Command;
import com.istic.m1.tp.editeur.receiver.MoteurEdition;
import com.istic.m1.tp.editeur.receiver.MoteurEditionImpl;

public class Inserer implements Command{

	MoteurEdition moteur;
	private String texte;



	public Inserer(MoteurEdition moteur) {
		super();
		this.moteur = moteur;
	}


	public String getTexte() {
		return texte;
	}


	public void setTexte(String texte) {
		this.texte = texte;
	}


	@Override
	public void execute() {
		moteur.insererTexte(texte);
	}

}
