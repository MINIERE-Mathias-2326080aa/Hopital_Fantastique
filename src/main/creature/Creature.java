package main.creature;

import java.util.ArrayList;
import java.util.List;
import main.maladie.Maladie;

public abstract class Creature {
	private String nom;
	private String sexe;
	private int poids;
	private int taille;
	private int age;
	private int moral;
	private List<Maladie> maladies = new ArrayList<Maladie>();
	private List<Creature> creaturesProches = new ArrayList<Creature>();
	private boolean hurler = false;
	
	public Creature(String nom, String sexe, int poids, int taille, int age, int moral, Maladie maladie) {
		super();
		this.nom = nom;
		this.sexe = sexe;
		this.poids = poids;
		this.taille = taille;
		this.age = age;
		this.moral = moral;
		maladies.add(maladie);
	}
	
	public void attendre() {
		if (moral <= 0) {
			if (!hurler) {
				hurler();
			} else {
				semporter();
			}
		} else if (estVIP()){
			diminuerMoral(30);
		} else {
			int ancienMoral = moral;
			for (int i = 0; i < getCreaturesProches().size() && moral==ancienMoral;++i) {
				if (this.getClass().getSimpleName() == creaturesProches.get(i).getClass().getSimpleName()) {
					diminuerMoral(5);
				} else {
					diminuerMoral(10);
				}
			}
		}
	}
	
	public void hurler() {
		System.out.println("[" + this.getNom() + " hurle]");
		this.hurler = true;
	}
	
	public void semporter() {
		System.out.println("[" + this.getNom() + " s'emporte]");
		
	}
	
	public void ajouterMaladie(Maladie maladie) {
		for (int i = 0; i < maladies.size(); ++i) {
			if (maladie.getNomAbrege() == maladies.get(i).getNomAbrege()) {
				return;
			}
		}
		this.maladies.add(new Maladie(maladie));
		System.out.println("[La créature " + getNom() + " a été infecté par la maladie " + maladie.getNomComplet() + "]");
	}
	
	public void enleverMaladie(Maladie maladie) {
		this.maladies.remove(maladie);
	}
	
	public abstract void trepasser();
	public boolean estVIP() {
		return false;
	}
	
	public void diminuerMoral(int quantite) {
		if (this.moral - quantite > 0) {
			setMoral(this.moral - quantite);
		} else {
			setMoral(0);
		}
	}
	
	public void afficherCreature() {
		System.out.println("Nom : " + nom);
		System.out.println("Sexe : " + (sexe=="M" ? "Male" : "Femelle"));
		System.out.println("Poids : " + poids + "kg");
		System.out.println("Taille : " + taille + "cm");
		System.out.println("Moral : " + moral);
		System.out.println("Maladies :");
		for (int i = 0 ; i < maladies.size(); ++i) {
			System.out.println("----------");
			maladies.get(i).afficherMaladie();
		}
	}

	public String getNom() {
		return nom;
	}

	public String getSexe() {
		return sexe;
	}

	public int getAge() {
		return age;
	}

	public int getPoids() {
		return poids;
	}

	public int getTaille() {
		return taille;
	}

	public void setMoral(int moral) {
		this.moral = moral;
	}
	
	public int getMoral() {
		return moral;
	}

	public List<Maladie> getMaladies() {
		return maladies;
	}
	
	public List<Creature> getCreaturesProches() {
		return creaturesProches;
	}

	public void setCreaturesProches(List<Creature> creaturesProches) {
		this.creaturesProches = creaturesProches;
	}
	
	public void ajouterCreatureProche(Creature creature) {
		this.creaturesProches.add(creature);
	}
	
	public void enleverCreatureProche(Creature creature) {
		this.creaturesProches.remove(creature);
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ "[nom=" + nom + ", sexe=" + sexe + ", poids=" + poids + ", taille=" + taille + ", age=" + age
				+ ", moral=" + moral + ", maladies=" + maladies + "]";
	}

}
