package main.hopital;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import main.creature.*;
import main.maladie.Maladie;
import main.serviceMedical.*;

public class HopitalFantastique{
	private static Random random = new Random();
	private List<Creature> listeAttente;
	private List<ServiceMedical> listeService;
	private static List<Maladie> maladies;
	private Map<Integer, Medecin> medecins;
	private static int compteurMedecin = 0;
	public static boolean quitter = false;
	
	public HopitalFantastique() {
		this.listeAttente = new ArrayList<Creature>();
		this.listeService = new ArrayList<ServiceMedical>();
		this.medecins = new HashMap<Integer, Medecin>();
	}

	public List<Creature> getListeAttente() {
		return this.listeAttente;
	}

	public void setListeAttente(ArrayList<Creature> listeAttente) {
		this.listeAttente = listeAttente;
	}

	public List<ServiceMedical> getListeService() {
		return this.listeService;
	}

	public void setListeService(List<ServiceMedical> listeService) {
		this.listeService = listeService;
	}

	public static List<Maladie> getMaladies() {
		return HopitalFantastique.maladies;
	}
	
	public static void setMaladies(List<Maladie> maladies) {
		HopitalFantastique.maladies = maladies;
	}

	public Map<Integer, Medecin> getMedecins() {
		return this.medecins;
	}

	public void setMedecins(List<Medecin> medecins) {
		for (Medecin medecin : medecins) {
			this.medecins.put(compteurMedecin, medecin);
			++compteurMedecin;
		}
	}

	public static Creature genererCreature() {
		Maladie maladie = getMaladies().get(random.nextInt(getMaladies().size()));
		Creature creature;
		String sexe = random.nextBoolean() ? "H" : "F";
		switch(random.nextInt(7)) {
			case(0):
				creature = new Elfe("elfe", sexe, random.nextInt(60,120), random.nextInt(150, 200), random.nextInt(15,100), 100, maladie);
				break;
			case(1):
				creature = new HommeBete("hommebete", sexe, random.nextInt(60,120), random.nextInt(150, 200), random.nextInt(15,100), 100, maladie);
				break;
			case(2):
				creature = new Lycanthrope("lycanthrope", sexe,random.nextInt(80,200), random.nextInt(150, 250), random.nextInt(15,100), 100, maladie);
				break;	
			case(3):
				creature = new Nain("nain", sexe, random.nextInt(50,90), random.nextInt(100, 150), random.nextInt(15,100), 100, maladie);
				break;
			case(4):
				creature = new Orque("orque", sexe, random.nextInt(100,200), random.nextInt(170, 250), random.nextInt(15,100), 100, maladie);
				break;
			case(5):
				creature = new Vampire("vampire", sexe, random.nextInt(60,120), random.nextInt(150, 200), random.nextInt(15,100), 100, maladie);
				break;
			case(6):
				creature = new Zombie("zombie", sexe, random.nextInt(60,120), random.nextInt(150, 200), random.nextInt(15,100), 100, maladie);
				break;
			default:
				creature = new Reptilien("reptilien", sexe, random.nextInt(60,120), random.nextInt(150, 200), random.nextInt(15,100), 100, maladie);
		}
		return creature;
	}
	
	public void ajouterCreature(Creature creature) {
		List<Creature> creaturesProches = new ArrayList<Creature>(getListeAttente());
		creature.setCreaturesProches(creaturesProches);
		for (Creature patient : creaturesProches) {
			patient.ajouterCreatureProche(creature);
		}
		listeAttente.addLast(creature);
	}

	public void afficherListeAttente() {
		System.out.println("---------------------------");
		System.out.println("Liste des créatures de la file d'attente :");
		List<Creature> creatures = new ArrayList<Creature>(getListeAttente());
		for (Creature creature : creatures) {
			System.out.println(creature);
		}
	}
	public void afficherMedecins() {
		System.out.println("---------------------------");
		System.out.println("Liste des médecins:");
		for (Entry <Integer, Medecin> entry : medecins.entrySet()) {
			System.out.println("(" + entry.getKey() + ") " + entry.getValue());
		}
	}
	public static void afficherMaladies() {
		System.out.println("---------------------------");
		System.out.println("Liste des maladies:");
		for (Maladie maladie : maladies) {
			System.out.println(maladie);
		}
	}
	public void afficherServicesMedicaux() {
		System.out.println("---------------------------");
		System.out.println("Liste des services médicaux :");
		for (ServiceMedical serviceMedical : listeService) {
			serviceMedical.afficherCaracteristiques();
			System.out.println("---------------------------");
		}
	}
	public void afficherNbPatients() {
		int nbPatient = listeAttente.size();
		for (ServiceMedical service : listeService) {
			nbPatient += service.getCreatures().size();
		}
		System.out.println("L'hopital posssède actuellement " + nbPatient + " patients.");
	}
	
	public void ajouterMedecin() {
		System.out.println("Comment voulez-vous ajouter un médecin ?");
		System.out.println("0 Automatiquement");
		System.out.println("1 Manuellement");
		Scanner scanner = new Scanner(System.in);
		int choix = scanner.nextInt();
		scanner.nextLine();
		while (0 != choix && 1!= choix) {
			System.out.println("Veuillez recommencer.");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		Medecin medecin;
		switch(choix) {
			case(0):
				medecin = new Medecin(genererCreature());
				this.medecins.put(compteurMedecin, medecin);
				++compteurMedecin;
				break;
			case(1):{
				String type = null;
				String nom;
				String sexe;
				int age;
				System.out.println("Quel est le type du médecin ?");
				System.out.println("0 Elfe");
				System.out.println("1 HommeBete");
				System.out.println("2 Lycanthrope");
				System.out.println("3 Nain");
				System.out.println("4 Orque");
				System.out.println("5 Reptilien");
				System.out.println("6 Vampire");
				System.out.println("7 Zombie");
				scanner = new Scanner(System.in);
				int choixT = scanner.nextInt();
				scanner.nextLine();
				while (choixT < 0 || 7 < choixT) {
					System.out.println("Veuillez entrer un nombre entre 0 et 7.");
					choixT = scanner.nextInt();
					scanner.nextLine();
				}
				switch(choixT) {
					case(0):
						type = Elfe.class.getSimpleName();
						break;
					case(1):
						type = HommeBete.class.getSimpleName();
						break;
					case(2):
						type = Lycanthrope.class.getSimpleName();
						break;
					case(3):
						type = Nain.class.getSimpleName();
						break;
					case(4):
						type = Orque.class.getSimpleName();
						break;
					case(5):
						type = Reptilien.class.getSimpleName();
						break;
					case(6):
						type = Vampire.class.getSimpleName();
						break;
					case(7):
						type = Zombie.class.getSimpleName();
						break;
					default:
						
						break;
				}
				System.out.println("Entrez un nom :");
				nom = scanner.next();
				scanner.nextLine();
				
				System.out.println("Choisissez un sexe :");
				System.out.println("0 Masculin");
				System.out.println("1 Féminin");
				int choixS = scanner.nextInt();
				scanner.nextLine();
				while (0 != choixS && 1 != choixS) {
					System.out.println("Veuillez choisir entre 0 ou 1.");
					choixS = scanner.nextInt();
					scanner.nextLine();
				}
				sexe = (choixS == 0) ? "H" : "F";
				
				System.out.println("Entrez un âge :");
				age = scanner.nextInt();
				scanner.nextLine();
				
				medecin = new Medecin(type, nom, sexe, age);
				this.medecins.put(compteurMedecin, medecin);
				++compteurMedecin;
				break;
			}
				
		}
	}
	
	public void creerServiceMedical() {
		String type;
		String nom;
		int superficie;
		int nbMaxCreatures;
		int budget;
		ServiceMedical service = null;
		
		System.out.println("Quel type de service médical voulez-vous?");
		System.out.println("0 Service médical standard");
		System.out.println("1 Crypte");
		System.out.println("2 Centre de quarantaine");
		Scanner scanner = new Scanner(System.in);
		int choix = scanner.nextInt();
		scanner.nextLine();
		while (choix < 0 || 2 < choix) {
			System.out.println("Veuillez entrer un nombre entre 0 et 2.");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		
		System.out.println("Entrez le nom de votre service");
		nom = scanner.next();
		scanner.nextLine();
		
		System.out.println("Entrez la superficie de votre service");
		superficie = scanner.nextInt();
		scanner.nextLine();
		while (superficie <=0) {
			System.out.println("Veuillez entrer un nombre supérieur à 0");
			superficie = scanner.nextInt();
			scanner.nextLine();
		}
		
		System.out.println("Entrez le nombre maximum de créatures que vous voulez dans votre service");
		nbMaxCreatures = scanner.nextInt();
		scanner.nextLine();
		while (nbMaxCreatures <= 0) {
			System.out.println("Veuillez entrer un nombre supérieur à 0");
			nbMaxCreatures = scanner.nextInt();
			scanner.nextLine();
		}
		
		System.out.println("Choisissez le budget de votre service");
		System.out.println("0 Inexistant");
		System.out.println("1 Médiocre");
		System.out.println("2 Insuffisant");
		System.out.println("3 Faible");
		budget = scanner.nextInt();
		scanner.nextLine();
		while (budget < 0 || 3 < budget) {
			System.out.println("Veuillez entrer un nombre entre 0 et 3");
			superficie = scanner.nextInt();
			scanner.nextLine();
		}
		
		switch(choix) {
			case(0):
				service = new ServiceMedical(nom, superficie, nbMaxCreatures, budget);
				break;
			case(1):
				service = new Crypte(nom, superficie, nbMaxCreatures, budget);
				break;
			case(2):
				service = new CentreDeQuarantaine(nom, superficie, nbMaxCreatures, budget);
		}
		this.listeService.add(service);
	}
	
	public void gererMedecins() {
		
	}
}
