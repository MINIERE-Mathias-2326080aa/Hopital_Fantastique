package main.creature;

import main.maladie.Maladie;

public class Elfe extends Creature implements Demoralisant {

	public Elfe(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}

	@Override
	public void demoraliser() {
		for (int i = 0; i < getCreaturesProches().size(); ++i) {
			Creature creature = getCreaturesProches().get(i);
			int moral = creature.getMoral();
			if (moral < 10) {
				creature.setMoral(0);
			} else {
				creature.diminuerMoral(10);;
			}
		}
		System.out.println("[" + this.getNom() + " démoralise toute les créatures proches d'elle]");
	}

	@Override
	public void trepasser() {
		System.out.println("[" + this.getNom() + " trépasse]");
		demoraliser();
	}

	@Override
	public boolean estVIP() {
		return true;
	}

}
