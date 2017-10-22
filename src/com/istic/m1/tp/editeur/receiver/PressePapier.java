package com.istic.m1.tp.editeur.receiver;

public class PressePapier {

	private String pressePapier;


	public PressePapier() {
		super();
		this.pressePapier = "";
	}


	public String getPressePapier() {
		return pressePapier;
	}


	public void setPressePapier(String pressePapier) {
		this.pressePapier = pressePapier;
	}


	@Override
	public String toString() {
		return "PressePapier [pressePapier=" + pressePapier + "]";
	}

}
