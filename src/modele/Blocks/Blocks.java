package modele.Blocks;

import modele.items.Items;

public abstract class Blocks extends Items {

	private boolean traversable;

	public Blocks(String nomBlock, int id, boolean traversable) {
		super(false, id , nomBlock);
		this.traversable = traversable;
		
	}

	public boolean getTraversable() {
		return this.traversable;
	}

	public abstract boolean dropable();
	
}
