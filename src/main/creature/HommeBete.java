package main.creature;

import main.maladie.Maladie;

public class HommeBete extends Creature {

	public HommeBete(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void trepasser() {
		
	}
	
	
}
