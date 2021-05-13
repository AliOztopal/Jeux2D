package modele.Blocks;

public class Fer extends Blocks {

	public Fer(String nom, int id) {
		super(nom, 20,false);
	}

	@Override
	public boolean dropable() {
		// TODO Auto-generated method stub
		return true;
	}

}