package modele.Blocks;

public class Diamant extends Blocks {

	public Diamant(String nom, int id) {
		super(nom, 21,false);
	}

	@Override
	public boolean dropable() {
		// TODO Auto-generated method stub
		return true;
	}

}
