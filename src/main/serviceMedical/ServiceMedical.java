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
			System.out.println(creature);
		}
	}
	
	public void ajouterCreature(Creature creature) {
		if (creatures.isEmpty()) {
			creatures.add(creature);
		} else {
			if (creatures.size() >= nbMaxCreatures) {
				System.out.println("Nombre maximum de creatures atteint.");
			} else if (creatures.getFirst().getClass() != creature.getClass()) {
				System.out.println("Les services médicaux peuvent accueillir uniquement des créatures de même type.");
			} else {
				creatures.add(creature);
			}
		}
	}
	
	public void enleverCreature(Creature creature) {
		this.creatures.remove(creature);
	}
	
	public void reviserBudget() {
		
	}

	public List<Creature> getCreatures() {
		return creatures;
	}
}
