package modele.items;

public class Armes extends Items{

	private int degats;

	public Armes(int degats) {
		super(true,4,"Armes");
		this.setDegats(degats);
	}

	public int getDegats() {
		return degats;
	}

	public void setDegats(int degats) {
		this.degats = degats;
	}

}
