package com.istic.m1.tp.editeur.commandeConcrete;

import com.istic.m1.tp.editeur.commande.Command;
import com.istic.m1.tp.editeur.receiver.MoteurEdition;
import com.istic.m1.tp.editeur.receiver.MoteurEditionImpl;

public class Selectionner implements Command{

	MoteurEdition moteur;

	public Selectionner(MoteurEdition moteur) {
		super();
		this.moteur = moteur;
	}

	@Override
	public void execute() {
		moteur.selectionner();
	}

}
