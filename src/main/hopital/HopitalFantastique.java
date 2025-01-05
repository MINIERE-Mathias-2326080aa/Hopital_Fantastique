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
	private Map<Integer, ServiceMedical> listeService;
	private static List<Maladie> maladies;
	private Map<Integer, Medecin> medecins;
	private int compteurMedecin = 0;
	private int nbMaxService = 10;
	public static boolean quitter = false;
	
	public HopitalFantastique() {
		this.listeAttente = new ArrayList<Creature>();
		this.listeService = new HashMap<Integer, ServiceMedical>();
		this.medecins = new HashMap<Integer, Medecin>();
	}

	public static Creature genererCreature() {
		Maladie maladie = new Maladie(getMaladies().get(random.nextInt(getMaladies().size())));
		Creature creature;
		String sexe = random.nextBoolean() ? "H" : "F";
		switch(random.nextInt(8)) {
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
				break;
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
	public void enleverCreature(Creature creature) {
		List<Creature> creaturesProches = new ArrayList<Creature>(getListeAttente());
		for (Creature patient : creaturesProches) {
			patient.enleverCreatureProche(creature);
		}
		listeAttente.remove(creature);
	}
 	public void ajouterService(ServiceMedical service) {
 		if (listeService.size() < nbMaxService) {
 			this.listeService.put(listeService.size(), service);
 		}
	}
 	public void enleverService(ServiceMedical service) {
 		this.listeService.remove(service);
 	}
	
 	// Fonctions d'affichage
	public void afficherListeAttente() {
		System.out.println("---------------------------");
		System.out.println("Liste des créatures de la file d'attente :");
		List<Creature> creatures = new ArrayList<Creature>(getListeAttente());
		for (int i = 0; i < creatures.size(); ++i) {
			Creature creature = creatures.get(i);
			System.out.println("(" + i + ") " + creature);
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
		if (listeService.isEmpty()) {
			System.out.println("L'hopital ne possède actuellement pas de service médical.");
		} else {
			System.out.println("Liste des services médicaux :");
			for (Entry<Integer, ServiceMedical> serviceMedical : listeService.entrySet()) {
				System.out.println("(" + serviceMedical.getKey() + ") " + serviceMedical.getValue().getNom());
			}
		}
	}
	public void afficherCreatures() {
		afficherListeAttente(); 
		for (Entry<Integer, ServiceMedical> service : listeService.entrySet()) {
			for (Creature creature : service.getValue().getCreatures()) {
				System.out.println(creature);
			}
		}
	}
	public void afficherNbPatients() {
		int nbPatient = listeAttente.size();
		for (Entry<Integer, ServiceMedical> service : listeService.entrySet()) {
			nbPatient += service.getValue().getCreatures().size();
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
				++this.compteurMedecin;
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
				++this.compteurMedecin;
				break;
			}
				
		}
	}
	
	public void creerServiceMedical() {
		if (listeService.size() >= nbMaxService) {
			System.out.println("Nombre maximal de service atteint.");
			return;
		} else if(nbMaxService - listeService.size() == 1) {
			System.out.println("Vous pouvez encore créer 1 service.");
		} else {
			System.out.println("Vous pouvez encore créer " + (nbMaxService - listeService.size()) + " services.");
		}
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
		nom = scanner.nextLine();
		
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
			budget = scanner.nextInt();
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
		ajouterService(service);
	}
	public void supprimerServiceMedical() {
		if (listeService.size() == 0) {
			System.out.println("Veuillez d'abord créer un service.");
			return;
		}
		afficherServicesMedicaux();
		System.out.println("(" + listeService.size() + ") Annuler");
		System.out.println("Quel service voulez-vous supprimer ?");
		Scanner scanner = new Scanner(System.in);
		int choix = scanner.nextInt();
		scanner.nextLine();
		while (choix < 0 || listeService.size() < choix) {
			System.out.println("Veuillez recommencer.");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		if (choix == listeService.size()) {
			return;
		} else {
			ServiceMedical service = listeService.get(choix);
			enleverService(service);
			System.out.println("Le service " + service.getNom() + " a été supprimé avec succès.");
		}
	}
	
	public void gererMedecins() {
		if (medecins.size() == 0) {
			System.out.println("Veuillez créer un médecin.");
			return;
		} 
		if (listeService.size() == 0) {
			System.out.println("Veuillez créer un service médical.");
			return;
		}
		afficherMedecins();
		System.out.println("(" + medecins.size() + ") Annuler");
		System.out.println("Quel médecin voulez-vous jouer ?");
		Scanner scanner = new Scanner(System.in);
		int id = scanner.nextInt();
		scanner.nextLine();
		while(id < 0 || medecins.size() < id) {
			System.out.println("Choisissez un nombre entre 0 et " + (medecins.size()) + ".");
			id = scanner.nextInt();
			scanner.nextLine();
		}
		if (id == medecins.size()) {
			return;
		}
		Medecin medecin = medecins.get(id);

		System.out.println("Que voulez-vous que le médecin fasse :");
		System.out.println("0 Examiner un service médical");
		System.out.println("1 Soigner les créatures d'un service médical.");
		System.out.println("2 Transférer une créature vers un service médical.");
		System.out.println("3 Réviser le budget d'un service médical.");
		int action = scanner.nextInt();
		scanner.nextLine();
		while(action < 0 || 3 < action) {
			System.out.println("Veuillez écrire un nombre entre 0 et 3.");
			action = scanner.nextInt();
			scanner.nextLine();
		}
		
		switch(action) {
			case(0):{
				System.out.println("Choisissez le service que vous voulez examiner.");
				ServiceMedical service = choisirServiceMedical();
				medecin.examinerService(service);
				break;
			}
			case(1):{
				System.out.println("Choisissez dans quel service vous voulez soigner une créature :");
				ServiceMedical service = choisirServiceMedical();
				if (service.getCreatures().isEmpty()) {
					System.out.println("Choisissez un autre service ou transférez une créature vers ce service.");
					break;
				} else {
					System.out.println("Quelle créature voulez-vous soigner ?");
					Creature creature = choisirCreature(service);
					System.out.println("Quelle maladie voulez-vous soigner ?");
					Maladie maladie = choisirMaladie(creature);
					medecin.soignerCreature(creature, maladie);
					System.out.println("La créature " + creature.getNom() + " a été guérie de la maladie " + maladie.getNomComplet());
					if (creature.getMaladies().isEmpty()) {
						System.out.println("Étant totalement guérie, la créature " + creature.getNom() + " a quitté l'hopital.");
						service.enleverCreature(creature);
					}
				}	
				break;
			}
			case(2):{
				System.out.println("---Transfert de créature---");
				System.out.println("Choisissez la source :");
				System.out.println("0 Liste d'attente");
				System.out.println("1 Service médical");
				scanner = new Scanner(System.in);
				int choixSource = scanner.nextInt();
				while(choixSource < 0 || 1 < choixSource) {
					System.out.println("Veuillez écrire un nombre entre 0 et 1.");
					choixSource = scanner.nextInt();
					scanner.nextLine();
				}
				if (choixSource == 0) {
					if (listeAttente.isEmpty()) {
						System.out.println("Aucune créature n'est présente dans la liste d'attente.");
						break;
					}
					System.out.println("Quelle créature voulez-vous transférer ?");
					Creature creature = choisirCreatureListeAttente();
					System.out.println("Dans quel service médical voulez-vous le transférer ?");
					ServiceMedical serviceDestination = choisirServiceMedical();
					medecin.transfererCreature(creature, this.listeAttente, serviceDestination);
				} else {
					if (this.getListeService().size() < 2 ){
						System.out.println("Il n'y a pas assez de services.");
						break;
					}
					ServiceMedical source = choisirServiceMedical();
					if (source.getCreatures().isEmpty()) {
						System.out.println("Ce service ne contient pas de créatures.");
						break;
					}
					System.out.println("Quelle créature voulez-vous transférer ?");
					Creature creature = choisirCreature(source);
					System.out.println("Dans quel service médical voulez-vous le transférer");
					afficherServicesMedicaux();
					ServiceMedical serviceDestination = choisirServiceMedical();
					medecin.transfererCreature(creature, source, serviceDestination);
				}
				break;
			}
			case(3):{
				break;
			}
		}
	}
	
	private ServiceMedical choisirServiceMedical() {
		this.afficherServicesMedicaux();
		Scanner scanner = new Scanner(System.in);
		int choixS = scanner.nextInt();
		scanner.nextLine();
		while(choixS < 0 || listeService.size()-1 < choixS) {
			System.out.println("Veuillez écrire un nombre entre 0 et " + (listeService.size()-1) + ".");
			choixS = scanner.nextInt();
			scanner.nextLine();
		}
		return listeService.get(choixS);
	}
	private Creature choisirCreature(ServiceMedical service) {
		for (int i = 0; i < service.getCreatures().size(); ++i) {
			Creature creature = service.getCreatures().get(i);
			System.out.println("(" + i + ") " + creature);
		}
		Scanner scanner = new Scanner(System.in);
		int choixC = scanner.nextInt();
		scanner.nextLine();
		while(choixC < 0 || service.getCreatures().size()-1 < choixC) {
			System.out.println("Veuillez écrire un nombre entre 0 et " + (service.getCreatures().size()-1) + ".");
			choixC = scanner.nextInt();
			scanner.nextLine();
		}
		return service.getCreatures().get(choixC);
	}
	private Creature choisirCreatureListeAttente() {
		afficherListeAttente();
		Scanner scanner = new Scanner(System.in);
		int choixC = scanner.nextInt();
		scanner.nextLine();
		while(choixC < 0 || listeAttente.size()-1 < choixC) {
			System.out.println("Veuillez écrire un nombre entre 0 et " + (listeAttente.size()-1) + ".");
			afficherListeAttente();
			choixC = scanner.nextInt();
			scanner.nextLine();
		}
		return listeAttente.get(choixC);
	}
	private Maladie choisirMaladie(Creature creature) {
		for (int i = 0; i < creature.getMaladies().size(); ++i) {
			Maladie maladie = creature.getMaladies().get(i);
			System.out.println("(" + i + ") " + maladie);
		}
		Scanner scanner = new Scanner(System.in);
		int choixM = scanner.nextInt();
		scanner.nextLine();
		while(choixM < 0 || creature.getMaladies().size()-1 < choixM) {
			System.out.println("Veuillez écrire un nombre entre 0 et " + (creature.getMaladies().size()-1) + ".");
			choixM = scanner.nextInt();
			scanner.nextLine();
		}
		return creature.getMaladies().get(choixM);
	}
	
	// Getters et Setters
	public List<Creature> getToutesCreatures(){
		List<Creature> creatures = new ArrayList<Creature>(listeAttente);
		for (int i=0; i<listeService.size(); ++i) {
			creatures.addAll(listeService.get(i).getCreatures());
		}
		return creatures;
	}
	public List<Creature> getListeAttente() {
		return this.listeAttente;
	}
	public void setListeAttente(ArrayList<Creature> listeAttente) {
		this.listeAttente = listeAttente;
	}
	public Map<Integer,ServiceMedical> getListeService() {
		return this.listeService;
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
}
