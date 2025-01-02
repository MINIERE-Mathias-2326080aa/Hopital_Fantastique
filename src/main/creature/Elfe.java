package main.creature;

import main.maladie.Maladie;

public class Elfe extends Creature implements Demoralisant {

	public Elfe(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void demoraliser() {

	}

	@Override
	public void trepasser() {
		
	}

	@Override
	public boolean estVIP() {
		return true;
	}

}
