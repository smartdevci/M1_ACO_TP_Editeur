package com.istic.m1.tp.editeur.receiver;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.istic.m1.tp.editeur.invoker.MonIHM;
import com.istic.m1.tp.editeur.observer.Observable;
import com.istic.m1.tp.editeur.observer.Observer;

//Le moteur observe l'IHM et est observé par l'IHM
public class MoteurEditionImpl implements MoteurEdition, Observer, Observable {

	MonIHM graphique;
	PressePapier presse_papier;
	Selection selection;
	Buffer buffer;

	List<Observer> listeObserveurDuMoteur;

	// Le couplage de l'interface graphique et la classe MoteurEdition
	public MoteurEditionImpl(MonIHM graphique) {
		super();
		this.graphique = graphique;
		graphique.addObserver(this);
		graphique.initCommand(this);// initialisation de mon moteur dans mes
									// commandes concrètes

		this.listeObserveurDuMoteur=new ArrayList<Observer>();
		listeObserveurDuMoteur.add(graphique);

		presse_papier = new PressePapier();
		selection = new Selection(); // initialisation de la
		buffer = new Buffer();

		notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());

	}


	@Override
	public String toString() {
		return "---------------------------------------------\nMoteurEditionImpl \n[presse_papier=" + presse_papier
				+ ", \nselection=" + selection + ", \nbuffer=" + buffer + "]";
	}

	@Override
	public void selectionner() {
		selection.setDebut(0);
		selection.setFin(buffer.getText().length());
		notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());
	}


	@Override
	public void coller() {
		buffer.getStringBuffer().delete(selection.getDebut(), selection.getFin());
		insererTexte(presse_papier.getPressePapier());
		selection.setDebut(selection.getDebut()+presse_papier.getPressePapier().length());
		selection.setFin(selection.getDebut());
		notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());
	}

	@Override
	public void copier() {

		if(selection.getDebut()!=selection.getFin())
		{
			presse_papier.setPressePapier(buffer.getText().substring(selection.getDebut(), selection.getFin()));
			notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());

		}

	}

	@Override
	public void insererTexte(String texte) {
		buffer.getStringBuffer().insert(selection.getDebut(), texte);
		notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());
		// buffer.setTexte_du_buffer(buffer.getTexte_du_buffer());
	}



	@Override
	public void couper() {
		//on va couper si du texte a été selectionné, c'est à dire debut_selection est différent de fin_selection
		if(selection.getDebut()!=selection.getFin())
		{
			presse_papier.setPressePapier(buffer.getText().substring(selection.getDebut(), selection.getFin()));
			StringBuffer b=buffer.getStringBuffer().delete(selection.getDebut(), selection.getFin());
			selection.setFin(selection.getDebut());
			notifyAllObserver(this.selection.getDebut(),this.selection.getFin(), this.presse_papier.getPressePapier(), this.buffer.getText());

		}
	}

	public void lancer() {
		graphique.run();
	}



	@Override
	public void update(int debut_selection, int fin_selection, String presse_papier, String buffer) {
		selection.setDebut(debut_selection);
		selection.setFin(fin_selection);
		this.presse_papier.setPressePapier(presse_papier);
		this.buffer.setText(buffer);

		System.out.println(this);
	}


	@Override
	public void addObserver(Observer obs) {
		this.listeObserveurDuMoteur.add(obs);

	}


	@Override
	public void deleteObserver(Observer obs) {
		this.listeObserveurDuMoteur.remove(obs);
	}


	@Override
	public void notifyAllObserver(int debut_selection,int fin_selection, String presse_papier, String buffer) {
		for(Observer obs : this.listeObserveurDuMoteur)
		{
			obs.update(debut_selection,fin_selection, presse_papier, buffer);
		}
	}


	@Override
	public void deleteObserverAll() {
		this.listeObserveurDuMoteur=new ArrayList<Observer>();
	}

}
