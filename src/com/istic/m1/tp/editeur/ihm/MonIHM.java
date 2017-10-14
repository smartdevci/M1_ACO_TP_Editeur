package com.istic.m1.tp.editeur.ihm;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.*;

public class MonIHM extends JFrame{

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

		this.setSize(Data.width, Data.height);  //definit la taille de la fenetre
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);  //Pour fermer la fenetre quand on clique sur la croix rouge (bouton fermer) de la fenetre
		this.setTitle(Data.EditorName);  //defini le titre de l'editeur
		this.setLocationRelativeTo(null);

		//initialisation des composants de l'interface
		this.initialize();

		//placement des composants sur le menu Fichier
		menuFichier.add(menuEnregistrer);
		menuFichier.add(menuFermer);

		//placement des composants sur le menu Edition
		menuEdition.add(menuCopier);
		menuEdition.add(menuCouper);
		menuEdition.add(menuColler);
		
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
		
		
		
		//Le corps




	}




	public void initialize()
	{
		barre_de_menu=new JMenuBar();
		menuFichier=new JMenu("Fichier");
		menuEdition=new JMenu("Edition");
		panneauPrincipal=new JPanel();

		menuCopier=new JMenuItem("Copier");
		menuCouper=new JMenuItem("Couper");
		menuColler=new JMenuItem("Coller");
		menuFermer=new JMenuItem("Fermer");
		menuEnregistrer=new JMenuItem("Enregistrer");
		
		zoneDeSaisie = new JTextArea("",10,10);
		zoneDeNotification=new JTextArea("",15,1);
	}




}
