package modele.items;

public abstract class Items {

	private boolean sefabrique; // POUR LES BLOCKS
	private String nom;
	private int id;
	
	
	
	public Items(boolean sefabrique , int id , String Nom) {
		this.sefabrique = sefabrique;
		this.nom=Nom;
		this.id=id;

	}

	public boolean getSeFabrique() {
		return sefabrique;

	}
	
	public String getNom() {
		return this.nom;
	}

	public int getId() {
		return this.id;
	}
	

}