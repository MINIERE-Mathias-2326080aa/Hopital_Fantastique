package main.creature;

import main.maladie.Maladie;

public class Lycanthrope extends Creature {

	public Lycanthrope(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void trepasser() {
		
	}

}
