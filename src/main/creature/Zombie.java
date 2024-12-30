package main.creature;

public class Zombie extends Creature implements Regenerant {

	public Zombie(String nom, String sexe, int poids, int taille, int age, int moral) {
		super(nom, sexe, poids, taille, age, moral);
	}

	@Override
	public void regenerer() {

	}

}
