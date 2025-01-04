package main.creature;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
		
	}
	
	public void hurler() {
		System.out.println(this.getNom() + "hurle.");
		this.hurler = true;
	}
	
	public void semporter(List<Creature> creaturesProches) {
		System.out.println(this.getNom() + "s'emporte.");
		
	}
	
	public void ajouterMaladie(Maladie maladie) {
		this.maladies.add(maladie);
	}
	
	public void enleverMaladie(Maladie maladie) {
		this.maladies.remove(maladie);
	}
	
	public abstract void trepasser();
	public boolean estVIP() {
		return false;
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
