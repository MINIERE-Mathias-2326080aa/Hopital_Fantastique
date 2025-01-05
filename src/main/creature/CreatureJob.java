package main.creature;

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
		Random random = new Random();
		while(!HopitalFantastique.quitter) {
			if (!hopital.getToutesCreatures().isEmpty()) {
				List<Creature> creatures = hopital.getToutesCreatures();
				for (int i = 0; i < creatures.size(); ++i) {
					Creature creature = creatures.get(i);
					for (Maladie maladie : creature.getMaladies()) {
						if (random.nextInt(100) < 20) {
							maladie.augmenterNiveau();
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
	
//	public void contaminer() {
//		List<Creature> creatures = new LinkedList<Creature>(hopital.getListeAttente());
//		for (ServiceMedical service : hopital.getListeService()) {
//			creatures.addAll(service.getCreatures());
//		}
//		for (Creature creature : creatures) {
//			creature.attendre();
//			if(creature.getMoral()<10) {
//				
//			}
//		}
//	}

}
