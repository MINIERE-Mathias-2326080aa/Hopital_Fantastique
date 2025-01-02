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
		for (Creature creature : creaturesProches) {
			Random r = new Random();
			if (r.nextInt(100) > 50) {
				contaminer(creature);
			}
		}
	}
	
	public void ajouterMaladie(Maladie maladie) {
		this.maladies.add(maladie);
	}
	
	private void contaminer(Creature creature) {
		Random random = new Random();
		Maladie maladie = this.maladies.get(random.nextInt(maladies.size()));
		creature.ajouterMaladie(maladie);
		System.out.println("La créature " + creature.getNom() + " a été infecté par la maladie " + maladie.getNomComplet());
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ "[nom=" + nom + ", sexe=" + sexe + ", poids=" + poids + ", taille=" + taille + ", age=" + age
				+ ", moral=" + moral + ", maladies=" + maladies + "]";
	}

}
