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
	
	public Maladie(Maladie maladie) {
		this.nomComplet = maladie.getNomComplet();
		this.nomAbrege = maladie.getNomAbrege();
		this.niveau = maladie.getNiveau();
		this.niveauMax = maladie.getNiveauMax();
	}

	public boolean estLetal() {
		return niveau == niveauMax;
	}
	
	public void changerNiveau(int niveau) {
		this.niveau = niveau;
	}
	
	public void augmenterNiveau() {
		if (niveau < niveauMax) {	
			this.niveau += 1;
		}
	}
	
	public void diminuerNiveau() {
		if (niveau > 0) {
			this.niveau -= 1;
		}
	}
	
	public void afficherMaladie() {
		System.out.println("Nom complet : " + nomComplet);
		System.out.println("Nom abrégé : " + nomAbrege);
		System.out.println("Niveau : " + niveau);
		System.out.println("Niveau max : " + niveauMax);
	}

	public String getNomComplet() {
		return nomComplet;
	}

	public String getNomAbrege() {
		return nomAbrege;
	}

	public int getNiveau() {
		return niveau;
	}

	public int getNiveauMax() {
		return niveauMax;
	}

	@Override
	public String toString() {
		return "Maladie [nomComplet=" + nomComplet + ", nomAbrege=" + nomAbrege + ", niveau=" + niveau + ", niveauMax=" + niveauMax + "]";
	}
	
}
