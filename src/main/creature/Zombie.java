package main.creature;

import main.maladie.Maladie;

public class Zombie extends Creature implements Regenerant {

	public Zombie(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void regenerer() {

	}

	@Override
	public void trepasser() {
		
	}

}
