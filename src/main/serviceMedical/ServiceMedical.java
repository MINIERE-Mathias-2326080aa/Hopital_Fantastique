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
}
