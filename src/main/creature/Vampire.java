package main.creature;

import java.util.Random;

import main.maladie.Maladie;

public class Vampire extends Creature implements Demoralisant, Regenerant, Contaminant {

	public Vampire(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super(nom, sexe, poids, taille, age, moral, maladie);
	}
	
	@Override
	public void semporter() {
		super.semporter();
		for (Creature creature : super.getCreaturesProches()) {
			Random r = new Random();
			if (r.nextInt(100) > 50) {
				contaminer(creature);
			}
		}
	}
	
	@Override
	public void contaminer(Creature creature) {
		Random random = new Random();
		Maladie maladie = this.getMaladies().get(random.nextInt(this.getMaladies().size()));
		creature.ajouterMaladie(maladie);
	}

	@Override
	public void regenerer() {
		
	}

	@Override
	public void demoraliser() {
		for (int i = 0; i < getCreaturesProches().size(); ++i) {
			Creature creature = getCreaturesProches().get(i);
			int moral = creature.getMoral();
			if (moral < 10) {
				creature.setMoral(0);
			} else {
				creature.setMoral(moral-10);
			}
		}
	}

	@Override
	public void trepasser() {
		demoraliser();
		contaminer(getCreaturesProches().get(new Random().nextInt(getCreaturesProches().size())));
		regenerer();
	}

	@Override
	public boolean estVIP() {
		return true;
	}

}
