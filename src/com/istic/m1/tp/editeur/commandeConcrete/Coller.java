package com.istic.m1.tp.editeur.commandeConcrete;

import com.istic.m1.tp.editeur.commande.Command;
import com.istic.m1.tp.editeur.receiver.MoteurEdition;
import com.istic.m1.tp.editeur.receiver.MoteurEditionImpl;

public class Coller implements Command{

	MoteurEdition moteur;

	public Coller(MoteurEdition m) {
		super();
		this.moteur = m;
	}

	@Override
	public void execute() {
		moteur.coller();
	}

}
