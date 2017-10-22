package com.istic.m1.tp.editeur.invoker;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.istic.m1.tp.editeur.commande.Command;
import com.istic.m1.tp.editeur.commandeConcrete.Coller;
import com.istic.m1.tp.editeur.commandeConcrete.Copier;
import com.istic.m1.tp.editeur.commandeConcrete.Couper;
import com.istic.m1.tp.editeur.commandeConcrete.Inserer;
import com.istic.m1.tp.editeur.commandeConcrete.Selectionner;
import com.istic.m1.tp.editeur.observer.Observable;
import com.istic.m1.tp.editeur.observer.Observer;
import com.istic.m1.tp.editeur.receiver.MoteurEdition;

public class MonIHM extends JFrame implements Observable, Observer{

	public static final String EDITOR_NAME="Mini Editeur";
	public static final int IHM_HEIGHT=600;
	public static final int IHM_WIDTH=800;
	private static final String details1="PRESSE PAPIER : ";
	private static final String details2="BUFFER : ";
	private static final String details3="DEBUT SELECTION : ";
	private static final String details4="FIN SELECTION : ";
	int i=0;



	//Liste des observateurs
	List<Observer> listeObservateur;
	private Command copier,couper,coller,inserer,selectionner;  //les objets command qui vont executer les commandes de l'IHM

	private int debut_selection, fin_selection;
	private String presse_papier;

	//String buffer;

	//La barre de menu
	private JMenuBar barre_de_menu;

	//Les menus
	private JMenu menuFichier;
	private JMenu menuEdition;

	//Les JPanel pour gerer la mis en page de l'interface
	private JPanel panneauPrincipal;


	//Les JMenuItem pour les bouton
	private JMenuItem menuCopier;
	private JMenuItem menuCouper;
	private JMenuItem menuColler;
	private JMenuItem menuSelectionnerTout;
	private JMenuItem menuFermer;
	private JMenuItem menuEnregistrer;

	/***********FIN MENU**************/

	//Les composants de la fenêtres
	private JTextArea zoneDeSaisie;
	private JTextArea zoneDeNotification;



	/*
	private JMenuItem menuCopier;*/






	public MonIHM()
	{

		this.setSize(IHM_WIDTH, IHM_HEIGHT);  //definit la taille de la fenetre
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //Pour fermer la fenetre quand on clique sur la croix rouge (bouton fermer) de la fenetre
		this.setTitle(EDITOR_NAME);  //defini le titre de l'editeur
		this.setLocationRelativeTo(null);

		//initialisation des composants de l'interface
		this.initialize();
		this.ajouterEvenement();

		menuCopier.setMnemonic(KeyEvent.VK_C);
		menuCouper.setMnemonic(KeyEvent.VK_X);
		menuColler.setMnemonic(KeyEvent.VK_V);

		//placement des composants sur le menu Fichier
		menuFichier.add(menuEnregistrer);
		menuFichier.add(menuFermer);

		//placement des composants sur le menu Edition
		menuEdition.add(menuCopier);
		menuEdition.add(menuCouper);
		menuEdition.add(menuColler);
		menuEdition.add(menuSelectionnerTout);

		//placement du Menu
		barre_de_menu.add(menuFichier);
		barre_de_menu.add(menuEdition);
		this.setJMenuBar(barre_de_menu);





		//Ajout au panneau
		panneauPrincipal.setLayout(new BorderLayout());
		zoneDeSaisie.setLineWrap(true);  //Aller automatiquement à la ligne
		zoneDeNotification.setBackground(Color.LIGHT_GRAY);


		panneauPrincipal.add(zoneDeSaisie,BorderLayout.CENTER);
		panneauPrincipal.add(zoneDeNotification,BorderLayout.SOUTH);

		this.setContentPane(panneauPrincipal);

	}


	public JMenuItem getMenuSelectionnerTout() {
		return menuSelectionnerTout;
	}


	public void setMenuSelectionnerTout(JMenuItem menuSelectionnerTout) {
		this.menuSelectionnerTout = menuSelectionnerTout;
	}


	public void run()
	{
		this.setVisible(true);

	}


	public JMenu getMenuFichier() {
		return menuFichier;
	}


	public void setMenuFichier(JMenu menuFichier) {
		this.menuFichier = menuFichier;
	}


	public JMenu getMenuEdition() {
		return menuEdition;
	}


	public void setMenuEdition(JMenu menuEdition) {
		this.menuEdition = menuEdition;
	}


	public JMenuItem getMenuCopier() {
		return menuCopier;
	}


	public void setMenuCopier(JMenuItem menuCopier) {
		this.menuCopier = menuCopier;
	}


	public JMenuItem getMenuCouper() {
		return menuCouper;
	}


	public void setMenuCouper(JMenuItem menuCouper) {
		this.menuCouper = menuCouper;
	}


	public JMenuItem getMenuColler() {
		return menuColler;
	}


	public void setMenuColler(JMenuItem menuColler) {
		this.menuColler = menuColler;
	}


	public JMenuItem getMenuFermer() {
		return menuFermer;
	}


	public void setMenuFermer(JMenuItem menuFermer) {
		this.menuFermer = menuFermer;
	}


	public JMenuItem getMenuEnregistrer() {
		return menuEnregistrer;
	}


	public void setMenuEnregistrer(JMenuItem menuEnregistrer) {
		this.menuEnregistrer = menuEnregistrer;
	}


	public JTextArea getZoneDeSaisie() {
		return zoneDeSaisie;
	}


	public void setZoneDeSaisie(JTextArea zoneDeSaisie) {
		this.zoneDeSaisie = zoneDeSaisie;
	}


	public JTextArea getZoneDeNotification() {
		return zoneDeNotification;
	}


	public void setZoneDeNotification(JTextArea zoneDeNotification) {
		this.zoneDeNotification = zoneDeNotification;
	}


	/**
	 * Initialise toutes les commandes concrète et les lie avec le moteur
	 * @param moteur : le moteur de l'application
	 */
	public void initCommand(MoteurEdition moteur)
	{
		copier = new Copier(moteur);
		couper = new Couper(moteur);
		coller = new Coller(moteur);
		inserer = new Inserer(moteur);
		selectionner = new Selectionner(moteur);
	}


	/**
	 * Initialise tous les composants présent sur l'IHM (Ménu et boutons ...)
	 */
	public void initialize()
	{
		listeObservateur=new ArrayList<Observer>();
		debut_selection=0;
		fin_selection=0;
		presse_papier="";

		barre_de_menu=new JMenuBar();
		menuFichier=new JMenu("Fichier");
		menuEdition=new JMenu("Edition");
		panneauPrincipal=new JPanel();

		menuCopier=new JMenuItem("Copier");
		menuCouper=new JMenuItem("Couper");
		menuColler=new JMenuItem("Coller");
		menuFermer=new JMenuItem("Fermer");
		menuSelectionnerTout=new JMenuItem(" Tout selectionner");
		menuEnregistrer=new JMenuItem("Enregistrer");

		zoneDeSaisie = new JTextArea("",10,10);
		zoneDeNotification=new JTextArea("",15,1);


	}


	@Override
	public void addObserver(Observer obs) {
		listeObservateur.add(obs);
	}


	@Override
	public void notifyAllObserver(int debut_selection,int fin_selection, String presse_papier, String buffer) {
		for(Observer obs : this.listeObservateur)
		{
			obs.update(debut_selection,fin_selection, presse_papier, buffer);
		}
	}


	@Override
	public void deleteObserver(Observer obs) {
		listeObservateur.remove(obs);
	}


	@Override
	public void deleteObserverAll() {
		listeObservateur=new ArrayList<Observer>();
	}





	public void ajouterEvenement()
	{
		//KEY LISTENER
		//Gestion des evenement liés au deplacement du curseur

		this.getZoneDeSaisie().addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {
				notifyAllObserver(getZoneDeSaisie().getSelectionStart(),getZoneDeSaisie().getSelectionEnd(),presse_papier,getZoneDeSaisie().getText());
			}

			@Override
			public void keyTyped(KeyEvent e) {}


		});

		//CARET LISTENER
		//Gestion des evenement liés au deplacement du curseur

		this.getZoneDeSaisie().addCaretListener(new CaretListener(){

			@Override
			public void caretUpdate(CaretEvent c) {
				notifyAllObserver(getZoneDeSaisie().getSelectionStart(),getZoneDeSaisie().getSelectionEnd(),presse_papier,getZoneDeSaisie().getText());
			}

		});




		//ACTION LISTENER
		//Gestion des evenements liés au clic
		//dans le menu ou sur la page

		this.getMenuFermer().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				dispose();
			}
		});



		this.getMenuCopier().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				copier.execute();
			}
		});


		this.getMenuCouper().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				couper.execute();
			}
		});

		this.getMenuColler().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				coller.execute();
			}
		});




		this.getMenuSelectionnerTout().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent a) {
				selectionner.execute();
			}
		});




	}

	/**
	 * Cette fonction met à jour l'IHM quand le moteur change
	 * @param debut_selection : le debut de la selection
	 * @param fin_selection : la fin de la selection
	 * @param presse_papier : le presse papier
	 * @param buffer : le texte du buffer
	 */
	@Override
	public void update(int debut_selection, int fin_selection, String presse_papier, String buffer) {
		
		this.debut_selection=debut_selection;
		this.fin_selection=fin_selection;
		this.presse_papier=presse_papier;

		
		//SwingUtilities.invokeLater(updateIHM);
		this.getZoneDeSaisie().setText(buffer);
		this.getZoneDeSaisie().setCaretPosition(debut_selection);
		this.getZoneDeSaisie().select(debut_selection, fin_selection);

		//Update notification zone
		this.getZoneDeNotification().setText(
				details1+presse_papier+"\n"+
				details2+this.getZoneDeSaisie().getText()+"\n"+
				details3+debut_selection+"\n"+
				details4+fin_selection+"\n");


		//this.enableDesable();
	}








	public void enableDesable()
	{
		if(this.presse_papier=="")
			menuColler.setEnabled(false);
		else
			menuColler.setEnabled(true);


		if(this.debut_selection==this.fin_selection)
		{
			menuCouper.setEnabled(false);
			menuCopier.setEnabled(false);
		}
		else
		{
			menuCouper.setEnabled(false);
			menuCopier.setEnabled(true);
		}
	}









}
