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
	/**
	 * Affiche les caratéristiques d'un service médical.
	 * @param service Le service médical.
	 */
	public void examinerService(ServiceMedical service) {
		service.afficherCaracteristiques();
		this.nbAction -= 1;
	}
	/**
	 * Enlève une maladie d'une créature.
	 * @param creature La créature soignée.
	 * @param maladie La maladie enlevée.
	 */
	public void soignerCreature(Creature creature, Maladie maladie) {
		creature.enleverMaladie(maladie);
		this.nbAction -= 1;
	}
	/**
	 * Déplace une créature d'un service médical à un autre.
	 * @param creature La créature déplacée.
	 * @param serviceSource Le service médical d'où provient la créature.
	 * @param serviceDestination Le service médical de destination.
	 */
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

	/**
	 * Déplace une créature de la liste d'attente à un service médical.
	 * @param creature La créature déplacée.
	 * @param source La liste d'où provient la créature.
	 * @param serviceDestination Le service médical de destination.
	 */
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
	/**
	 * Renvoie vrai si le nombre d'action est supérieur à 0.
	 * @return true si le nombre d'action est supérieur à 0.
	 */
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
