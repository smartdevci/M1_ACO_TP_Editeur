package com.istic.m1.tp.editeur.luncher;

import com.istic.m1.tp.editeur.invoker.MonIHM;
import com.istic.m1.tp.editeur.receiver.MoteurEditionImpl;

public class monApplication {

	public static void main(String[] args) {
		/*StringBuffer b=new StringBuffer("Texte par defaut");
		b.delete(2, 5);
		System.out.println(b.toString());
		*/

		MonIHM fenetre=new MonIHM();
		MoteurEditionImpl moteur=new MoteurEditionImpl(fenetre);
		moteur.lancer();

	}

}
