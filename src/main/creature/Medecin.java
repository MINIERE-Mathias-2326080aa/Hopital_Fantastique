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
	}
	
	public void soignerCreature(Creature creature, Maladie maladie) {
		creature.enleverMaladie(maladie);
	}
	
	public void transfererCreature(Creature creature, ServiceMedical serviceSource, ServiceMedical serviceDestination) {
		serviceSource.enleverCreature(creature);
		serviceDestination.ajouterCreature(creature);
	}

	public void transfererCreature(Creature creature, List<Creature> source, ServiceMedical serviceDestination) {
		List<Creature> creaturesProches = new ArrayList<Creature>(source);
		for (Creature patient : creaturesProches) {
			patient.enleverCreatureProche(creature);
		}
		source.remove(creature);
		serviceDestination.ajouterCreature(creature);
	}

	@Override
	public String toString() {
		return "Medecin [type=" + type + ", nom=" + nom + ", sexe=" + sexe + ", age=" + age + "]";
	}
}
