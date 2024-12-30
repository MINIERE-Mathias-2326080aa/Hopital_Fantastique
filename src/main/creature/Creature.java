package main.creature;

public abstract class Creature {
	private String nom;
	private String sexe;
	private int poids;
	private int taille;
	private int age;
	private int moral;
	
	public Creature(String nom, String sexe, int poids, int taille, int age, int moral) {
		super();
		this.nom = nom;
		this.sexe = sexe;
		this.poids = poids;
		this.taille = taille;
		this.age = age;
		this.moral = moral;
	}
	
	public void attendre() {
		
	}
	
	public void hurler() {
		
	}
	
	public void semporter() {
		
	}
	
	protected void contaminer() {
		
	}
	
	public abstract void trepasser();
}
