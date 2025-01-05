package main.serviceMedical;

import java.util.ArrayList;
import java.util.List;
import main.creature.Creature;

public class ServiceMedical {
	private String nom;
	private int superficie;
	private int nbMaxCreatures;
	private List<Creature> creatures = new ArrayList<Creature>();
	private int budget;
	
	public ServiceMedical(String nom, int superficie, int nbMaxCreatures, int budget) {
		super();
		this.nom = nom;
		this.superficie = superficie;
		this.nbMaxCreatures = nbMaxCreatures;
		this.budget = budget;
	}
	
	public void afficherCaracteristiques() {
		System.out.println("Nom : " + nom);
		System.out.println("Superficie : " + superficie + "m2");
		System.out.print("Budget : ");
		switch(this.budget) {
			case(0):
				System.out.println("inexistant");
				break;
			case(1):
				System.out.println("médiocre");
				break;
			case(2):
				System.out.println("insuffisant");
				break;
			case(3):
				System.out.println("faible");
		}
		System.out.println("Nombre maximum de créature : " + nbMaxCreatures);
		System.out.println("Créatures :");
		for (Creature creature : creatures) {
			System.out.println("---------------------------");
			creature.afficherCreature();
		}
	}
	
	public void ajouterCreature(Creature creature) {
		creature.setCreaturesProches(new ArrayList<Creature>(creatures));
		if (!creatures.isEmpty()) {
			for (int i = 0; i < creatures.size(); ++i) {
				creatures.get(i).ajouterCreatureProche(creature);
			}
		}
		creatures.add(creature);
	}
	
	public void enleverCreature(Creature creature) {
		this.creatures.remove(creature);
		for (Creature patient : creatures) {
			patient.enleverCreatureProche(creature);
		}
	}
	
	public void reviserBudget() {
		
	}
	
	public boolean peutAjouter(Creature creature) {
		return (creatures.isEmpty() || (creatures.getFirst().getClass() == creature.getClass() && (creatures.size() < nbMaxCreatures)));
	}

	public List<Creature> getCreatures() {
		return creatures;
	}

	public String getNom() {
		return nom;
	}
	
}
