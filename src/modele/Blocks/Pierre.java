package modele.Blocks;

public class Pierre extends Blocks {

	public Pierre() {
		super("Pierre", 3,false);
	}

	@Override
	public boolean dropable() {
		// TODO Auto-generated method stub
		return true;
	}

}
