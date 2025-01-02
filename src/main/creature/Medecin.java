package main.creature;

import main.serviceMedical.ServiceMedical;

public class Medecin {
	private String type;
	private String nom;
	private String sexe;
	private int age;

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

	public void examinerService(ServiceMedical service) {
		
	}

	@Override
	public String toString() {
		return "Medecin [type=" + type + ", nom=" + nom + ", sexe=" + sexe + ", age=" + age + "]";
	}
}
