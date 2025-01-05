package main.creature;

import main.hopital.HopitalFantastique;

public class MedecinJob implements Runnable {
	private HopitalFantastique hopital;
	
	public MedecinJob(HopitalFantastique hopital) {
		this.hopital = hopital;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; !HopitalFantastique.quitter && i < 100 ; ++i) {
				Thread.sleep(600);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(!HopitalFantastique.quitter) {
			for (int i = 0; i < hopital.getMedecins().size();++i) {
				Medecin medecin = hopital.getMedecins().get(i);
				medecin.setNbAction(medecin.getNbActionMax());
			}
			System.out.println("-----Tous les médecins se sont reposés, leurs nombre d'action est désormais au maximum-----");
			try {
				for (int i = 0; !HopitalFantastique.quitter && i < 100 ; ++i) {
					Thread.sleep(600);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
