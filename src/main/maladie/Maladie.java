package main.maladie;

public class Maladie {
	private String nomComplet;
	private String nomAbrege;
	private int niveau;
	private int niveauMax;
	
	public Maladie(String nomComplet, String nomAbrege, int niveau, int niveauMax) {
		this.nomComplet = nomComplet;
		this.nomAbrege = nomAbrege;
		this.niveau = niveau;
		this.niveauMax = niveauMax;
	}
	
	public boolean estLetal() {
		return niveau == niveauMax;
	}
	
	public void changerNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public void augmenterNiveau() {
		this.niveau += 1;
	}
	
	public void diminuerNiveau() {
		this.niveau -= 1;
	}
	
}
