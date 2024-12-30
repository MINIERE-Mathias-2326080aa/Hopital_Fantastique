package main.creature;

public class Vampire extends Creature implements Demoralisant, Regenerant {

	public Vampire(String nom, String sexe, int poids, int taille, int age, int moral) {
		super(nom, sexe, poids, taille, age, moral);
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

}
