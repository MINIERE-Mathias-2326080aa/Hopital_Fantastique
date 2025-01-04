package main.serviceMedical;

import java.util.Arrays;

import main.creature.Creature;
import main.creature.Regenerant;

public class Crypte extends ServiceMedical {

	public Crypte(String nom, int superficie, int nbMaxCreatures, int budget) {
		super(nom, superficie, nbMaxCreatures, budget);
	}
	
	@Override
	public void ajouterCreature(Creature creature) {
		if (peutAjouter(creature)) {
			super.ajouterCreature(creature);
		}
	}

	public void reviserBudget() {
		
	}

	@Override
	public boolean peutAjouter(Creature creature) {
		return (super.peutAjouter(creature) || Arrays.asList(creature.getClass().getInterfaces()).contains(Regenerant.class));
	}
	

}
