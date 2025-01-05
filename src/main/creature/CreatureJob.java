package main.creature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import main.hopital.HopitalFantastique;
import main.maladie.Maladie;

public class CreatureJob implements Runnable {

	private HopitalFantastique hopital;
	
	public CreatureJob(HopitalFantastique hopital) {
		this.hopital = hopital;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; !HopitalFantastique.quitter && i < 100 ; ++i) {
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Random random = new Random();
		while(!HopitalFantastique.quitter) {
			if (!hopital.getToutesCreatures().isEmpty()) {
				List<Creature> creatures = hopital.getToutesCreatures();
				for (int i = 0; i < creatures.size(); ++i) {
					Creature creature = creatures.get(i);
					List<Maladie> maladies = new ArrayList<Maladie>(creature.getMaladies());
					for (Maladie maladie : maladies) {
						if (random.nextInt(100) < 20) {
							maladie.augmenterNiveau();
							if (maladie.estLetal()) {
								creature.trepasser();
								if (maladies.isEmpty() || !Arrays.asList(creature.getClass().getInterfaces()).contains(Regenerant.class)) {
									hopital.enleverCreature(creature);
								}
							}
						}
					}
					if (random.nextInt(100) < 50) {
						creature.attendre();
					}
				}
			}
			try {
				for (int i = 0; !HopitalFantastique.quitter && i < 100 ; ++i) {
					Thread.sleep(300);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
