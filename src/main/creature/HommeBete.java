package main.creature;

import java.util.Random;

import main.maladie.Maladie;

public class HommeBete extends Creature implements Contaminant{

	public HommeBete(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
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
		System.out.println("La créature " + creature.getNom() + " a été infecté par la maladie " + maladie.getNomComplet());
	}
	
	@Override
	public void trepasser() {
		
	}
	
	
}
