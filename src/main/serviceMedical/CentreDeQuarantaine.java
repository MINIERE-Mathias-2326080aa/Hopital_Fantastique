package main.serviceMedical;

import java.util.Arrays;

import main.creature.*;

public class CentreDeQuarantaine extends ServiceMedical {

	public CentreDeQuarantaine(String nom, int superficie, int nbMaxCreatures, int budget) {
		super(nom, superficie, nbMaxCreatures, budget);
	}

	@Override
	public void ajouterCreature(Creature creature) {
		if (Arrays.asList(creature.getClass().getInterfaces()).contains(Contaminant.class)) {
			super.ajouterCreature(creature);
		}
	}

	@Override
	public void reviserBudget() {
		
	}

}
