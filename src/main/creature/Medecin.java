package main.creature;

import java.util.ArrayList;
import java.util.List;

import main.maladie.Maladie;
import main.serviceMedical.ServiceMedical;

public class Medecin {
	private String type;
	private String nom;
	private String sexe;
	private int age;
	private int nbActionMax = 2;
	private int nbAction = nbActionMax;

	public Medecin(Creature creature) {
		super();
		this.type = creature.getClass().getSimpleName();
		this.nom = creature.getNom();
		this.sexe = creature.getSexe();
		this.age = creature.getAge();
	}
	
	public Medecin(String type, String nom, String sexe, int age) {
		super();
		this.type = type;
		this.nom = nom;
		this.sexe = sexe;
		this.age = age;
	}

	public void examinerService(ServiceMedical service) {
		service.afficherCaracteristiques();
		this.nbAction -= 1;
	}
	
	public void soignerCreature(Creature creature, Maladie maladie) {
		creature.enleverMaladie(maladie);
		this.nbAction -= 1;
	}
	
	public void transfererCreature(Creature creature, ServiceMedical serviceSource, ServiceMedical serviceDestination) {
		if (serviceDestination.peutAjouter(creature)) {
			serviceSource.enleverCreature(creature);
			serviceDestination.ajouterCreature(creature);
			System.out.println("La créature a bien été transférée.");
			this.nbAction -= 1;
		} else {
			System.out.println("La créature n'a pas pu être transférée.");
		}
	}

	public void transfererCreature(Creature creature, List<Creature> source, ServiceMedical serviceDestination) {
		if (serviceDestination.peutAjouter(creature)) {
			List<Creature> creaturesProches = new ArrayList<Creature>(source);
			for (Creature patient : creaturesProches) {
				patient.enleverCreatureProche(creature);
			}
			source.remove(creature);
			serviceDestination.ajouterCreature(creature);
			System.out.println("La créature a bien été transférée.");
			this.nbAction -= 1;
		} else {
			System.out.println("La créature n'a pas pu être transférée.");
		}
		
	}
	
	public boolean peutAgir() {
		return nbAction > 0;
	}

	public int getNbActionMax() {
		return nbActionMax;
	}

	public int getNbAction() {
		return nbAction;
	}

	public void setNbAction(int nbAction) {
		this.nbAction = nbAction;
	}

	@Override
	public String toString() {
		return "Medecin [type=" + type + ", nom=" + nom + ", sexe=" + sexe + ", age=" + age + "]";
	}
}
