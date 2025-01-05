package main.hopital;

import java.util.Random;

import main.creature.*;

public class HopitalJob implements Runnable {
	private HopitalFantastique hopital;

	public HopitalJob(HopitalFantastique hopital) {
		this.hopital = hopital;
	}

	@Override
	public void run() {
		while (!HopitalFantastique.quitter) {
			Creature creature = HopitalFantastique.genererCreature();
			hopital.ajouterCreature(creature);
			int tempsAttente = new Random().nextInt(200,600);
			try {
				for (int i = 0; !HopitalFantastique.quitter && i < 100 ; ++i) {
					Thread.sleep(tempsAttente);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
