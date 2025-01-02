package main.creature;

import main.maladie.Maladie;

public class Vampire extends Creature implements Demoralisant, Regenerant {

	public Vampire(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void regenerer() {

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
