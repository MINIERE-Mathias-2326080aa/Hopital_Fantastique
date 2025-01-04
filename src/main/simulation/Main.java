package main.simulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import main.creature.*;
import main.hopital.*;
import main.maladie.Maladie;
import main.serviceMedical.CentreDeQuarantaine;
import main.serviceMedical.ServiceMedical;

public class Main {
	public static void main(String[] args){		
		HopitalFantastique hopital = new HopitalFantastique();
		
		Maladie mdc = new Maladie("Maladie débilitante chronique", "MDC", 1, 5);
		Maladie fomo = new Maladie("Fear of missing out", "FOMO", 1, 5);
		Maladie drs = new Maladie("Dépendance aux réseaux sociaux", "DRS", 1, 5);
		Maladie pec = new Maladie("Porphyrie érythropoïétique congénitale", "PEC", 1, 5);
		Maladie zpl = new Maladie("Zoopathie paraphrénique lycanthropique", "ZPL", 1, 5);
		Maladie bg = new Maladie("Nom de maladie à définir", "NDMAD", 1, 5);
		HopitalFantastique.setMaladies(Arrays.asList(mdc,fomo,drs,pec,zpl,bg));
		
		Medecin medecin1 = new Medecin(HopitalFantastique.genererCreature());
		Medecin medecin2 = new Medecin(HopitalFantastique.genererCreature());
		Medecin medecin3 = new Medecin(HopitalFantastique.genererCreature());
		hopital.setMedecins(new ArrayList<Medecin>(Arrays.asList(medecin1,medecin2,medecin3)));
		
		Runnable hopitalJob = new HopitalJob(hopital);
		Thread hopitalThread = new Thread(hopitalJob);
		hopitalThread.start();
		
		Scanner scanner = new Scanner(System.in);
		while(!HopitalFantastique.quitter) { 
			System.out.println("---------------------------");
			System.out.println("Que voulez-vous faire :");
			System.out.println("0 Quitter");
			System.out.println("1 Afficher la liste d'attente");
			System.out.println("2 Afficher les médecins");
			System.out.println("3 Afficher les maladies");
			System.out.println("4 Afficher les services médicaux");
			System.out.println("5 Afficher le nombre de patients");
			System.out.println("6 Ajouter un médecin");
			System.out.println("7 Créer un service medical");
			System.out.println("8 Gérer les médecins");
			System.out.println("---------------------------");
			int choix = scanner.nextInt();
			scanner.nextLine();
			
			while (choix < 0 || 8 < choix) {
				System.out.println("Veuillez faire un choix valable");
				choix = scanner.nextInt();
				scanner.nextLine();
			}
			
			switch(choix) {
				case 0:
					HopitalFantastique.quitter = true;
					break;
				case 1:
					hopital.afficherListeAttente();
					break;
				case 2:
					hopital.afficherMedecins();
					break;
				case(3):
					HopitalFantastique.afficherMaladies();
					break;
				case(4):
					hopital.afficherServicesMedicaux();
					break;
				case(5):
					hopital.afficherNbPatients();
					break;
				case(6):
					hopital.ajouterMedecin();
					break;
				case(7):
					hopital.creerServiceMedical();
					break;
				case(8):
					hopital.gererMedecins();
					break;
			}
		}
		scanner.close();
	}
}
