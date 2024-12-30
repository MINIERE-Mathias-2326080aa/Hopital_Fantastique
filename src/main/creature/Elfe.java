package main.creature;

public class Elfe extends Creature implements Demoralisant {

	public Elfe(String nom, String sexe, int poids, int taille, int age, int moral) {
		super(nom, sexe, poids, taille, age, moral);
	}

	@Override
	public void demoraliser() {

	}

	@Override
	public void trepasser() {
		
	}

}
