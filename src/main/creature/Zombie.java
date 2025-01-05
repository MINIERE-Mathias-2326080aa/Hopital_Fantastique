package main.creature;

import main.maladie.Maladie;

public class Zombie extends Creature implements Regenerant {

	public Zombie(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void regenerer() {
		for (int i = 0; i < getMaladies().size();++i) {
			if (getMaladies().get(i).estLetal()) {
				getMaladies().remove(i);
			}
		}
		System.out.println("[" + this.getNom() + " régénère]");
	}

	@Override
	public void trepasser() {
		System.out.println("[" + this.getNom() + " trépasse]");
		regenerer();
	}

}
